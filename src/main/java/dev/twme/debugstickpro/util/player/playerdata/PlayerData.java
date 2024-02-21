package dev.twme.debugstickpro.util.player.playerdata;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.SubBlockData;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerData {
    Block block;
    private SubBlockData selectedSubBlockData;
    private ArrayList<SubBlockData> displaySubBlockData;
    private ArrayList<SubBlockData> storedSubBlockData;
    private DebugStickMode debugStickMode;
    private boolean ModeSelection = false;

    private UUID playerUUID;
    private BlockData blockData;

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.debugStickMode = DebugStickMode.Classic;

    }


    public PlayerData setPlayerMode(DebugStickMode debugStickMode) {
        this.debugStickMode = debugStickMode;
        return this;
    }

    public DebugStickMode getPlayerMode() {
        return debugStickMode;
    }


    public PlayerData setDisplaySubBlockData(Block block) {
        this.block = block;
        this.blockData = block.getBlockData();
        this.displaySubBlockData = BlockDataSeparater.Separate(blockData);
        return this;
    }

    public PlayerData removeDisplaySubBlockData() {
        this.displaySubBlockData = null;

        return this;
    }

    public PlayerData setModeSelection(boolean modeSelection) {
        ModeSelection = modeSelection;
        return this;
    }
    public boolean getModeSelection() {
        return ModeSelection;
    }

    public String getDisplaySubBlockData() {
        if (ModeSelection) {
            return modeChangeDisplay();
        }


        if (debugStickMode == DebugStickMode.Classic) {
            return classicDisplay();

        } else if (debugStickMode == DebugStickMode.Copy) {
            return copyDisplay();


        } else if (debugStickMode == DebugStickMode.Freeze) {


        }
        return displaySubBlockData.toString();
    }

    public void changeSelected() {
        if (debugStickMode == DebugStickMode.Classic) {
            for (int i = 0; i < displaySubBlockData.size(); i++) {
                SubBlockData subBlockData = displaySubBlockData.get(i);
                if (subBlockData.isUsing()) {
                    subBlockData.setIsUsing(false);
                    if (i == displaySubBlockData.size() - 1) {
                        displaySubBlockData.get(0).setIsUsing(true);
                        this.selectedSubBlockData = displaySubBlockData.get(0);
                    } else {
                        displaySubBlockData.get(i + 1).setIsUsing(true);
                        this.selectedSubBlockData = displaySubBlockData.get(i + 1);
                    }
                    break;
                }
            }
        }
    }

    public void changeValue() {
        if (debugStickMode == DebugStickMode.Classic) {
            for (SubBlockData subBlockData : displaySubBlockData) {
                if (subBlockData.isUsing()) {
                    subBlockData.nextData();
                    subBlockData.getBlockData();
                    this.block.setBlockData(subBlockData.getBlockData(), false);
                    this.block.getState().update();
                    break;
                }
            }

        } else if (debugStickMode == DebugStickMode.Copy) {

        }
    }

    public void setStoredSubBlockData(Block block) {
        BlockData blockData1 = block.getBlockData();
        this.storedSubBlockData = BlockDataSeparater.Separate(blockData1);
    }

    private String classicDisplay() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean hasUsing = false;
        if (displaySubBlockData == null) {
            return null;
        }

        for (int i = 0; i < displaySubBlockData.size(); i++) {

            SubBlockData subBlockData = displaySubBlockData.get(i);
            if (selectedSubBlockData == null) {
                displaySubBlockData.get(0).setIsUsing(true);
                hasUsing = true;
            } else {
                for (int j = 0; j < displaySubBlockData.size(); j++) {
                    if (displaySubBlockData.get(j).dataName().equals(selectedSubBlockData.dataName())) {
                        displaySubBlockData.get(j).setIsUsing(true);
                        hasUsing = true;
                    }
                }
                if (!hasUsing) {
                    displaySubBlockData.get(0).setIsUsing(true);
                }
            }

            if (displaySubBlockData.get(i).isUsing()) {
                stringBuilder.append("<b><gold><u>" + subBlockData.dataName() + "</u></gold></b>" + ": " + subBlockData.getDataAsString().toLowerCase() + " ");
            } else {
                stringBuilder.append("<b><gold>" + subBlockData.dataName() + ": " + "</gold></b>" + subBlockData.getDataAsString().toLowerCase() + " ");
            }
        }
        return stringBuilder.toString();
    }

    private String copyDisplay(){
        StringBuilder stringBuilder = new StringBuilder();
        for (SubBlockData subBlockData : storedSubBlockData) {
            stringBuilder.append("<b><red>").append(subBlockData.dataName()).append(": ").append("</red></b>").append(subBlockData.getDataAsString().toLowerCase()).append(" ");
        }
        return stringBuilder.toString();
    }

    private String freezeDisplay(){

        return null;
    }

    private String modeChangeDisplay() {
        StringBuilder stringBuilder1 = new StringBuilder();
        for (DebugStickMode debugStickMode : DebugStickMode.values()) {
            if (debugStickMode.equals(this.debugStickMode)) {
                stringBuilder1.append("<b><red>").append(debugStickMode.name()).append("</red></b>").append(" ");
            } else {
                stringBuilder1.append(debugStickMode.name()).append(" ");
            }
        }
        return stringBuilder1.toString();
    }

}
