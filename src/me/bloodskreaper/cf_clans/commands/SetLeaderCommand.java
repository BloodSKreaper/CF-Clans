package me.bloodskreaper.cf_clans.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class SetLeaderCommand implements CommandInterface {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§cDu musst einen Namen angeben. Bsp: §b/clan leader BloodSKreaper");
		} else {
			if (args.length > 2) {
				CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan leader <PLAYERNAME>");
			} else {
				if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
					CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
				} else {
					Clan clan = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
					if (!clan.getLeader().equals(p.getUniqueId())) {
						CF_Clans.sendMessageToPlayer(p, "§cDu bist nicht der Admin deines Clans!");
					} else {
						if (Bukkit.getOfflinePlayer(args[1]) == null) {
							CF_Clans.sendMessageToPlayer(p, "§cDer Spieler §6" + args[1] + " §cexistiert nícht!");
						} else {
							OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
							if (CF_Clans.getClanManager().getClanOfMember(target.getUniqueId()) == null) {
								CF_Clans.sendMessageToPlayer(p, "§cDer Spieler ist kein Mitglied eines Clans!");
							} else {
								if (!CF_Clans.getClanManager().getClanOfMember(target.getUniqueId()).equals(clan)) {
									CF_Clans.sendMessageToPlayer(p, "§cDer Spieler ist nicht in deinem Clan!");
								} else {
									// SET LEADER
									clan.setLeader(target.getUniqueId());
									CF_Clans.sendMessageToPlayer(p, "§aDu hast §6" + target.getName()
											+ " §azum Admin des Clans §6" + clan.getName() + " §aernannt!");
									if (Bukkit.getPlayer(args[1]) != null)
										CF_Clans.sendMessageToPlayer(Bukkit.getPlayer(args[1]),
												"§aDu wurdest zum Admin vom Clan §6" + clan.getName() + " §aernannt!");
								}
							}
						}
					}
				}
			}
		}
		return false;

	}

}