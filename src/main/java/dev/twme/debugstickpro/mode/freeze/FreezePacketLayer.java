package dev.twme.debugstickpro.mode.freeze;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.SimplePacketListenerAbstract;
import com.github.retrooper.packetevents.event.simple.PacketPlaySendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.world.states.WrappedBlockState;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockChange;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerMultiBlockChange;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

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
                return;
            }

            if (packetType == PacketType.Play.Server.MULTI_BLOCK_CHANGE) {
                rewriteMultiBlockChange(event, player);
            }
        }

        private static void rewriteBlockChange(PacketPlaySendEvent event, Player player) {
            WrapperPlayServerBlockChange wrapper = new WrapperPlayServerBlockChange(event);
            Vector3i position = wrapper.getBlockPosition();

            String expectedData = getExpectedData(player.getWorld(), position.getX(), position.getY(), position.getZ());
            if (expectedData == null) {
                return;
            }

            WrappedBlockState expectedState = WrappedBlockState.getByString(expectedData);
            if (expectedState == null) {
                return;
            }

            wrapper.setBlockState(expectedState);
        }

        private static void rewriteMultiBlockChange(PacketPlaySendEvent event, Player player) {
            WrapperPlayServerMultiBlockChange wrapper = new WrapperPlayServerMultiBlockChange(event);
            WrapperPlayServerMultiBlockChange.EncodedBlock[] blocks = wrapper.getBlocks();

            boolean changed = false;
            World world = player.getWorld();
            for (WrapperPlayServerMultiBlockChange.EncodedBlock block : blocks) {
                String expectedData = getExpectedData(world, block.getX(), block.getY(), block.getZ());
                if (expectedData == null) {
                    continue;
                }

                WrappedBlockState expectedState = WrappedBlockState.getByString(expectedData);
                if (expectedState == null) {
                    continue;
                }

                block.setBlockState(expectedState);
                changed = true;
            }

            if (changed) {
                wrapper.setBlocks(blocks);
            }
        }

        private static String getExpectedData(World world, int x, int y, int z) {
            Location location = new Location(world, x, y, z);
            return FreezeBlockManager.getProtectedDependentData(location);
        }
    }
}
