package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.Log;
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


        Log.info("1");


        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Log.info("2");


        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }


        //TODO: event.setCancelled(true);

        block = event.getClickedBlock();

        Log.info("3");

        player.sendMessage("BlockData: " + block.getBlockData().toString());

        BlockDataSeparater.Separate(block);
    }
}
