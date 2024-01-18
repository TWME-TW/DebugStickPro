package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.Log;
import dev.twme.debugstickpro.util.PersistentKey;
import dev.twme.debugstickpro.util.blockutil.BlockUtil;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDataSeparater;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;

public class RightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

        Block block;
        Player player = event.getPlayer();

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        if (!player.hasPermission("debugstickpro.use")) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getType() != Material.BLAZE_ROD) {
            return;
        }
        PersistentDataContainer playerHandItemData = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer();
        if (!playerHandItemData.has(PersistentKey.DEBUG_STICK_ITEM)){
            return;
        }
        event.setCancelled(true);

        block = event.getClickedBlock();

        Log.info("3");

        player.sendMessage("BlockData: " + block.getBlockData().toString());

        BlockUtil.add(player.getUniqueId(), block.getBlockData());
    }
}
