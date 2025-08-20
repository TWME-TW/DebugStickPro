package dev.twme.debugstickpro.utils;

import dev.twme.debugstickpro.DebugStickPro;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SendFakeBarrier {
    public static void sendFakeBarrier(Player player, Location location) {
        BlockData blockData = Material.BARRIER.createBlockData();
        if (player != null) {
            player.sendBlockChange(location, blockData);
            Bukkit.getScheduler().runTaskLater(DebugStickPro.getInstance(), () -> {
                // player.sendBlockChange(location, blockData);
                FakeBlockAPI.getInstance().setBlock(player, location, blockData);
            }, 1L);
        }
    }

    public static void sendFakeBarrier(UUID playerUUID, Location location) {
        Player player = org.bukkit.Bukkit.getPlayer(playerUUID);
        sendFakeBarrier(player, location);
    }

    public static void sendFakeBarrierToAllPlayers(Location location) {
        BlockData blockData = Material.BARRIER.createBlockData();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendBlockChange(location, blockData);
            Bukkit.getScheduler().runTaskLater(DebugStickPro.getInstance(), () -> {
                // player.sendBlockChange(location, blockData);
                FakeBlockAPI.getInstance().setBlock(player, location, blockData);
            }, 1L);
        }
    }
}
