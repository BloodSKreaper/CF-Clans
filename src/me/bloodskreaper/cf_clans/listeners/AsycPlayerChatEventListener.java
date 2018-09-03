package me.bloodskreaper.cf_clans.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class AsycPlayerChatEventListener implements Listener {

	@EventHandler
	public void onAsyncChatEvent(AsyncPlayerChatEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (!e.getMessage().startsWith("!")) {
			return;
		}
		Clan clan = CF_Clans.getClanManager().getClanOfMember(e.getPlayer().getUniqueId());
		if (clan != null) {
			e.setCancelled(true);
			clan.sendClanChat(e.getPlayer(), e.getMessage().substring(1));
		}

	}

}
