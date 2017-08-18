package me.bloodskreaper.clans;

import java.util.ArrayList;
import java.util.List;

public class Clan {
	private String name;
	private String displayname;
	private List<ClanMember> members = new ArrayList<ClanMember>(15);
	
	public Clan(String name, ClanMember leader){
		this.name = name;
		members.add(leader);
		members.set(0, leader);
	}
	
	public Clan(String name, String displayname, ArrayList<ClanMember> members){
		this.name = name;
		this.displayname = displayname;
		this.members = members;
	}
	
	public void setClanDisplayName(String displayname){
		this.displayname = displayname;
	}
	
	public String getClanDisplayName(){
		return displayname;
	}
	
	public ClanMember getLeader(){
		return members.get(0);
	}
	
	public String getName(){
		return name;
	}
	
	public boolean setLeader(ClanMember leader){
		if(members.contains(leader)&&members.get(0) != leader){
			members.set(0, leader);
			return true;
		}
		else{
			return false;
		}
	}
	
	
	public boolean addMember(ClanMember member){
		if(members.add(member))return true;
		return false;
	}
	
	public boolean removeMember(ClanMember member){
		boolean result = false;
		if(member != members.get(0))
		result = members.remove(member);
		return result;
	}
	
	public List<ClanMember> getMembers(){
		return members;
	}
	
	

}
