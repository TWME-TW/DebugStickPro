package dev.twme.debugstickpro.playerdata;

import com.destroystokyo.paper.profile.PlayerProfile;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store the player data
 */
public class PlayerData {

    /**
     * a string to store the selected SubBlockData type
     */
    private String selectedSubBlockDataType = null;

    /**
     * the copied SubBlockData
     */
    private List<SubBlockData> copiedSubBlockData = new ArrayList<>();

    /**
     * the copied skull block player profile
     */
    private PlayerProfile copiedSkullBlockPlayerProfile;

    /**
     * the debug stick mode, default is CLASSIC
     */
    private DebugStickMode debugStickMode = DebugStickMode.CLASSIC;

    /**
     * set player selected SubBlockData type
     *
     * @param selectedSubBlockDataType selected SubBlockData type
     * @return PlayerData player data
     */
    public PlayerData setSelectedSubBlockDayaType(String selectedSubBlockDataType) {
        this.selectedSubBlockDataType = selectedSubBlockDataType;
        return this;
    }

    /**
     * set player copied SubBlockData
     * @param copiedSubBlockData copied SubBlockData
     * @return PlayerData player data
     */
    public PlayerData setCopiedSubBlockData(ArrayList<SubBlockData> copiedSubBlockData) {
        this.copiedSubBlockData = copiedSubBlockData;
        return this;
    }

    /**
     * set player debug stick mode
     * @param debugStickMode debug stick mode
     * @return PlayerData player data
     */
    public PlayerData setDebugStickMode(DebugStickMode debugStickMode) {
        this.debugStickMode = debugStickMode;
        return this;
    }

    /**
     * get player selected SubBlockData type
     * @return selected SubBlockData type
     */
    public String getSelectedSubBlockDataType() {
        return selectedSubBlockDataType;
    }

    /**
     * get player copied SubBlockData
     * @return copied SubBlockData
     */
    public List<SubBlockData> getCopiedSubBlockData() {
        return copiedSubBlockData;
    }

    /**
     * get player debug stick mode
     * @return debug stick mode
     */
    public DebugStickMode getDebugStickMode() {
        return debugStickMode;
    }

    /**
     * get player copied skull block player profile
     * @return copied skull block player profile
     */
    public PlayerData setCopiedSkullBlockPlayerProfile(PlayerProfile copiedSkullBlockPlayerProfile) {
        this.copiedSkullBlockPlayerProfile = copiedSkullBlockPlayerProfile;
        return this;
    }

    /**
     * get player copied skull block player profile
     * @return copied skull block player profile
     */
    public PlayerProfile getCopiedSkullBlockPlayerProfile() {
        return copiedSkullBlockPlayerProfile;
    }

    /**
     * remove copied skull block player profile
     * @return player data
     */
    public PlayerData removePlayerProfile() {
        this.copiedSkullBlockPlayerProfile = null;
        return this;
    }

}
