package dev.twme.debugstickpro.blockdatautil.subdata.redstonewire;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.RedstoneWire;

import java.util.List;

public abstract class RedstoneWireData extends SubBlockData {
    private RedstoneWire.Connection connection;
    private final BlockFace face;

    private final static List<RedstoneWire.Connection> connections = List.of(
            RedstoneWire.Connection.NONE,
            RedstoneWire.Connection.SIDE,
            RedstoneWire.Connection.UP
    );

    public RedstoneWireData(BlockData blockData, BlockFace face) {
        this.blockData = blockData;
        this.face = face;
        this.connection = ((RedstoneWire) blockData).getFace(face);
    }

    @Override
    public String getDataAsString() {
        return connection.name();
    }

    @Override
    public SubBlockData nextData() {
        RedstoneWire redstoneWire = (RedstoneWire) blockData;
        connection = connections.get((connections.indexOf(connection) + 1) % connections.size());
        redstoneWire.setFace(face, connection);
        this.blockData = redstoneWire;
        return this;
    }

    @Override
    public SubBlockData previousData() {
        RedstoneWire redstoneWire = (RedstoneWire) blockData;
        connection = connections.get((connections.indexOf(connection) + 2) % connections.size());
        redstoneWire.setFace(face, connection);
        this.blockData = redstoneWire;
        return this;
    }

    @Override
    public BlockData copyTo(BlockData blockData) {
        ((RedstoneWire) blockData).setFace(face, connection);
        return blockData;
    }
}
