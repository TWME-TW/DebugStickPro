package dev.twme.debugstickpro.util.actionbar;

import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

public class CheckBlockCanUseUtil {
    public static boolean check(BlockData blockData) {
        if (blockData == null){
            return false;
        }
        return true;
    }
    public static boolean check(Block block) {
        if (block == null){
            return false;
        }
        return check(block.getBlockData());
    }
}
