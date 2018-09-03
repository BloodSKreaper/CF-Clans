package me.bloodskreaper.cf_clans.clansystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.bloodskreaper.cf_clans.CF_Clans;

public class Clan {
	/*
	 * name = Name des Clans displayname = Abkürzung für den Clan leader =
	 * Teamleiter members = alle Clanmitglieder einschließlich des Leaders
	 */
	private String name;
	private UUID clanUUID;
	private String displayname;
	private UUID leader;
	private List<UUID> members = new ArrayList<UUID>();

	public Clan(String name, UUID leader) {
		clanUUID = UUID.randomUUID();
		this.name = name;
		this.leader = leader;
		members.add(leader);
	}

	public Clan(UUID clanUUID, String name, String displayname, UUID leader, List<UUID> members) {
		this.name = name;
		this.displayname = displayname;
		this.members = members;
		this.leader = leader;
		this.clanUUID = clanUUID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDisplayName(String displayname) {
		this.displayname = displayname;
	}

	public void setLeader(UUID leader) {
		this.leader = leader;
	}

	public void setMembers(List<UUID> members) {
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public UUID getClanUUID() {
		return clanUUID;
	}

	public String getClanDisplayName() {
		if (displayname != null)
			return ChatColor.translateAlternateColorCodes('&', displayname);
		return null;
	}

	public UUID getLeader() {
		return leader;
	}

	public List<UUID> getMembers() {
		return members;
	}

	public void addMember(UUID member) {
		members.add(member);
	}

	public void removeMember(UUID member) {
		members.remove(member);
	}

	public boolean playerIsMember(UUID uuid) {
		if (members.contains(uuid))
			return true;
		return false;
	}

	public void sendMessageToAllMembers(String message) {
		for (UUID userid : members) {
			if (Bukkit.getPlayer(userid) != null) {
				CF_Clans.sendMessageToPlayer(Bukkit.getPlayer(userid), message);
			}
		}
	}

	public void sendClanChat(Player sender, String message) {
		String formattedmessage = ChatColor.translateAlternateColorCodes('&', message);
		String formattedprefix = "§8[§6" + ChatColor.translateAlternateColorCodes('&', displayname) + "§8]";
		String sendercoloured = sender.getUniqueId().equals(leader) ? "§4" + sender.getName() : "§2" + sender.getName();
		Player target;
		for (UUID userid : members) {
			target = Bukkit.getPlayer(userid);
			if (target != null) {
				target.sendMessage(formattedprefix + " " + sendercoloured + " §7>> §f" + formattedmessage);
			}
		}
	}

}
