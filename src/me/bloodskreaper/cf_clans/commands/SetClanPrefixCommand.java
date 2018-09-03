package me.bloodskreaper.cf_clans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class SetClanPrefixCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§cDu musst einen Prefix angeben. Bsp: §b/clan prefix FLY");
			return false;
		}
		if (args.length > 2) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan create <CLANPREFIX>");
			return false;
		}
		if (args[1].length() < 2 || args[1].length() > 6) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDer Prefix ist entweder zu lang oder zu kurz. Er muss aus 2 bis 4 Zeichen bestehen.");
			return false;
		}
		if (args[1].contains("&")) {
			CF_Clans.sendMessageToPlayer(p, "§cPrefix darf keine Color-Codes enthalten");
			return false;
		}

		Clan clan = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
		if (clan == null) {
			CF_Clans.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
			return false;
		}
		if (!clan.getLeader().equals(p.getUniqueId())) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDu bist nicht der Admin des Clans und kannst somit nicht den Prefix ändern!");
			return false;
		}
		if (CF_Clans.getClanManager().getClanFromPrefix(args[1]) != null) {
			CF_Clans.sendMessageToPlayer(p, "§cDer Prefix ist bereits für einen anderen Clan vergeben!");
			return false;
		}
		clan.setDisplayName(args[1]);
		CF_Clans.sendMessageToPlayer(p, "§aDu hast den Prefix deines Clans §6" + clan.getName()
				+ " §aerfolgreich geändert! Dieser lautet nun §6" + clan.getClanDisplayName());

		return false;

	}
}