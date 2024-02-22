package dev.twme.debugstickpro.FreezeBlockUtil;

import org.bukkit.block.Block;

import org.bukkit.entity.Entity;

public class FreezeBlockData {
    private Entity entity;
    private Block block;
    private String blockString;

    public FreezeBlockData(Entity entity, Block block) {
        this.entity = entity;
        this.block = block;
        this.blockString = block.getBlockData().getAsString();
    }

    public Entity getEntity() {
        return entity;
    }

    public String getBlockString() {
        return blockString;
    }
    public Block getBlock(){
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FreezeBlockData)) return false;

        FreezeBlockData thisData = (FreezeBlockData) o;
        if (!thisData.getEntity().equals(this.getEntity())) {
            return false;
        }

        if (! thisData.getBlock().getLocation().equals(this.getBlock().getLocation())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = entity.hashCode();
        result = 31 * result + block.hashCode();
        return result;
    }
}
