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

public class ClanMainCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		CF_Clans.sendMessageToPlayer(p, "§3Deine Clan Informationen:");
		p.sendMessage("§aDein Clan:");
		Clan clan = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
		if (clan == null) {
			p.sendMessage("§cKein Mitglied eines Clans");
		} else {
			printClanDetailsToPlayer(p, clan);
		}
		p.sendMessage("§aEinladungen an dich:");
		if (CF_Clans.getInviteManager().playerIsInvited(p.getUniqueId())) {
			p.sendMessage(InviteListToCommaString(CF_Clans.getInviteManager().getInvitesOfPlayer(p.getUniqueId())));
		} else {
			p.sendMessage("§cKeine Einladungen vorhanden.");
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

	private String inviteListToCommaStringClan(List<Invite> invites) {
		int i = invites.size();
		String output = "§6";
		for (Invite in : invites) {
			if (i == 1) {
				output = output + Bukkit.getOfflinePlayer(in.getPlayer()).getName();

			} else {
				output = output + Bukkit.getOfflinePlayer(in.getPlayer()).getName() + "§a, §6";

			}
			i = i - 1;

		}
		return output;
	}

	private String UUIDListToCommaString(List<UUID> members) {
		int i = members.size();
		String output = "§6";
		for (UUID in : members) {
			if (i == 1) {
				output = output + Bukkit.getOfflinePlayer(in).getName();

			} else {
				output = output + Bukkit.getOfflinePlayer(in).getName() + "§a, §6";

			}
			i = i - 1;

		}
		return output;
	}

	private void printClanDetailsToPlayer(Player p, Clan clan) {
		String name = clan.getName();
		String displayname = clan.getClanDisplayName();
		String admin = Bukkit.getOfflinePlayer(clan.getLeader()).getName();
		String members = UUIDListToCommaString(clan.getMembers());
		String invitedOnes = inviteListToCommaStringClan(CF_Clans.getInviteManager().getInvitesOfClan(clan));

		p.sendMessage("§aName: §8[§b" + displayname + "§8]" + " " + "§6" + name);
		p.sendMessage("§aAdmin: §6" + admin);
		p.sendMessage("§aMitglieder: " + members);
		p.sendMessage("§aEingeladene: " + invitedOnes);
	}
}
