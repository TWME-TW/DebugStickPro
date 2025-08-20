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
        if (player == null) {
            System.out.println("[SendFakeBarrier] Player is null!");
            return;
        }
        
        // Check if this player already has a fake barrier at this location
        UUID playerUUID = player.getUniqueId();
        String locationKey = getLocationKey(location);
        
        Map<String, String> playerBarriers = playerFakeBarriers.get(playerUUID);
        if (playerBarriers != null && playerBarriers.containsKey(locationKey)) {
            System.out.println("[SendFakeBarrier] Player " + player.getName() + " already has a fake barrier at " + locationKey);
            return; // Don't send duplicate fake barrier
        }
        
        BlocketAPI blocketAPI = DebugStickPro.getInstance().getBlocketAPI();
        if (blocketAPI == null) {
            System.out.println("[SendFakeBarrier] BlocketAPI is null!");
            return;
        }
        
        System.out.println("[SendFakeBarrier] Sending fake barrier to " + player.getName() + " at " + location.toString());
        
        try {
            // Create BlocketPosition from Location
            BlocketPosition pos = BlocketPosition.fromLocation(location);
            System.out.println("[SendFakeBarrier] Created BlocketPosition: " + pos.toString());
            
            // Create audience for this player only
            Audience audience = Audience.fromPlayers(Set.of(player));
            System.out.println("[SendFakeBarrier] Created Audience with 1 player");
            
            // Create a unique stage ID for this player and location
            String stageId = getStageId(playerUUID, location);
            System.out.println("[SendFakeBarrier] Stage ID: " + stageId);
            
            // Create stage for the fake barrier (single block area)
            Stage stage = new Stage(stageId, location.getWorld(), pos, pos, audience);
            System.out.println("[SendFakeBarrier] Created Stage");
            
            // Create pattern for barrier block
            BlockData barrierData = Material.BARRIER.createBlockData();
            Map<BlockData, Double> blockWeights = new HashMap<>();
            blockWeights.put(barrierData, 1.0);
            Pattern barrierPattern = new Pattern(blockWeights);
            System.out.println("[SendFakeBarrier] Created Pattern");
            
            // Create view with barrier pattern
            View view = new View("barrier_view", stage, barrierPattern, false);
            System.out.println("[SendFakeBarrier] Created View");
            
            // Add the specific block using addBlock
            view.addBlock(pos);
            System.out.println("[SendFakeBarrier] Added block to view");
            
            // Add view to stage
            stage.addView(view);
            System.out.println("[SendFakeBarrier] Added view to stage");
            
            // Register stage with Blocket FIRST
            blocketAPI.getStageManager().createStage(stage);
            System.out.println("[SendFakeBarrier] Registered stage with BlocketAPI");
            
            // THEN send blocks to audience
            stage.sendBlocksToAudience();
            System.out.println("[SendFakeBarrier] Sent blocks to audience");
        
            
            // Track this fake barrier
            playerFakeBarriers.computeIfAbsent(playerUUID, k -> new ConcurrentHashMap<>())
                              .put(locationKey, stageId);
            
            System.out.println("[SendFakeBarrier] Successfully sent fake barrier!");
            
        } catch (Exception e) {
            System.out.println("[SendFakeBarrier] Error: " + e.getMessage());
            e.printStackTrace();
        }
    }    public static void sendFakeBarrier(UUID playerUUID, Location location) {
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
        if (player == null) {
            System.out.println("[RemoveFakeBarrier] Player is null!");
            return;
        }
        
        BlocketAPI blocketAPI = DebugStickPro.getInstance().getBlocketAPI();
        if (blocketAPI == null) {
            System.out.println("[RemoveFakeBarrier] BlocketAPI is null!");
            return;
        }
        
        UUID playerUUID = player.getUniqueId();
        String locationKey = getLocationKey(location);
        
        System.out.println("[RemoveFakeBarrier] Removing fake barrier for " + player.getName() + " at " + locationKey);
        
        // Check if this player has a fake barrier at this location
        Map<String, String> playerBarriers = playerFakeBarriers.get(playerUUID);
        if (playerBarriers != null) {
            String stageId = playerBarriers.get(locationKey);
            if (stageId != null) {
                System.out.println("[RemoveFakeBarrier] Found stage ID: " + stageId);
                // Get and properly remove the stage
                try {
                    blocketAPI.getStageManager().deleteStage(stageId);
                    System.out.println("[RemoveFakeBarrier] Successfully deleted stage");
                } catch (Exception e) {
                    System.out.println("[RemoveFakeBarrier] Error deleting stage: " + e.getMessage());
                    // Stage might already be removed, continue with cleanup
                }
                
                // Remove from tracking
                playerBarriers.remove(locationKey);
                if (playerBarriers.isEmpty()) {
                    playerFakeBarriers.remove(playerUUID);
                }
                System.out.println("[RemoveFakeBarrier] Cleaned up tracking data");
            } else {
                System.out.println("[RemoveFakeBarrier] No stage ID found for location: " + locationKey);
            }
        } else {
            System.out.println("[RemoveFakeBarrier] No fake barriers found for player: " + player.getName());
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
