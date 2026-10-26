package dev.twme.debugstickpro.mode.freeze;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.simple.PacketPlaySendEvent;
import com.github.retrooper.packetevents.netty.buffer.ByteBufHelper;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.world.states.WrappedBlockState;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockChange;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerMultiBlockChange;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUnloadChunk;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import io.github.twme.virtualentities.VirtualViewer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Shows frozen blocks to clients as their frozen state although the server holds a barrier anchor.
 * <p>
 * Block updates for frozen positions are rewritten, and whenever a client receives a chunk that
 * contains frozen blocks, the fake block states and outline entities are sent again after it.
 */
public final class FreezePacketLayer {
    private static PacketListenerCommon listener;
    private static boolean initialized;

    private FreezePacketLayer() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }

        listener = new FreezePacketListener();
        PacketEvents.getAPI().getEventManager().registerListener(listener);
        initialized = true;
    }

    public static synchronized void shutdown() {
        if (!initialized) {
            return;
        }

        if (listener != null) {
            PacketEvents.getAPI().getEventManager().unregisterListener(listener);
            listener = null;
        }
        initialized = false;
    }

    /**
     * Sends the real server state of a block to every player in its world.
     */
    public static void resyncBlock(Block block) {
        int stateId = SpigotConversionUtil.fromBukkitBlockData(block.getBlockData()).getGlobalId();
        FreezeLocation location = FreezeLocation.of(block);
        for (Player player : block.getWorld().getPlayers()) {
            sendBlockState(player, location, stateId);
        }
    }

    static void sendBlockState(Player player, FreezeLocation location, int stateId) {
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, blockChange(location, stateId));
    }

    private static PacketWrapper<?> blockChange(FreezeLocation location, int stateId) {
        return new WrapperPlayServerBlockChange(new Vector3i(location.x(), location.y(), location.z()), stateId);
    }

    /**
     * @return the global state id the client must see at this position, or null to leave the packet untouched
     */
    private static Integer getClientStateId(UUID worldUUID, int x, int y, int z) {
        FreezeLocation location = new FreezeLocation(worldUUID, x, y, z);
        FreezeBlockData frozenData = FreezeBlockManager.getFrozenBlock(location);
        if (frozenData != null) {
            return frozenData.getClientStateId();
        }

        String dependentData = FreezeBlockManager.getProtectedDependentData(location);
        if (dependentData == null) {
            return null;
        }

        WrappedBlockState state = WrappedBlockState.getByString(dependentData);
        return state == null ? null : state.getGlobalId();
    }

    private static final class FreezePacketListener extends SimplePacketListenerAbstract {
        private FreezePacketListener() {
            super(PacketListenerPriority.NORMAL);
        }

        @Override
        public void onPacketPlaySend(PacketPlaySendEvent event) {
            Object rawPlayer = event.getPlayer();
            if (!(rawPlayer instanceof Player player)) {
                return;
            }

            PacketType.Play.Server packetType = event.getPacketType();
            if (packetType == PacketType.Play.Server.BLOCK_CHANGE) {
                rewriteBlockChange(event, player);
            } else if (packetType == PacketType.Play.Server.MULTI_BLOCK_CHANGE) {
                rewriteMultiBlockChange(event, player);
            } else if (packetType == PacketType.Play.Server.CHUNK_DATA) {
                replayChunk(event, player);
            } else if (packetType == PacketType.Play.Server.UNLOAD_CHUNK) {
                forgetChunk(event, player);
            }
        }

        private static void rewriteBlockChange(PacketPlaySendEvent event, Player player) {
            WrapperPlayServerBlockChange wrapper = new WrapperPlayServerBlockChange(event);
            Vector3i position = wrapper.getBlockPosition();

            Integer stateId = getClientStateId(player.getWorld().getUID(), position.getX(), position.getY(), position.getZ());
            if (stateId != null && stateId != wrapper.getBlockId()) {
                wrapper.setBlockID(stateId);
                event.markForReEncode(true);
            }
        }

        private static void rewriteMultiBlockChange(PacketPlaySendEvent event, Player player) {
            WrapperPlayServerMultiBlockChange wrapper = new WrapperPlayServerMultiBlockChange(event);
            WrapperPlayServerMultiBlockChange.EncodedBlock[] blocks = wrapper.getBlocks();

            boolean changed = false;
            UUID worldUUID = player.getWorld().getUID();
            for (WrapperPlayServerMultiBlockChange.EncodedBlock block : blocks) {
                Integer stateId = getClientStateId(worldUUID, block.getX(), block.getY(), block.getZ());
                if (stateId != null && stateId != block.getBlockId()) {
                    block.setBlockId(stateId);
                    changed = true;
                }
            }

            if (changed) {
                wrapper.setBlocks(blocks);
                event.markForReEncode(true);
            }
        }

        private static void replayChunk(PacketPlaySendEvent event, Player player) {
            FreezeLocation.FreezeChunk chunk = readChunkPosition(event, player);
            List<FreezeBlockData> frozenBlocks = FreezeBlockManager.getFrozenBlocksInChunk(chunk);
            if (frozenBlocks.isEmpty()) {
                return;
            }

            User user = event.getUser();
            // The chunk carries the server's barrier anchors and the client drops entities when it
            // (re)loads a chunk, so both the fake states and the outlines must follow the chunk.
            event.getTasksAfterSend().add(() -> {
                VirtualViewer viewer = VirtualViewer.of(user);
                for (FreezeBlockData frozenData : frozenBlocks) {
                    user.sendPacket(blockChange(frozenData.getLocation(), frozenData.getClientStateId()));
                    FreezeBlockManager.addOutlineViewer(frozenData, viewer);
                }
            });
        }

        private static void forgetChunk(PacketPlaySendEvent event, Player player) {
            WrapperPlayServerUnloadChunk wrapper = new WrapperPlayServerUnloadChunk(event);
            FreezeLocation.FreezeChunk chunk = new FreezeLocation.FreezeChunk(
                    player.getWorld().getUID(), wrapper.getChunkX(), wrapper.getChunkZ()
            );
            for (FreezeBlockData frozenData : FreezeBlockManager.getFrozenBlocksInChunk(chunk)) {
                FreezeBlockManager.removeOutlineViewer(frozenData, player.getUniqueId());
            }
        }

        /**
         * Reads only the leading chunk coordinates instead of decoding the whole column.
         */
        private static FreezeLocation.FreezeChunk readChunkPosition(PacketPlaySendEvent event, Player player) {
            Object buffer = event.getByteBuf();
            int readerIndex = ByteBufHelper.readerIndex(buffer);
            try {
                int chunkX = ByteBufHelper.readInt(buffer);
                int chunkZ = ByteBufHelper.readInt(buffer);
                return new FreezeLocation.FreezeChunk(player.getWorld().getUID(), chunkX, chunkZ);
            } finally {
                ByteBufHelper.readerIndex(buffer, readerIndex);
            }
        }
    }
}
