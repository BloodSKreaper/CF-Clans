package me.bloodskreaper.cf_clans.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;
import me.bloodskreaper.cf_clans.clansystem.Invite;

public class AcceptInviteCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length < 2) {
			if (!CF_Clans.getInviteManager().playerIsInvited(p.getUniqueId())) {
				CF_Clans.sendMessageToPlayer(p, "§cDu hast keine Einladungen!");
				return false;
			}
			List<Invite> invites = CF_Clans.getInviteManager().getInvitesOfPlayer(p.getUniqueId());
			CF_Clans.sendMessageToPlayer(p,
					"§aVon folgenden Clans steht eine Einladung aus: " + ListToCommaString(invites));

			return false;
		}
		if (args.length > 2) {
			CF_Clans.sendMessageToPlayer(p, "§cZu viele Angaben! §b/clan acceptinvite <CLANNAME>");
			return false;
		}
		Clan clan = CF_Clans.getClanManager().getClanFromName(args[1]);
		if (clan == null) {
			CF_Clans.sendMessageToPlayer(p, "§cDer Clan existiert nicht! Achte auf die Schreibweise des Namens.");
			return false;
		}
		if (!CF_Clans.getInviteManager().playerHasInviteFromClan(p.getUniqueId(), clan)) {
			CF_Clans.sendMessageToPlayer(p, "§cDu hast keine Einladung vom Clan §6" + args[1]);
			return false;
		}
		if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) != null) {
			CF_Clans.sendMessageToPlayer(p,
					"§cDu kannst nicht einem anderen Clan beitreten, solange du Mitglied eines Clans bist!");
			return false;
		}
		Invite invite = CF_Clans.getInviteManager().getInviteOfPlayerAndClan(p.getUniqueId(), clan);
		invite.getClan().sendMessageToAllMembers("§aDer Spieler §6" + p.getName()
				+ " §ahat die Clan-Einladung angenommen und ist nun Mitglied bei §6" + invite.getClan().getName());
		invite.getClan().addMember(p.getUniqueId());
		CF_Clans.getInviteManager().removeInvite(invite);
		p.sendMessage("§aDu hast die Einladung vom Clan §6" + invite.getClan().getName()
				+ " §aangenommen und bist nun Mitglied des Clans!");

		return false;
	}

	private String ListToCommaString(List<Invite> invites) {
		int i = invites.size();
		String output = "§6";
		for (Invite in : invites) {
			if (i == 1) {
				output = output + in.getClan().getName();

			} else {
				output = output + in.getClan().getName() + "§a, §6";

			}
			i = i - 1;

		}
		return output;
	}

}
