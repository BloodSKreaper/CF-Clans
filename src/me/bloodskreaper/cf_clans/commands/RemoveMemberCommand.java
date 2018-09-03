package me.bloodskreaper.cf_clans.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class RemoveMemberCommand implements CommandInterface {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§cDu musst einen Namen angeben! §b/clan removemember <NAME>");
			return false;
		}
		if (args.length > 2) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! §b/clan removemember <NAME>");
			return false;
		}
		Clan clan = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
		if (clan == null) {
			CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
			return false;
		}
		OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
		if (!clan.getMembers().contains(target.getUniqueId())) {
			CF_Clans.sendMessageToPlayer(p, "§cDer Spieler ist nicht in deinem Clan!");
			return false;
		}
		if (clan.getLeader() != p.getUniqueId()) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDu bist nicht der Admin des Clans und kannst somit keine Member entfernen!");
			return false;
		}
		clan.removeMember(target.getUniqueId());
		if (Bukkit.getPlayer(args[1]) != null)
			CF_Clans.sendMessageToPlayer(Bukkit.getPlayer(args[1]),
					"§cDu wurdest aus dem Clan §6" + clan.getName() + " §cgeworfen!");
		CF_Clans.sendMessageToPlayer(p, "§aDu hast den Spieler §6" + target.getName() + " §aaus deinem Clan entfernt!");

		return false;
	}

}