package me.bloodskreaper.cf_clans.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;
import me.bloodskreaper.cf_clans.clansystem.Invite;

public class InviteMemberCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§cDu musst einen Namen angeben. Bsp: §b/clan invite BloodSKreaper");
			return false;
		}
		if (args.length > 2) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan invite <PLAYERNAME>");
			return false;
		}
		Clan clan = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
		if (clan == null) {
			CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
			return false;
		}
		if (!clan.getLeader().equals(p.getUniqueId())) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDu bist nicht der Admin des Clans und kannst somit keine Spieler einladen!");
			return false;
		}
		Player invited = Bukkit.getPlayer(args[1]);
		if (invited == null) {
			CF_Clans.sendMessageToPlayer(p, "§cDer Spieler §6" + args[1] + " §cist nicht online!");
			return false;
		}
		if (clan.getMembers().contains(invited.getUniqueId())) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDer Spieler §6" + invited.getName() + " §cist bereits Mitglied des Clans §6" + clan.getName());
			return false;
		}
		if (CF_Clans.getInviteManager().playerHasInviteFromClan(invited.getUniqueId(), clan)) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDer Spieler §6" + invited.getName() + " §cwurde bereits in den Clan eingeladen!");
			return false;
		}
		// All should be fine here
		CF_Clans.getInviteManager().addInvite(new Invite(invited.getUniqueId(), clan));
		CF_Clans.sendMessageToPlayer(p, "§aDu hast §6" + invited.getName() + " §ain den Clan §6" + clan.getName()
				+ " §aeingeladen. §6" + invited.getName() + " §ahat 10 Tage Zeit diese anzunehmen!");

		CF_Clans.sendMessageToPlayer(invited,
				"§aDu hast eine Einladung vom Clan §6" + clan.getName()
						+ " §aerhalten. Du kannst diese mit §b/clan acceptinvite " + clan.getName()
						+ " §aannehmen, oder mit §b/clan denyinvite " + clan.getName() + " §aablehnen");

		return false;
	}

}