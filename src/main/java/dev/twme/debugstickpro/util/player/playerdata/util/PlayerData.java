package dev.twme.debugstickpro.util.player.playerdata.util;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDatas;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.SubBlockData;
import dev.twme.debugstickpro.util.player.playerdata.util.DebugStickMode;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerData {
    private SubBlockData selectedSubBlockData;
    private BlockDatas selectedBlockDatas;
    private DebugStickMode debugStickMode;
    private UUID playerUUID;
    private BlockData blockData;

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.debugStickMode = DebugStickMode.Classic;
    }
    public PlayerData setBlockData(BlockData blockData) {
        this.blockData = blockData;
        this.selectedBlockDatas = new BlockDatas(blockData);
        return this;
    }
    public BlockData getBlockData() {
        return blockData;
    }
    private PlayerData setPlayerMode(DebugStickMode debugStickMode) {
        this.debugStickMode = debugStickMode;
        return this;
    }




}
