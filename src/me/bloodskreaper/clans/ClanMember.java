package me.bloodskreaper.clans;

import java.util.UUID;


public class ClanMember {
	private UUID uuid;
	private Clan clan;
	
	public ClanMember(UUID uuid, Clan clan){
		this.uuid = uuid;
		this.clan = clan;
	}
	
	public Clan getClan(){
		return clan;
	}
	
	public UUID getUUID(){
		return uuid;
	}
	
	public String getClanPrefix(){
		return clan.getClanDisplayName();
	}
	
	public void setClan(Clan clan){
		this.clan = clan;
	}

}
