package me.bloodskreaper.clans.api;

import java.util.UUID;

import me.bloodskreaper.clans.Clan;
import me.bloodskreaper.clans.Main;


public class ClanAPI {
	
	public ClanAPI(){
		
	}
	
	public String getClanPrefix(UUID uuid){
		if(getClanOfUser(uuid)!=null) {
			return getClanOfUser(uuid).getClanDisplayName();
		}
		return null;
	}
	
	public static Clan getClanOfUser(UUID uuid) {
		return Main.getClanManager().getClanOfMember(uuid);
		
	}

}
