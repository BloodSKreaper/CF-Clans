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
	private UUID clanUUID;
	private String displayname;
	private UUID leader;
	private List<String> members = new ArrayList<String>();
	
	public Clan(String name, UUID leader){
		clanUUID = UUID.randomUUID();
		this.name = name;
		this.leader = leader;
	}
	
	public Clan(UUID clanUUID, String name, String displayname, UUID leader, List<String> members){
		this.name = name;
		this.displayname = displayname;
		this.members = members;
		this.leader = leader;
		this.clanUUID = clanUUID;
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
	public void setMembers(List<String> members) {
		this.members = members;
	}
	
	public String getName(){
		return name;
	}
	public UUID getClanUUID(){
		return clanUUID;
	}
	public String getClanDisplayName(){
		return displayname;
	}	
	public UUID getLeader(){
		return leader;
	}	
	public List<String> getMembers(){
		return members;
	}
	
	public void addMember(UUID member){
		members.add(member.toString());
	}	
	public void removeMember(UUID member){
		members.remove(member.toString());
	}

}
