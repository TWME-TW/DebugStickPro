package dev.twme.debugstickpro.util.player.playerdata.util;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.SubBlockData;
import dev.twme.debugstickpro.util.player.playerdata.util.DebugStickMode;

public class PlayerData {
    private SubBlockData subBlockData;
    private DebugStickMode debugStickMode;
    public PlayerData() {
        this.subBlockData = null;
        this.debugStickMode = DebugStickMode.Classic;
    }
    public PlayerData(SubBlockData subBlockData) {
        this.subBlockData = subBlockData;
    }
    public PlayerData(SubBlockData subBlockData, DebugStickMode debugStickMode) {
        this.subBlockData = subBlockData;
        this.debugStickMode = debugStickMode;
    }
    public PlayerData(DebugStickMode debugStickMode) {
        this.debugStickMode = debugStickMode;
    }

    public boolean isEqualSubBlockData(SubBlockData subBlockData) {
        return this.subBlockData.name().equals(subBlockData.name());
    }
    public DebugStickMode getDebugStickMode() {
        return debugStickMode;
    }
    public void setDebugStickMode(DebugStickMode debugStickMode) {
        this.debugStickMode = debugStickMode;
    }



}