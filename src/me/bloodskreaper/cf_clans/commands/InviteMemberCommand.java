package me.bloodskreaper.cf_clans.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;
import me.bloodskreaper.cf_clans.clansystem.Invite;

public class InviteMemberCommand implements CommandInterface {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§cDu musst einen Namen angeben. Bsp: §b/clan invite BloodSKreaper");
		} else if (args.length > 2) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan invite <PLAYERNAME>");
		} else {
			if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
				CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
			} else {
				if (!CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
					CF_Clans.sendMessageToPlayer(p,
							"§cDu bist nicht der Admin des Clans und kannst somit keine Spieler einladen!");
				} else {
					if (Bukkit.getOfflinePlayer(args[1]) == null) {
						CF_Clans.sendMessageToPlayer(p, "§cDer Spieler §6" + args[1] + " §cexistiert nicht!");
					} else {
						OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
						Clan c = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
						if (c.getMembers().contains(target.getUniqueId().toString())) {
							CF_Clans.sendMessageToPlayer(p, "§cDer Spieler §6" + target.getName()
									+ " §cist bereits Mitglied des Clans §6" + c.getName());
						} else {
							if (CF_Clans.getInviteManager().playerHasInviteFromClan(target.getUniqueId(),
									c.getName())) {
								CF_Clans.sendMessageToPlayer(p, "§cDer Spieler §6" + target.getName()
										+ " §cwurde bereits in den Clan eingeladen!");
							} else {
								CF_Clans.getInviteManager().addInvite(new Invite(target.getUniqueId(), c));
								CF_Clans.sendMessageToPlayer(p,
										"§aDu hast §6" + target.getName() + " §ain den Clan §6" + c.getName()
												+ " §aeingeladen. §6" + target.getName()
												+ " §ahat 10 Tage Zeit diese anzunehmen!");
								if (Bukkit.getPlayer(args[1]) != null)
									CF_Clans.sendMessageToPlayer(Bukkit.getPlayer(args[1]),
											"§aDu hast eine Einladung vom Clan §6" + c.getName()
													+ " §aerhalten. Du kannst diese mit §b/clan acceptinvite "
													+ c.getName() + " §aannehmen, oder mit §b/clan denyinvite "
													+ c.getName() + " §aablehnen");
							}
						}
					}
				}
			}
		}
		return false;
	}

}