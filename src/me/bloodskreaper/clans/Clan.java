package me.bloodskreaper.clans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Clan {
	/*
	 * name = Name des Clans
	 * displayname = Abkürzung für den Clan
	 * leader = Teamleiter
	 * members = alle Clanmitglieder einschließlich des Leaders
	 */
	private String name;
	private String displayname;
	private UUID leader;
	private List<UUID> members = new ArrayList<UUID>();
	
	public Clan(String name, UUID leader){
		this.name = name;
		this.leader = leader;
	}
	
	public Clan(String name, String displayname, UUID leader, ArrayList<UUID> members){
		this.name = name;
		this.displayname = displayname;
		this.members = members;
		this.leader = leader;
	}

	public void setName(String name){
		this.name = name;
	}	
	public void setDisplayName(String displayname){
		this.displayname = displayname;
	}	
	public void setLeader(UUID leader) {
		this.leader = leader;
	}	
	public void setMembers(List<UUID> members) {
		this.members = members;
	}
	
	public String getName(){
		return name;
	}	
	public String getClanDisplayName(){
		return displayname;
	}	
	public UUID getLeader(){
		return leader;
	}	
	public List<UUID> getMembers(){
		return members;
	}
	
	public void addMember(UUID member){
		members.add(member);
	}	
	public void removeMember(UUID member){
		members.remove(member);
	}

}
