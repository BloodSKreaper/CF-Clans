package me.bloodskreaper.cf_clans.clansystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.bloodskreaper.cf_clans.CF_Clans;

public class ClanManager {
	private List<Clan> clans = new ArrayList<Clan>();

	public boolean addClan(Clan clan) {
		return clans.add(clan);
	}

	public boolean removeClan(Clan clan) {
		CF_Clans.getInviteManager().removeInvite(CF_Clans.getInviteManager().getInvitesOfClan(clan));
		return clans.remove(clan);
	}

	public List<Clan> getClans() {
		return clans;
	}

	public Clan getClanOfMember(UUID uuid) {
		Clan result = null;
		for (Clan c : clans) {
			for (UUID id : c.getMembers()) {
				if (id.equals(uuid)) {
					result = c;
					break;
				}

			}
		}
		return result;
	}

	public Clan getClanFromName(String s) {
		Clan result = null;
		for (Clan c : clans) {
			if (c.getName().equalsIgnoreCase(s))
				result = c;
		}
		return result;
	}

	public Clan getClanFromPrefix(String s) {
		Clan result = null;
		for (Clan c : clans) {
			if (c.getClanDisplayName() != null && c.getClanDisplayName().equalsIgnoreCase(s))
				result = c;
		}
		return result;
	}

	public Clan getClanFromUUID(UUID uuid) {
		Clan result = null;
		for (Clan c : clans) {
			if (c.getClanUUID().equals(uuid))
				result = c;
		}
		return result;

	}

}
