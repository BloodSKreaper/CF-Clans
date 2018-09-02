package me.bloodskreaper.cf_clans.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;

public class RemoveMemberCommand implements CommandInterface {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§cDu musst einen Namen angeben! §b/clan removemember <NAME>");
		} else {
			if (args.length > 2) {
				CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! §b/clan removemember <NAME>");
			} else {
				if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
					CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
				} else {
					if (Bukkit.getOfflinePlayer(args[1]) == null) {
						CF_Clans.sendMessageToPlayer(p, "§cDer Spieler §6+" + args[1] + "§cexistiert nicht!");
					} else {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						if (CF_Clans.getClanManager().getClanOfMember(target.getUniqueId()) == null) {
							CF_Clans.sendMessageToPlayer(p, "§cDer Spieler ist kein Mitglied eines Clans!");
						} else {
							if (!CF_Clans.getClanManager().getClanOfMember(target.getUniqueId())
									.equals(CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()))) {
								CF_Clans.sendMessageToPlayer(p, "§cDer Spieler ist nicht in deinem Clan!");
							} else {
								if (!CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()).getLeader()
										.equals(p.getUniqueId())) {
									CF_Clans.sendMessageToPlayer(p,
											"§cDu bist nicht der Admin des Clans und kannst somit keine Member entfernen!");
								} else {
									if (target.getUniqueId().equals(
											CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()).getLeader())) {
										CF_Clans.sendMessageToPlayer(p,
												"§cDer Admin kann nicht aus dem Clan entfernt werden!");
									} else {
										CF_Clans.getClanManager().getClanOfMember(p.getUniqueId())
												.removeMember(target.getUniqueId());
										if (Bukkit.getPlayer(args[1]) != null)
											CF_Clans.sendMessageToPlayer(Bukkit.getPlayer(args[1]),
													"§cDu wurdest aus dem Clan §6" + CF_Clans.getClanManager()
															.getClanOfMember(p.getUniqueId()).getName()
															+ " §cgeworfen!");
										CF_Clans.sendMessageToPlayer(p, "§aDu hast den Spieler §6" + target.getName()
												+ " §aaus deinem Clan entfernt!");
									}
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