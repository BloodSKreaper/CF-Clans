package me.bloodskreaper.clans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClanManager {
	private List<Clan> clans = new ArrayList<Clan>();
	

	
	public boolean addClan(Clan clan){
		return clans.add(clan);
	}
	
	public boolean removeClan(Clan clan){
		return clans.remove(clan);
	}
	public List<Clan> getClans(){
		return clans;
	}
	
	public Clan getClanOfMember(UUID uuid) {
		Clan result = null;
		for(Clan c: clans) {
			for(String id: c.getMembers()) {
				if(id.equalsIgnoreCase(uuid.toString())) {
					result = c;
					break;
				}
				
			}
		}
		return result;
	}
	
	public Clan getClanFromName(String s) {
		Clan result = null;
		for(Clan c: clans) {
			if(c.getName().equalsIgnoreCase(s)) result = c;
		}
		return result;
	}
	
	public Clan getClanFromPrefix(String s) {
		Clan result = null;
		for(Clan c: clans) {
			if(c.getClanDisplayName() != null &&c.getClanDisplayName().equalsIgnoreCase(s)) result = c;
		}
		return result;
	}
	
	public Clan getClanFromUUID(UUID uuid) {
		Clan result = null;
		for(Clan c: clans) {
			if(c.getClanUUID().equals(uuid)) result = c;
		}
		return result;
		
	}

}
