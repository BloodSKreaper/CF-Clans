package me.bloodskreaper.cf_clans.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.bloodskreaper.cf_clans.CF_Clans;

public class AsycPlayerChatEventListener implements Listener {

	@EventHandler
	public void onAsyncChatEvent(AsyncPlayerChatEvent e) {
		if (!e.isCancelled()) {
			if (e.getMessage().startsWith("!")) {
				if (CF_Clans.getClanManager().getClanOfMember(e.getPlayer().getUniqueId()) != null) {
					e.setCancelled(true);
					CF_Clans.getClanManager().getClanOfMember(e.getPlayer().getUniqueId()).sendClanChat(e.getPlayer(),
							e.getMessage().substring(1));
				}
			}
		}
	}

}
