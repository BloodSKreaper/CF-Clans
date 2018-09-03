package me.bloodskreaper.cf_clans.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class CreateClanCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player p = (Player) sender;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§cDu musst einen Namen angeben. Bsp: §b/clan create FlyMans");
			return false;
		}
		if (args.length > 2) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan create <CLANNAME>");
			return false;
		}
		if (args[1].length() < 3 || args[1].length() > 16) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDer Name ist entweder zu lang oder zu kurz. Er muss aus 3 bis 16 Zeichen bestehen.");
			return false;
		}
		if (CF_Clans.getClanManager().getClanFromName(args[1]) != null) {
			CF_Clans.sendMessageToPlayer(p, "§cDer Name ist bereits für einen anderen Clan vergeben!");
			return false;
		}
		if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) != null) {
			CF_Clans.sendMessageToPlayer(p, "§cDu bist bereits Mitglied eines Clans!");
			return false;
		}
		Clan c = new Clan(args[1], p.getUniqueId());
		CF_Clans.getClanManager().addClan(c);
		CF_Clans.sendMessageToPlayer(p, "§aDu hast deinen Clan §6" + c.getName()
				+ " §aerfolgreich erstellt! Mit §b/clan prefix <CLANPREFIX> §a kannst du einen Prefix festlegen!");
		return false;
	}

}
