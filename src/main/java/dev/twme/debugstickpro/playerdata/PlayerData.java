package dev.twme.debugstickpro.playerdata;

import dev.twme.debugstickpro.blockdatautil.subdata.SubBlockData;

import java.util.ArrayList;

public class PlayerData {

    /* 儲存的 SubBlockData 類別 */
    private String selectedSubBlockDataType = null;

    /* 用於顯示方塊資料 */
    private ArrayList<SubBlockData> copiedSubBlockData = new ArrayList<>();

    /* 除錯棒模式，預設為 Classic */
    private DebugStickMode debugStickMode = DebugStickMode.Classic;

    /*
     * 設置被選到的 SubBlockData 類型
     *
     * @param selectedSubBlockDataType 被選到的 SubBlockData 類型
     * @return PlayerData 本身
     */
    public PlayerData setSelectedSubBlockDayaType(String selectedSubBlockDataType) {
        this.selectedSubBlockDataType = selectedSubBlockDataType;
        return this;
    }

    // 設置該玩家在複製模式下複製的方塊資料
    public PlayerData setCopiedSubBlockData(ArrayList<SubBlockData> copiedSubBlockData) {
        this.copiedSubBlockData = copiedSubBlockData;
        return this;
    }

    // 設置玩家的除錯棒模式
    public PlayerData setDebugStickMode(DebugStickMode debugStickMode) {
        this.debugStickMode = debugStickMode;
        return this;
    }

    // 獲取玩家選鑿的
    public String getSelectedSubBlockDataType() {
        return selectedSubBlockDataType;
    }

    public ArrayList<SubBlockData> getCopiedSubBlockData() {
        return copiedSubBlockData;
    }

    public DebugStickMode getDebugStickMode() {
        return debugStickMode;
    }





}
