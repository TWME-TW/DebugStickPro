package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CopyActionBarDisplay {
    public static String getDisplay(UUID playerUUID) {

        PlayerData playerData = PlayerDataManager.getPlayerData(playerUUID);
        List<SubBlockData> copiedSubBlockData = playerData.getCopiedSubBlockData();

        if (copiedSubBlockData.isEmpty()) {
            return LangFile.Tips.copyModeIntroduction;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (SubBlockData subBlockData : copiedSubBlockData) {
            stringBuilder.append(LangFile.ActionBar.formatCopiedBlockData(subBlockData.dataName(), subBlockData.getDataAsString().toLowerCase())).append(" ");
        }
        return stringBuilder.toString();
    }
}
