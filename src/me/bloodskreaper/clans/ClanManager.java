package me.bloodskreaper.clans;

import java.util.HashMap;
import java.util.UUID;

public class ClanManager {
	private HashMap<UUID, ClanMember> clanmembers = new HashMap<UUID, ClanMember>();
	
	public ClanMember getClanMember(UUID uuid){
		return clanmembers.get(uuid);
	}

}
