package me.bloodskreaper.cf_clans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class LeaveClanCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length != 1) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! §b/clan leave");
		} else {
			if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
				CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
			} else {
				if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
					CF_Clans.sendMessageToPlayer(p,
							"§cDu kannst einen Clan nicht verlassen, solange du der Admin dieses bist! Ernenne jemanden Anderen als Admin mit §b/clan leader <NAME> §coder lösche den Clan mit §b/clan delete");
				} else {
					Clan c = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
					c.removeMember(p.getUniqueId());
					CF_Clans.sendMessageToPlayer(p, "§aDu hast den Clan §6" + c.getName() + " §averlassen!");
				}
			}
		}
		return false;
	}
}
