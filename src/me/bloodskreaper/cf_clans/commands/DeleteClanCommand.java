package me.bloodskreaper.cf_clans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class DeleteClanCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length > 1) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! §b/clan delete");
			return false;
		}
		Clan clan = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
		if (clan == null) {
			CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
			return false;
		}
		if (!clan.getLeader().equals(p.getUniqueId())) {
			CF_Clans.sendMessageToPlayer(p, "§cDu bist nicht der Admin des Clans!");
			return false;
		}
		clan.sendMessageToAllMembers("§6" + p.getName() + " §chat deinen Clan §6" + clan.getName() + " §cgelöscht!");
		CF_Clans.getClanManager().removeClan(clan);

		return false;
	}

}