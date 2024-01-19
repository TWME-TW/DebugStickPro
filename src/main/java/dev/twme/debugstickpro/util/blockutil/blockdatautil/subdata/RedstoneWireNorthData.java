package dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.RedstoneWire;

public class RedstoneWireNorthData implements SubBlockData{
    private String NAME = "RedstoneWire";
    private BlockData blockData;
    private RedstoneWire.Connection connection;
    private BlockFace face;
    public RedstoneWireNorthData(BlockData blockData) {
        this.blockData = blockData;
        this.face = BlockFace.NORTH;
    }
    @Override
    public String NAME() {
        return NAME;
    }

    @Override
    public BlockData getData() {
        return blockData;
    }

    @Override
    public BlockData getNextData() {
        nextData();
        return blockData;
    }

    @Override
    public String getAsString() {
        return "Connection: " + connection;
    }

    @Override
    public String getNextAsString() {
        return "Connection: " + connection;
    }

    @Override
    public String getDataAsString() {
        return connection.name();
    }

    @Override
    public String getNextDataAsString() {
        return connection.name();
    }

    @Override
    public void setIsUsing(boolean isUsing) {

    }
    private void nextData(){
        RedstoneWire redstoneWire = ((RedstoneWire) blockData);
        connection = redstoneWire.getFace(face);
        switch (connection){
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
    }
}
