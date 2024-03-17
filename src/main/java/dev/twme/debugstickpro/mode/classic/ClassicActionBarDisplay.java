package dev.twme.debugstickpro.mode.classic;

import dev.twme.debugstickpro.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.blockdatautil.SubBlockData;
import dev.twme.debugstickpro.configs.ConfigFile;
import dev.twme.debugstickpro.configs.LangFile;
import dev.twme.debugstickpro.playerdata.PlayerData;
import dev.twme.debugstickpro.playerdata.PlayerDataManager;
import org.bukkit.block.data.BlockData;

import java.util.ArrayList;
import java.util.UUID;

public class ClassicActionBarDisplay {
    public static String getDisplay(UUID playerUUID, BlockData blockData) {

        // 用於確認是否有任何 SubBlockData 類型被使用的變數
        boolean hasIsUsingType = false;

        // 獲取玩家資料
        PlayerData playerData = PlayerDataManager.getPlayerData(playerUUID);

        // 獲取方塊拆分後的資料
        ArrayList<SubBlockData> displayList = BlockDataSeparater.separate(blockData);

        // TODO: 需檢查這邊的返回是否是冗於
        if (displayList.isEmpty()) {
            return " ";
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
        if (ConfigFile.ActionBarDisplay.AutoToCenter) {
            sort = selectedIndex - displayList.size()/2 + 1 + displayList.size() - (displayList.size() % 2);
        }

        // 排序顯示順序
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < displayList.size(); i++) {
            SubBlockData subBlockData = displayList.get((i + sort) % displayList.size());
            if (subBlockData.isUsing()) {
                stringBuilder.append(LangFile.ActionBar.formatSelectedData(subBlockData.dataName(), subBlockData.getDataAsString().toLowerCase())).append(" ");
            } else {
                stringBuilder.append(LangFile.ActionBar.formatNotSelectedData(subBlockData.dataName(), subBlockData.getDataAsString().toLowerCase())).append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
