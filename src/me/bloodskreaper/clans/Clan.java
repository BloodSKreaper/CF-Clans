package me.bloodskreaper.clans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clan {
	private String name;
	private String displayname;
	private List<UUID> members = new ArrayList<UUID>();
	private UUID leader;
	
	public Clan(String name, UUID leader){
		this.name = name;
		this.leader = leader;
		members.add(leader);
	}
	
	public Clan(String name, UUID leader, String displayname, ArrayList<UUID> members){
		this.name = name;
		this.leader = leader;
		this.displayname = displayname;
		this.members = members;
	}
	
	public void setClanDisplayName(String displayname){
		this.displayname = displayname;
	}
	
	public String getClanDisplayName(){
		return displayname;
	}
	
	public boolean setLeader(UUID leader){
		if(members.contains(leader)&&this.leader != leader){
			this.leader = leader;
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean addMember(UUID uuid){
		if(members.add(uuid))return true;
		return false;
	}
	
	public boolean removeMember(UUID uuid){
		boolean result = false;
		if(uuid != leader)
		result = members.remove(uuid);
		return result;
	}
	
	

}
