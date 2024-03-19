package dev.twme.debugstickpro.mode.freeze;

import dev.twme.debugstickpro.util.PersistentKeys;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class NewFreezeBlockManager {
    private static final HashMap<UUID, HashSet<NewFreezeBlockLocation>> playerFreezeBlockLocations = new HashMap<>();
    private static final HashMap<NewFreezeBlockLocation, FreezeBlockData> freezeBlockDataMap = new HashMap<>();

    // <UUID worldUUID, NewFreezeBlockLocation>
    private static final HashMap<UUID, HashSet<NewFreezeBlockLocation>> worldFreezeBlockLocations = new HashMap<>();

    public static void addFreezeBlock(UUID playerUUID, Block block) {
        Location location = block.getLocation();
        NewFreezeBlockLocation freezeBlockLocation = new NewFreezeBlockLocation(location, playerUUID);
        if (!playerFreezeBlockLocations.containsKey(playerUUID)) {
            playerFreezeBlockLocations.put(playerUUID, new HashSet<>());
        }

        playerFreezeBlockLocations.get(playerUUID).add(freezeBlockLocation);

        if (!worldFreezeBlockLocations.containsKey(location.getWorld().getUID())) {
            worldFreezeBlockLocations.put(location.getWorld().getUID(), new HashSet<>());
        }
        worldFreezeBlockLocations.get(location.getWorld().getUID()).add(freezeBlockLocation);

        freezeBlockDataMap.put(freezeBlockLocation, freezeBlockGenerator(freezeBlockLocation, playerUUID));
    }

    public static void removeOneFreezeBlock(UUID playerUUID, Block block) {
        Location location = block.getLocation();
        NewFreezeBlockLocation freezeBlockLocation = new NewFreezeBlockLocation(location);
        if (playerFreezeBlockLocations.get(playerUUID).contains(freezeBlockLocation)) {
            removeFreezeBlockData(freezeBlockLocation);
            playerFreezeBlockLocations.get(playerUUID).remove(freezeBlockLocation);
        }
    }

    public static void clearFreezeBlocksOnWorld(UUID worldUUID) {

        if (!worldFreezeBlockLocations.containsKey(worldUUID)) {
            return;
        }
        HashSet<NewFreezeBlockLocation> freezeBlockLocations = worldFreezeBlockLocations.get(worldUUID);

        for (NewFreezeBlockLocation location : freezeBlockLocations) {
            removeFreezeBlockData(location);
        }
        worldFreezeBlockLocations.remove(worldUUID);

    }

    public static void removeOnChunk(Entity entity) {
        Location blockLocation = entity.getLocation().toBlockLocation();
        NewFreezeBlockLocation freezeBlockLocation = new NewFreezeBlockLocation(blockLocation);

        if (freezeBlockDataMap.containsKey(freezeBlockLocation)) {
            removeFreezeBlockData(freezeBlockLocation);
            return;
        }

        if (!entity.getPersistentDataContainer().has(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING)) {
            return;
        }

        UUID playerUUID = UUID.fromString(entity.getPersistentDataContainer().get(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING));

        if (!playerFreezeBlockLocations.isEmpty() && playerFreezeBlockLocations.containsKey(playerUUID)) {
            if (playerFreezeBlockLocations.get(playerUUID).contains(freezeBlockLocation)) {
                removeFreezeBlockData(freezeBlockLocation);
                return;
            }
        }

        Block block = blockLocation.getBlock();
        if (block.getType() == Material.BARRIER) {
            block.setType(Material.AIR, false);
        }
        if (entity.getType() == EntityType.BLOCK_DISPLAY || entity.getType() == EntityType.ITEM_DISPLAY) {
            entity.remove();
        }

    }

    public static void clearPlayerFreezeBlock(UUID playerUUID) {
        if (!playerFreezeBlockLocations.containsKey(playerUUID)) {
            return;
        }


        HashSet<NewFreezeBlockLocation> freezeBlockLocations = playerFreezeBlockLocations.get(playerUUID);

        if (freezeBlockLocations == null || freezeBlockLocations.isEmpty()) {
            return;
        }

        for (NewFreezeBlockLocation location : freezeBlockLocations) {
            removeOneFreezeBlock(playerUUID, location.getLocation().getBlock());
        }
        playerFreezeBlockLocations.remove(playerUUID);
    }

    public static void clearOnServerClose() {
        for (NewFreezeBlockLocation location: freezeBlockDataMap.keySet()) {
            removeFreezeBlockData(location);
            freezeBlockDataMap.remove(location);
        }
    }

    public static boolean isFreezeBlock(Block block) {
        NewFreezeBlockLocation location = new NewFreezeBlockLocation(block.getLocation());
        return freezeBlockDataMap.containsKey(location);
    }

    private static void removeFreezeBlockData(NewFreezeBlockLocation location) {

        if (!freezeBlockDataMap.containsKey(location)) {
            return;
        }

        FreezeBlockData data = freezeBlockDataMap.get(location);
        data.getBlockDisplay().remove();
        data.getItemDisplay().remove();

        Block block = location.getLocation().getBlock();

        block.setBlockData(Bukkit.createBlockData(data.getBlockString()), false);
        block.getState().update();

        if (location.hasPlayer()) {
            playerFreezeBlockLocations.get(location.getPlayerUUID()).remove(location);
        }

        freezeBlockDataMap.remove(location);
    }

    private static FreezeBlockData freezeBlockGenerator(NewFreezeBlockLocation freezeBlockLocation, UUID playerUUID) {

        Location location = freezeBlockLocation.getLocation();
        Block block = location.getBlock();

        // spawn item display
        Location entityLocation = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);
        Entity entity = location.getWorld().spawnEntity(entityLocation, EntityType.ITEM_DISPLAY);
        ItemDisplay itemDisplay = (ItemDisplay) entity;
        ItemStack itemStack = new ItemStack(Material.TINTED_GLASS, 1);
        itemDisplay.setItemStack(itemStack);

        Transformation transformation = itemDisplay.getTransformation();
        transformation.getScale().set(1.0001F);
        itemDisplay.setTransformation(transformation);

        entity.setGlowing(true);
        entity.setInvulnerable(true);

        // spawn block display
        Location offsetLocation = SpecialBlockLocationOffsetFilter.filter(block.getType(), location);

        Entity blockEntity = location.getWorld().spawnEntity(offsetLocation, EntityType.BLOCK_DISPLAY);
        BlockDisplay blockDisplay = (BlockDisplay) blockEntity;
        blockDisplay.setBlock(block.getBlockData());
        blockEntity.setInvulnerable(true);

        entity.getPersistentDataContainer().set(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING, playerUUID.toString());
        blockEntity.getPersistentDataContainer().set(PersistentKeys.FREEZE_BLOCK_DISPLAY, PersistentDataType.STRING, playerUUID.toString());

        FreezeBlockData freezeBlockData = new FreezeBlockData(entity, blockDisplay, block);

        // place barrier
        block.setType(Material.BARRIER, false);
        block.getState().update();

        return freezeBlockData;
    }

    public static int getFreezeBlockCount(UUID playerUUID) {
        if (!playerFreezeBlockLocations.containsKey(playerUUID)) {
            return 0;
        }
        return playerFreezeBlockLocations.get(playerUUID).size();
    }
}
