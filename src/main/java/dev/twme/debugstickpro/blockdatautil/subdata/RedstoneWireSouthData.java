package dev.twme.debugstickpro.blockdatautil.subdata;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.Lang;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.RedstoneWire;

public class RedstoneWireSouthData implements SubBlockData {
    private final BlockData blockData;
    private RedstoneWire.Connection connection;
    final private BlockFace face = BlockFace.SOUTH;
    private boolean isUsing = false;

    public RedstoneWireSouthData(BlockData blockData) {
        this.blockData = blockData;
        this.connection = ((RedstoneWire) blockData).getFace(face);
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String dataName() {
        return Lang.DataKeyName.RedstoneWireSouthDataName;
    }

    @Override
    public BlockData getBlockData() {
        return blockData;
    }


    @Override
    public String getDataAsString() {
        return connection.name();
    }


    @Override
    public SubBlockData setIsUsing(boolean isUsing) {
        this.isUsing = isUsing;
        return this;
    }

    @Override
    public boolean isUsing() {
        return isUsing;
    }

    public SubBlockData nextData() {
        RedstoneWire redstoneWire = ((RedstoneWire) blockData);
        connection = redstoneWire.getFace(face);
        switch (connection) {
            case NONE:
                redstoneWire.setFace(face, RedstoneWire.Connection.SIDE);
                break;
            case SIDE:
                redstoneWire.setFace(face, RedstoneWire.Connection.UP);
                break;
            case UP:
                redstoneWire.setFace(face, RedstoneWire.Connection.NONE);
                break;
        }
        connection = redstoneWire.getFace(face);
        return this;
    }

    @Override
    public SubBlockData previousData() {
        RedstoneWire redstoneWire = ((RedstoneWire) blockData);
        connection = redstoneWire.getFace(face);
        switch (connection) {
            case NONE:
                redstoneWire.setFace(face, RedstoneWire.Connection.UP);
                break;
            case SIDE:
                redstoneWire.setFace(face, RedstoneWire.Connection.NONE);
                break;
            case UP:
                redstoneWire.setFace(face, RedstoneWire.Connection.SIDE);
                break;
        }
        connection = redstoneWire.getFace(face);
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((RedstoneWire) blockData).setFace(face, connection);
        return blockData;
    }

    @Override
    public SubBlockData fromBlockData(BlockData blockData) {
        return new RedstoneWireSouthData(blockData);
    }
}
