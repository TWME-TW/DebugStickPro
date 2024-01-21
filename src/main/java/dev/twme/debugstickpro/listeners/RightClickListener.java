package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.Log;
import dev.twme.debugstickpro.util.PersistentKey;
import dev.twme.debugstickpro.util.blockutil.BlockUtil;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDataSeparater;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RightClickListener implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

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

        player.sendMessage("BlockData: " + block.getBlockData().toString());
        Jukebox jukebox = (Jukebox) block.getBlockData();
        if (!((Jukebox)block.getBlockData()).hasRecord()){
            ((org.bukkit.block.Jukebox)block.getState()).getInventory().setRecord(null);
            block.getState().update();
        } //else if (((org.bukkit.block.Jukebox)block.getState()).getInventory().getRecord().getType() == Material.MUSIC_DISC_CAT){
        //    ((org.bukkit.block.Jukebox)block.getState()).getInventory().setRecord(new ItemStack(Material.AIR));
        //} else {
        //    ((org.bukkit.block.Jukebox)block.getState()).getInventory().setRecord(null);
        //}



        //if ((((org.bukkit.block.Jukebox) block.getState()).isPlaying())){
        //    ((org.bukkit.block.Jukebox) block.getState()).setPlaying(Material.AIR);
        //} else {
        //    ((org.bukkit.block.Jukebox) block.getState()).stopPlaying();
        //}
        player.sendMessage("JukeboxInventory: " + ((org.bukkit.block.Jukebox)block.getState()).getInventory().getRecord());

        BlockUtil.add(player.getUniqueId(), block);
    }
}
