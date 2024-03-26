package dev.twme.debugstickpro.mode.copy;

import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;

import java.util.List;
import java.util.UUID;

public class CopyActionBarDisplay {
    public static String getDisplay(UUID playerUUID) {

        PlayerData playerData = PlayerDataManager.getPlayerData(playerUUID);
        List<SubBlockData> copiedSubBlockData = playerData.getCopiedSubBlockData();

        if (copiedSubBlockData.isEmpty()) {
            return I18n.str(playerUUID, Lang.Tips.copyModeIntroduction);
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (SubBlockData subBlockData : copiedSubBlockData) {
            stringBuilder.append(Lang.ActionBar.formatCopiedBlockData(I18n.str(playerUUID, Lang.ActionBar.CopiedBlockDataFormat),
                    I18n.str(playerUUID, subBlockData.dataName()), subBlockData.getDataAsString().toLowerCase())).append(" ");
        }
        return stringBuilder.toString();
    }
}
