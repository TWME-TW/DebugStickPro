package dev.twme.debugstickpro.utils;

import dev.twme.blocket.api.BlocketAPI;
import dev.twme.blocket.types.BlocketPosition;
import dev.twme.blocket.models.Audience;
import dev.twme.blocket.models.Stage;
import dev.twme.blocket.models.View;
import dev.twme.blocket.models.Pattern;
import dev.twme.debugstickpro.DebugStickPro;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SendFakeBarrier {
    // Track fake barriers for each player at each location
    private static final Map<UUID, Map<String, String>> playerFakeBarriers = new ConcurrentHashMap<>();
    
    /**
     * Generate a unique identifier for a location
     */
    private static String getLocationKey(Location location) {
        return location.getWorld().getName() + "_" + 
               location.getBlockX() + "_" + 
               location.getBlockY() + "_" + 
               location.getBlockZ();
    }
    
    /**
     * Generate a unique stage ID for a player's fake barrier
     */
    private static String getStageId(UUID playerUUID, Location location) {
        return "fake_barrier_" + playerUUID.toString().substring(0, 8) + "_" + getLocationKey(location);
    }
    
    public static void sendFakeBarrier(Player player, Location location) {
        if (player == null) return;
        
        BlocketAPI blocketAPI = DebugStickPro.getInstance().getBlocketAPI();
        if (blocketAPI == null) return;
        
        // Create BlocketPosition from Location
        BlocketPosition pos = BlocketPosition.fromLocation(location);
        
        // Create audience for this player only
        Audience audience = Audience.fromPlayers(Set.of(player));
        
        // Create a unique stage ID for this player and location
        String stageId = getStageId(player.getUniqueId(), location);
        
        // Create stage for the fake barrier (single block area)
        Stage stage = new Stage(stageId, location.getWorld(), pos, pos, audience);
        
        // Create pattern for barrier block
        BlockData barrierData = Material.BARRIER.createBlockData();
        Map<BlockData, Double> blockWeights = new HashMap<>();
        blockWeights.put(barrierData, 1.0);
        Pattern barrierPattern = new Pattern(blockWeights);
        
        // Create view with barrier pattern
        View view = new View("barrier_view", stage, barrierPattern, false);
        
        // Set the specific block directly instead of using addBlock
        view.setBlock(pos, barrierData);
        
        // Add view to stage
        stage.addView(view);
        
        // Register stage with Blocket
        blocketAPI.getStageManager().createStage(stage);
        
        // Send blocks to audience
        stage.sendBlocksToAudience();
        
        // Track this fake barrier
        playerFakeBarriers.computeIfAbsent(player.getUniqueId(), k -> new ConcurrentHashMap<>())
                          .put(getLocationKey(location), stageId);
    }

    public static void sendFakeBarrier(UUID playerUUID, Location location) {
        Player player = Bukkit.getPlayer(playerUUID);
        sendFakeBarrier(player, location);
    }

    public static void sendFakeBarrierToAllPlayers(Location location) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendFakeBarrier(player, location);
        }
    }

    /**
     * Remove fake barrier (unfreeze the block)
     * 
     * @param player The player to unfreeze the block for
     * @param location The location to unfreeze
     */
    public static void removeFakeBarrier(Player player, Location location) {
        if (player == null) return;
        
        BlocketAPI blocketAPI = DebugStickPro.getInstance().getBlocketAPI();
        if (blocketAPI == null) return;
        
        UUID playerUUID = player.getUniqueId();
        String locationKey = getLocationKey(location);
        
        // Check if this player has a fake barrier at this location
        Map<String, String> playerBarriers = playerFakeBarriers.get(playerUUID);
        if (playerBarriers != null) {
            String stageId = playerBarriers.get(locationKey);
            if (stageId != null) {
                // Get and properly remove the stage
                try {
                    blocketAPI.getStageManager().deleteStage(stageId);
                } catch (Exception e) {
                    // Stage might already be removed, continue with cleanup
                }
                
                // Remove from tracking
                playerBarriers.remove(locationKey);
                if (playerBarriers.isEmpty()) {
                    playerFakeBarriers.remove(playerUUID);
                }
            }
        }
    }

    /**
     * Remove fake barrier (unfreeze the block) for a specific player UUID
     * 
     * @param playerUUID The UUID of the player to unfreeze the block for
     * @param location The location to unfreeze
     */
    public static void removeFakeBarrier(UUID playerUUID, Location location) {
        Player player = Bukkit.getPlayer(playerUUID);
        if (player != null) {
            removeFakeBarrier(player, location);
        } else {
            // Handle offline player
            BlocketAPI blocketAPI = DebugStickPro.getInstance().getBlocketAPI();
            if (blocketAPI == null) return;
            
            String locationKey = getLocationKey(location);
            Map<String, String> playerBarriers = playerFakeBarriers.get(playerUUID);
            if (playerBarriers != null) {
                String stageId = playerBarriers.get(locationKey);
                if (stageId != null) {
                    try {
                        blocketAPI.getStageManager().deleteStage(stageId);
                    } catch (Exception e) {
                        // Stage might already be removed, continue with cleanup
                    }
                    playerBarriers.remove(locationKey);
                    if (playerBarriers.isEmpty()) {
                        playerFakeBarriers.remove(playerUUID);
                    }
                }
            }
        }
    }

    /**
     * Remove fake barrier (unfreeze the block) for all players
     * 
     * @param location The location to unfreeze
     */
    public static void removeFakeBarrierFromAllPlayers(Location location) {
        String locationKey = getLocationKey(location);
        BlocketAPI blocketAPI = DebugStickPro.getInstance().getBlocketAPI();
        if (blocketAPI == null) return;
        
        // Remove from all players
        for (Map.Entry<UUID, Map<String, String>> entry : playerFakeBarriers.entrySet()) {
            Map<String, String> playerBarriers = entry.getValue();
            String stageId = playerBarriers.get(locationKey);
            if (stageId != null) {
                try {
                    blocketAPI.getStageManager().deleteStage(stageId);
                } catch (Exception e) {
                    // Stage might already be removed, continue with cleanup
                }
                playerBarriers.remove(locationKey);
            }
        }
        
        // Clean up empty maps
        playerFakeBarriers.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }
    
    /**
     * Clean up all fake barriers for a player (called when player leaves)
     * 
     * @param playerUUID The UUID of the player to clean up
     */
    public static void cleanupPlayerFakeBarriers(UUID playerUUID) {
        BlocketAPI blocketAPI = DebugStickPro.getInstance().getBlocketAPI();
        if (blocketAPI == null) return;
        
        Map<String, String> playerBarriers = playerFakeBarriers.remove(playerUUID);
        if (playerBarriers != null) {
            for (String stageId : playerBarriers.values()) {
                try {
                    blocketAPI.getStageManager().deleteStage(stageId);
                } catch (Exception e) {
                    // Stage might already be removed, continue with cleanup
                }
            }
        }
    }
    
    /**
     * Check if a player has a fake barrier at a specific location
     * 
     * @param playerUUID The player's UUID
     * @param location The location to check
     * @return true if the player has a fake barrier at this location
     */
    public static boolean hasFakeBarrier(UUID playerUUID, Location location) {
        Map<String, String> playerBarriers = playerFakeBarriers.get(playerUUID);
        if (playerBarriers != null) {
            return playerBarriers.containsKey(getLocationKey(location));
        }
        return false;
    }
}
