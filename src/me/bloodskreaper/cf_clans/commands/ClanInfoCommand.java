package me.bloodskreaper.cf_clans.commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;
import me.bloodskreaper.cf_clans.clansystem.Invite;

public class ClanInfoCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (args.length > 1) {
			CF_Clans.sendMessageToPlayer(p, "§cFalsches Format! §b/clan info");
		} else {
			CF_Clans.sendMessageToPlayer(p, "§3Deine Clan Informationen:");
			p.sendMessage("§aDein Clan:");
			if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
				p.sendMessage("§cKein Mitglied eines Clans");
			} else {
				Clan c = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
				p.sendMessage("§aClan: §6" + c.getClanDisplayName() + " " + c.getName());
				String role = "";
				if (c.getLeader().equals(p.getUniqueId()))
					role = "§6Admin";
				if (!c.getLeader().equals(p.getUniqueId()))
					role = "§6Mitglied";
				p.sendMessage("§aDeine Rolle: " + role);
				p.sendMessage("§aAlle Mitglieder:");
				p.sendMessage(UUIDListToCommaString(c.getMembers()));
			}

			p.sendMessage("§aEinladungen:");
			if (CF_Clans.getInviteManager().playerIsInvited(p.getUniqueId())) {
				p.sendMessage(InviteListToCommaString(CF_Clans.getInviteManager().getInvitesOfPlayer(p.getUniqueId())));
			} else {
				p.sendMessage("§cKeine Einladungen vorhanden.");
			}

		}

		return false;
	}

	private String InviteListToCommaString(List<Invite> invites) {
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

	private String UUIDListToCommaString(List<String> members) {
		int i = members.size();
		String output = "§6";
		for (String in : members) {
			if (i == 1) {
				output = output + Bukkit.getOfflinePlayer(UUID.fromString(in)).getName();

			} else {
				output = output + Bukkit.getOfflinePlayer(UUID.fromString(in)).getName() + "§a, §6";

			}
			i = i - 1;

		}
		return output;
	}
}
