package me.bloodskreaper.clans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClanManager {
	private HashMap<UUID, ClanMember> clanmembers = new HashMap<UUID, ClanMember>();
	private List<Clan> clans = new ArrayList<Clan>();
	
	public ClanMember getClanMember(UUID uuid){
		return clanmembers.get(uuid);
	}
	
	public boolean addClan(Clan clan){
		return clans.add(clan);
	}
	
	public boolean removeClan(Clan clan){
		return clans.remove(clan);
	}

}
