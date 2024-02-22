package dev.twme.debugstickpro.playerdata;

import dev.twme.debugstickpro.FreezeBlockUtil.FreezeBlockManager;
import dev.twme.debugstickpro.events.DebugStickChangeBlockEvent;
import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.subdata.SubBlockData;
import dev.twme.debugstickpro.util.Log;
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

    private ArrayList<Block> freezeBlock = new ArrayList<>();

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

        } else {
            return freezeDisplay();
        }
    }

    public void changeSelected() {
        if (ModeSelection){
            changeModeSelected();
            return;
        }


        if (debugStickMode == DebugStickMode.Classic) {
            if (this.displaySubBlockData == null){
                return;
            }
            changeSelectedClassic();
            return;
        } else if (debugStickMode == DebugStickMode.Copy) {
            if (this.displaySubBlockData == null){
                return;
            }
            this.storedSubBlockData = BlockDataSeparater.Separate(blockData);
            return;
        }  else { // Freeze mode
            // TODO: Something
            changeSelectedFreeze();
        }
    }

    public void changeValue() {
        if (ModeSelection){
            changeValueMode();
            return;
        }


        if (debugStickMode == DebugStickMode.Classic) {
            if (this.displaySubBlockData == null){
                return;
            }
            changeValueClassic();
        } else if (debugStickMode == DebugStickMode.Copy) {
            if (this.displaySubBlockData == null){
                return;
            }
            changeValueCopy();
        } else { // Freeze mode

            changeValueFreeze();
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

    private String copyDisplay() {
        StringBuilder stringBuilder = new StringBuilder();
        if (storedSubBlockData == null || storedSubBlockData.isEmpty()) {
            return "<dark_gray>You need to select a block first</dark_gray>";
        }
        for (SubBlockData subBlockData : storedSubBlockData) {
            stringBuilder.append("<b><red>").append(subBlockData.dataName()).append(": ").append("</red></b>").append(subBlockData.getDataAsString().toLowerCase()).append(" ");
        }
        return stringBuilder.toString();
    }

    private String freezeDisplay() {

        return "<dark_gray>Freeze mode, right click f</dark_gray>";
    }

    private String modeChangeDisplay() {
        StringBuilder stringBuilder1 = new StringBuilder();
        if (debugStickMode == DebugStickMode.Classic) {
            stringBuilder1.append("<b><red>").append(debugStickMode.name()).append("</red></b>").append(" ");
        } else {
            stringBuilder1.append(DebugStickMode.Classic.name()).append(" ");
        }

        if (debugStickMode == DebugStickMode.Copy) {
            stringBuilder1.append("<b><red>").append(debugStickMode.name()).append("</red></b>").append(" ");
        } else {
            stringBuilder1.append(DebugStickMode.Copy.name()).append(" ");
        }

        if (debugStickMode == DebugStickMode.Freeze) {
            stringBuilder1.append("<b><red>").append(debugStickMode.name()).append("</red></b>").append(" ");
        } else {
            stringBuilder1.append(DebugStickMode.Freeze.name()).append(" ");
        }

        return stringBuilder1.toString();
    }

    private void changeModeSelected() {
        this.storedSubBlockData = null;
        if (debugStickMode == DebugStickMode.Classic) {
            debugStickMode = DebugStickMode.Copy;
        } else if (debugStickMode == DebugStickMode.Copy) {
            debugStickMode = DebugStickMode.Freeze;
        } else {
            debugStickMode = DebugStickMode.Classic;
        }
    }

    private void changeSelectedClassic() {
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
    private void changeSelectedFreeze() {
        FreezeBlockManager.removeAllBlock(playerUUID);
    }

    private void changeValueMode() {
        ModeSelection = false;
    }
    private void changeValueCopy() {
        ArrayList<SubBlockData> subBlockDatas = BlockDataSeparater.Separate(block);
        for (SubBlockData subBlockData : subBlockDatas) {
            for (SubBlockData storedSubBlockData : storedSubBlockData) {
                if (subBlockData.dataName().equals(storedSubBlockData.dataName())) {

                    DebugStickChangeBlockEvent event = new DebugStickChangeBlockEvent(playerUUID, block, subBlockData);
                    if (event.isCancelled()){
                        return;
                    }
                    this.block.setBlockData(storedSubBlockData.copyTo(subBlockData.getBlockData()), false);
                    this.block.getState().update();
                    break;
                }
            }
        }

    }

    private void changeValueClassic() {
        for (SubBlockData subBlockData : displaySubBlockData) {
            if (subBlockData.isUsing()) {
                subBlockData.nextData();
                subBlockData.getBlockData();
                DebugStickChangeBlockEvent event = new DebugStickChangeBlockEvent(playerUUID, block, subBlockData);
                if (event.isCancelled()){
                    return;
                }
                this.block.setBlockData(subBlockData.getBlockData(), false);
                this.block.getState().update();
                break;
            }
        }
    }

    private void changeValueFreeze() {
        if (FreezeBlockManager.isFreezeBlock(block.getLocation())) {
            
            FreezeBlockManager.removeBlock(playerUUID, block);
        } else {

            FreezeBlockManager.addBlock(playerUUID, block);
        }
    }

}
