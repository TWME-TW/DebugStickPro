package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.subdata.SubBlockData;
import dev.twme.debugstickpro.configs.LangFile;
import dev.twme.debugstickpro.playerdata.NewPlayerData;
import dev.twme.debugstickpro.playerdata.NewPlayerDataManager;

import java.util.ArrayList;
import java.util.UUID;

public class CopyActionBarDisplay {
    public static String getDisplay(UUID playerUUID) {

        NewPlayerData playerData = NewPlayerDataManager.getPlayerData(playerUUID);
        ArrayList<SubBlockData> copiedSubBlockData = playerData.getCopiedSubBlockData();

        if (copiedSubBlockData.isEmpty()) {
            return LangFile.Tips.copyModeIntroduction;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (SubBlockData subBlockData : copiedSubBlockData) {
            stringBuilder.append("<b><red>").append(subBlockData.dataName()).append(": ").append("</red></b>").append(subBlockData.getDataAsString().toLowerCase()).append(" ");
        }
        return stringBuilder.toString();
    }
}
