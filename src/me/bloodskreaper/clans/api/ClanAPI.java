package me.bloodskreaper.clans.api;

import java.util.UUID;

import me.bloodskreaper.clans.ClanMember;
import me.bloodskreaper.clans.Main;


public class ClanAPI {
	
	public ClanAPI(){
		
	}
	
	public String getClanPrefix(UUID uuid){
		ClanMember member = Main.getClanManager().getClanMember(uuid);
		return member.getClanPrefix();
	}

}
