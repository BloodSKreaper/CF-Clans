package me.bloodskreaper.clans.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.bloodskreaper.clans.Main;

public class AsycPlayerChatEventListener implements Listener{
	
	@EventHandler
	public void onAsyncChatEvent(AsyncPlayerChatEvent e) {
		if(!e.isCancelled()) {
			if(e.getMessage().startsWith("!")) {
				if(Main.getClanManager().getClanOfMember(e.getPlayer().getUniqueId())!=null) {
					e.setCancelled(true);
					Main.getClanManager().getClanOfMember(e.getPlayer().getUniqueId()).sendClanChat(e.getPlayer(), e.getMessage().substring(1));
				}
			}
		}
	}

}
