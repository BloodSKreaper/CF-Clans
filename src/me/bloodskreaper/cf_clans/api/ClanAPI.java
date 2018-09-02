package me.bloodskreaper.cf_clans.api;

import java.util.UUID;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class ClanAPI {

	public ClanAPI() {

	}

	public static String getClanPrefix(UUID uuid) {
		if (getClanOfUser(uuid) != null) {
			return getClanOfUser(uuid).getClanDisplayName();
		}
		return null;
	}

	public static Clan getClanOfUser(UUID uuid) {
		return CF_Clans.getClanManager().getClanOfMember(uuid);

	}

}
