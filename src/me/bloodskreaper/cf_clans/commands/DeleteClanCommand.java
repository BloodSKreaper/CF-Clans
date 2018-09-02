package me.bloodskreaper.cf_clans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;

public class DeleteClanCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length > 1) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! §b/clan delete");
		} else {
			if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
				CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
			} else {
				if (!CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
					CF_Clans.sendMessageToPlayer(p, "§cDu bist nicht der Admin des Clans!");
				} else {
					CF_Clans.getClanManager().removeClan(CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()));
					CF_Clans.sendMessageToPlayer(p, "§aDu hast deinen Clan gelöscht!");
				}
			}
		}
		return false;
	}

}