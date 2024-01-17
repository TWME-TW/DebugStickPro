package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.blockdatautil.BlockDataSeparater;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        Block block;
        Player player = event.getPlayer();


        player.sendMessage("1");


        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        player.sendMessage("2");


        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }


        //TODO: event.setCancelled(true);

        block = event.getClickedBlock();

        player.sendMessage("3");

        player.sendMessage("BlockData: " + block.getBlockData().toString());

        BlockDataSeparater.Separate(block);
    }
}
