package dev.twme.debugstickpro.utils;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.world.states.WrappedBlockState;
import com.github.retrooper.packetevents.util.Vector3i;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerBlockChange;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class FakeBlockAPI {

    private static final FakeBlockAPI INSTANCE = new FakeBlockAPI();
    private boolean initialized = false;
    private PacketListener listener;

    // Structure: Location -> Player UUID -> Fake BlockData
    private final Map<Location, Map<UUID, BlockData>> fakeBlocks = new ConcurrentHashMap<>();

    private FakeBlockAPI() {
        // Private constructor for singleton pattern
    }

    /**
     * Gets the singleton instance of the FakeBlockAPI.
     *
     * @return The FakeBlockAPI instance.
     */
    public static FakeBlockAPI getInstance() {
        return INSTANCE;
    }

    /**
     * Initializes the API. This must be called once in your plugin's onEnable method.
     *
     * @param plugin Your plugin instance.
     */
    public void init(JavaPlugin plugin) {
        if (initialized) {
            return;
        }

        // It's crucial that PacketEvents is loaded before this plugin.
        // Add "PacketEvents" to your plugin's `depend` list in plugin.yml.
        PacketEvents.getAPI().getEventManager().registerListener(listener = new PacketListener() {
            @Override
            public void onPacketSend(PacketSendEvent event) {
                if (event.getPacketType() != PacketType.Play.Server.BLOCK_CHANGE) {
                    return;
                }

                Player player = event.getPlayer();
                if (player == null) return;

                WrapperPlayServerBlockChange packet = new WrapperPlayServerBlockChange(event);
                Vector3i pos = packet.getBlockPosition();
                Location loc = new Location(player.getWorld(), pos.getX(), pos.getY(), pos.getZ());

                Map<UUID, BlockData> playersAtLocation = fakeBlocks.get(loc);
                if (playersAtLocation != null && playersAtLocation.containsKey(player.getUniqueId())) {
                    // If there is a fake block for this player at this location,
                    // cancel the real block update packet from the server.
                    event.setCancelled(true);
                }
            }
        }, PacketListenerPriority.NORMAL);

        initialized = true;
        plugin.getLogger().info("FakeBlockAPI has been initialized.");
    }

    /**
     * Shuts down the API and unregisters the packet listener.
     * Call this in your plugin's onDisable method.
     */
    public void shutdown() {
        if (!initialized) {
            return;
        }
        PacketEvents.getAPI().getEventManager().unregisterListener((PacketListenerCommon) listener);
        fakeBlocks.clear();
        initialized = false;
    }

    /**
     * Sends a fake block to a player.
     *
     * @param player    The player to send the fake block to.
     * @param location  The location to display the fake block at.
     * @param material  The material of the fake block.
     */
    public void setBlock(Player player, Location location, Material material) {
        setBlock(player, location, material.createBlockData());
    }

    /**
     * Sends a fake block to a player using specific block data.
     *
     * @param player     The player to send the fake block to.
     * @param location   The location to display the fake block at.
     * @param blockData  The BlockData of the fake block (e.g., for directional blocks).
     */
    public void setBlock(Player player, Location location, BlockData blockData) {
        if (!initialized) {
            throw new IllegalStateException("FakeBlockAPI has not been initialized! Call init() in onEnable.");
        }

        Location blockLoc = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());

        // Store the fake block information
        fakeBlocks.computeIfAbsent(blockLoc, k -> new ConcurrentHashMap<>()).put(player.getUniqueId(), blockData);

        // Get the global palette ID for the block state
        int blockStateId = (WrappedBlockState.getByString(blockData.getAsString()).getGlobalId());

        // Create the block change packet
        Vector3i position = new Vector3i(blockLoc.getBlockX(), blockLoc.getBlockY(), blockLoc.getBlockZ());
        WrapperPlayServerBlockChange packet = new WrapperPlayServerBlockChange(position, blockStateId);

        // Send the packet to the player
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, packet);
    }

    /**
     * Removes a fake block for a player, revealing the real block.
     *
     * @param player   The player to remove the fake block for.
     * @param location The location of the fake block.
     */
    public void removeBlock(Player player, Location location) {
        Location blockLoc = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());

        Map<UUID, BlockData> playersAtLocation = fakeBlocks.get(blockLoc);
        if (playersAtLocation != null && playersAtLocation.remove(player.getUniqueId()) != null) {
            // If the map for this location becomes empty, remove it to save memory.
            if (playersAtLocation.isEmpty()) {
                fakeBlocks.remove(blockLoc);
            }
            // Send the real block from the server to the client to update their view.
            player.sendBlockChange(blockLoc, blockLoc.getBlock().getBlockData());
        }
    }

    /**
     * Gets the fake block data a player sees at a specific location.
     *
     * @param player The player.
     * @param location The location to check.
     * @return The fake BlockData, or null if the player sees the real block.
     */
    public BlockData getFakeBlock(Player player, Location location) {
        Location blockLoc = new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
        Map<UUID, BlockData> playersAtLocation = fakeBlocks.get(blockLoc);
        if (playersAtLocation != null) {
            return playersAtLocation.get(player.getUniqueId());
        }
        return null;
    }
}