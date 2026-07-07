package dev.twme.debugstickpro.mode.classic;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.config.ConfigFile;
import dev.twme.debugstickpro.localization.I18n;
import dev.twme.debugstickpro.localization.Lang;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.UUID;

public class ClassicActionBarDisplay {
    public static String getDisplay(UUID playerUUID, BlockData blockData) {
        if (blockData == null) {
            return I18n.string(playerUUID, Lang.Tips.classicModeIntroduction);
        }

        // 用於確認是否有任何 SubBlockData 類型被使用的變數
        boolean hasIsUsingType = false;

        // 獲取玩家資料
        PlayerData playerData = PlayerDataManager.getOrCreatePlayerData(playerUUID);

        // 獲取方塊拆分後的資料
        ArrayList<SubBlockData> displayList = BlockDataSeparater.separate(blockData, playerUUID);

        if (displayList.isEmpty()) {
            return I18n.string(playerUUID, Lang.Tips.classicModeIntroduction);
        }

        int selectedIndex;

        // 將與玩家原本相同類型的 SubBlockData 類型設為 True
        for (selectedIndex = 0; selectedIndex < displayList.size(); selectedIndex++) {
            if (displayList.get(selectedIndex).name().equals(playerData.getSelectedSubBlockDataType())) {
                displayList.get(selectedIndex).setIsUsing(true);
                hasIsUsingType = true;
                break;
            }
        }

        // 如果沒有已經使用的類型，則將第一個設為使用的類型
        if (!hasIsUsingType) {
            displayList.get(0).setIsUsing(true);
            selectedIndex = 0;
        }

        int sort = 0;
        int displayListSize = displayList.size();
        if (ConfigFile.ActionBarDisplay.AutoToCenter) {
            sort = selectedIndex - displayListSize / 2 + 1 + displayListSize - (displayListSize % 2);
        }

        // 排序顯示順序
        StringBuilder stringBuilder = new StringBuilder(displayListSize * 32);
        String selectedDataFormat = I18n.string(playerUUID, Lang.ActionBar.SelectedDataFormat);
        String notSelectedDataFormat = I18n.string(playerUUID, Lang.ActionBar.NotSelectedDataFormat);

        for (int i = 0; i < displayListSize; i++) {
            SubBlockData subBlockData = displayList.get((i + sort) % displayListSize);
            String value = I18n.blockDataValue(playerUUID, subBlockData);
            String dataName = I18n.string(playerUUID, subBlockData.dataName());
            if (subBlockData.isUsing()) {
                stringBuilder.append(Lang.ActionBar.formatSelectedData(selectedDataFormat, dataName, value)).append(" ");
            } else {
                stringBuilder.append(Lang.ActionBar.formatNotSelectedData(notSelectedDataFormat, dataName, value)).append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
