package me.bloodskreaper.clans.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import me.bloodskreaper.clans.Main;

public class PlayerJoinEventListener implements Listener{
	Plugin pl;
	public PlayerJoinEventListener(Plugin plugin) {
		pl=plugin;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		if(Main.getInviteManager().isInvited(p.getUniqueId())) {
			Bukkit.getScheduler().runTaskLaterAsynchronously(pl, new Runnable() {
				
				public void run() {
					if(p != null) {
						Main.sendMessageToPlayer(p, "§aDu hast Clan-Einladungen! Nimm diese mit §b/clan acceptinvite <CLANNAME> §aan, oder lehne diese mit §b/clan denyinvite <CLANNAME> §aab!");
					}
				}
			}, 20*3L);
		}
	}
}
