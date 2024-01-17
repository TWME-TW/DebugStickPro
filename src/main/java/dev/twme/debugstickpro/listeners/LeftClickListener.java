package dev.twme.debugstickpro.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickListener implements Listener {
    public void onLeftClickEvent(PlayerInteractEvent event){
        if (!event.getAction().isLeftClick()){
            return;
        }

    }
}
