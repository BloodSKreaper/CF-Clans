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
	public ClanInfoCommand() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		Clan clan;
		if (args.length == 1) {
			CF_Clans.sendMessageToPlayer(p, "§3Deine Clan Informationen:");
			if (CF_Clans.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
				p.sendMessage("§cKein Mitglied eines Clans");
			} else {
				clan = CF_Clans.getClanManager().getClanOfMember(p.getUniqueId());
				printClanDetailsToPlayer(p, clan);
			}
			return false;
		}
		if (args.length == 2) {
			clan = CF_Clans.getClanManager().getClanFromPrefix(args[1]);
			if (clan == null) {
				clan = CF_Clans.getClanManager().getClanFromName(args[1]);
			}

			if (clan == null) {
				CF_Clans.sendMessageToPlayer(p,
						"§cEs wurde kein passender Clan zur Eingabe §6" + args[1] + " §cgefunden.");
			} else {
				CF_Clans.sendMessageToPlayer(p, "§3Clan Infos zum Clan " + clan.getName() + ":");
				printClanDetailsToPlayer(p, clan);
			}
			return false;
		}

		return false;
	}

	private String inviteListToCommaString(List<Invite> invites) {
		int i = invites.size();
		String output = "§6";
		for (Invite in : invites) {
			if (i == 1) {
				output = output + Bukkit.getOfflinePlayer(in.getPlayer()).getName();
			} else {
				output = output + Bukkit.getOfflinePlayer(in.getPlayer()).getName() + "§a, §6";
			}
			i--;
		}
		return output;
	}

	private void printClanDetailsToPlayer(Player p, Clan clan) {
		String name = clan.getName();
		String displayname = clan.getClanDisplayName();
		String admin = Bukkit.getOfflinePlayer(clan.getLeader()).getName();
		String members = UUIDListToCommaString(clan.getMembers());
		String invitedOnes = inviteListToCommaString(CF_Clans.getInviteManager().getInvitesOfClan(clan));

		p.sendMessage("§aName: §8[§b" + displayname + "§8]" + " " + "§6" + name);
		p.sendMessage("§aAdmin: §6" + admin);
		p.sendMessage("§aMitglieder: " + members);
		p.sendMessage("§aEingeladene: " + invitedOnes);
	}

	public String UUIDListToCommaString(List<UUID> members) {
		int i = members.size();
		String output = "§6";
		for (UUID in : members) {
			if (i == 1) {
				output = output + Bukkit.getOfflinePlayer(in).getName();
			} else {
				output = output + Bukkit.getOfflinePlayer(in).getName() + "§a, §6";
			}

			i--;
		}

		return output;
	}
}
