package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.util.DebugStickItemCheck;
import dev.twme.debugstickpro.util.Log;
import dev.twme.debugstickpro.util.PersistentKey;
import dev.twme.debugstickpro.util.blockutil.blockdatautil.BlockDataSeparater;
import dev.twme.debugstickpro.util.player.playerdata.PlayerDataManager;
import dev.twme.debugstickpro.util.player.playerdata.util.PlayerData;
import net.kyori.adventure.text.Component;
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

        if (!DebugStickItemCheck.checkPlayer(player)) {
            return;
        }

        event.setCancelled(true);

        block = event.getClickedBlock();

        player.sendMessage("1: " + block.getBlockData().getAsString());
        player.sendMessage("2: true  " + block.getBlockData().getAsString(true));
        player.sendMessage("3: false " + block.getBlockData().getAsString(false));

        PlayerData playerData = new PlayerData(player.getUniqueId());

    }
}
