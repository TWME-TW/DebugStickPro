package dev.twme.debugstickpro.util.player.playerdata;

import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.subdata.SubBlockData;
import org.bukkit.Location;
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
    private DebugStickMode oldDebugStickMode;
    private UUID playerUUID;
    private BlockData blockData;

    public PlayerData(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.debugStickMode = DebugStickMode.Classic;
        this.oldDebugStickMode = DebugStickMode.Classic;
    }


    public PlayerData setPlayerMode(DebugStickMode debugStickMode) {
        this.oldDebugStickMode = this.debugStickMode;
        this.debugStickMode = debugStickMode;
        return this;
    }

    public DebugStickMode getPlayerMode() {
        return debugStickMode;
    }
    public DebugStickMode getOldPlayerMode() {
        return oldDebugStickMode;
    }

    public PlayerData setDisplaySubBlockData(Block block) {
        this.block = block;
        this.blockData = block.getBlockData();
        this.displaySubBlockData = BlockDataSeparater.Separate(blockData);
        return this;
    }

    public PlayerData removeDisplaySubBlockData(){
        this.displaySubBlockData = null;

        return this;
    }

    public String getDisplaySubBlockData() {
        StringBuilder stringBuilder = new StringBuilder();
        if (debugStickMode == DebugStickMode.Classic) {

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
                    if (hasUsing == false) {
                        displaySubBlockData.get(0).setIsUsing(true);
                    }
                }

                if (displaySubBlockData.get(i).isUsing() == true) {
                    stringBuilder.append("<b><gold><u>" + subBlockData.dataName() + ": " + "</u></gold></b>" + subBlockData.getDataAsString().toLowerCase() + " ");
                } else {
                    stringBuilder.append("<b><gold>" + subBlockData.dataName() + ": " + "</gold></b>" + subBlockData.getDataAsString().toLowerCase() + " ");
                }
            }

            return stringBuilder.toString();

        } else if (debugStickMode == DebugStickMode.Copy) {

            for (SubBlockData subBlockData : storedSubBlockData) {
                stringBuilder.append("<b><red>").append(subBlockData.dataName()).append(": ").append("</red></b>").append(subBlockData.getDataAsString().toLowerCase()).append(" ");
            }

        } else if (debugStickMode == DebugStickMode.Freeze) {


        } else if (debugStickMode == DebugStickMode.ModeChange) {

            StringBuilder stringBuilder1 = new StringBuilder();
            for (DebugStickMode debugStickMode : DebugStickMode.values()) {
                if (debugStickMode.equals(this.oldDebugStickMode)){
                    stringBuilder1.append("<b><red>").append(debugStickMode.name()).append("</red></b>").append(" ");
                } else {
                    stringBuilder1.append("a" + debugStickMode.name()).append(" ");
                }
                return stringBuilder1.toString();
            }
        }
        return displaySubBlockData.toString();
    }

    public void changeSelected(){
        if (debugStickMode == DebugStickMode.Classic ) {
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
                    this.block.setBlockData(subBlockData.getBlockData(),false);
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
}
