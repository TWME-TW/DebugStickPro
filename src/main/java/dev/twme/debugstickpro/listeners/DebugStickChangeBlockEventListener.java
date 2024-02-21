package dev.twme.debugstickpro.listeners;

import dev.twme.debugstickpro.events.DebugStickChangeBlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class DebugStickChangeBlockEventListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onDebugStickChangeBlockEvent(DebugStickChangeBlockEvent event) {

    }
}
