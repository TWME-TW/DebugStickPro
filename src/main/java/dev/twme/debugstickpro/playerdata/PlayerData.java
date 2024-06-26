package dev.twme.debugstickpro.playerdata;

import com.destroystokyo.paper.profile.PlayerProfile;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    /* 儲存的 SubBlockData 類別 */
    private String selectedSubBlockDataType = null;

    /* 用於顯示方塊資料 */
    private List<SubBlockData> copiedSubBlockData = new ArrayList<>();
    private PlayerProfile copiedSkullBlockPlayerProfile;

    /* 除錯棒模式，預設為 Classic */
    private DebugStickMode debugStickMode = DebugStickMode.CLASSIC;

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

    // set player copied SubBlockData
    public PlayerData setCopiedSubBlockData(ArrayList<SubBlockData> copiedSubBlockData) {
        this.copiedSubBlockData = copiedSubBlockData;
        return this;
    }

    // set player debug stick mode
    public PlayerData setDebugStickMode(DebugStickMode debugStickMode) {
        this.debugStickMode = debugStickMode;
        return this;
    }

    // get player selected SubBlockData type
    public String getSelectedSubBlockDataType() {
        return selectedSubBlockDataType;
    }

    // get player copied SubBlockData
    public List<SubBlockData> getCopiedSubBlockData() {
        return copiedSubBlockData;
    }

    // get player debug stick mode
    public DebugStickMode getDebugStickMode() {
        return debugStickMode;
    }

    public PlayerData setCopiedSkullBlockPlayerProfile(PlayerProfile copiedSkullBlockPlayerProfile) {
        this.copiedSkullBlockPlayerProfile = copiedSkullBlockPlayerProfile;
        return this;
    }

    public PlayerProfile getCopiedSkullBlockPlayerProfile() {
        return copiedSkullBlockPlayerProfile;
    }

    public PlayerData removePlayerProfile() {
        this.copiedSkullBlockPlayerProfile = null;
        return this;
    }

}
