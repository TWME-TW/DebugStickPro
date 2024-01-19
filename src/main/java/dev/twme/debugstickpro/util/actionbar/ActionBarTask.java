package dev.twme.debugstickpro.util.actionbar;

import dev.twme.debugstickpro.util.blockutil.BlockUtil;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDatas;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ActionBarTask implements Runnable {
    public static ArrayList<UUID> playerList = new ArrayList<>();

    @Override
    public void run() {
        for (UUID uuid : playerList) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null){
                return;
            }
            if (player.getTargetBlockExact(5) != null) {
                Block block = player.getTargetBlockExact(5);
                BlockData blockData = null;

                if (block != null) {
                    blockData = block.getBlockData();
                }
                if (blockData != null) {
                    if (BlockUtil.contains(uuid) && blockData.equals(BlockUtil.get(uuid).getBlockData())){
                        player.sendActionBar(Component.text(BlockUtil.get(uuid).getAsString()));
                    }
                    player.sendActionBar(Component.text("Test: " + new BlockDatas(block).getAsString()));
                }
            } else {
                player.sendActionBar(Component.text(" "));
            }
        }
    }
}
