package me.bloodskreaper.cf_clans.clansystem;

import java.util.UUID;

public class Invite {
	private long created;
	private UUID inviteUUID;
	private UUID invitedPlayer;
	private Clan clan;
	
	public Invite(UUID player, Clan clan) {
		this.inviteUUID = UUID.randomUUID();
		this.invitedPlayer = player;
		this.clan = clan;
		created = System.currentTimeMillis();
	}
	
	public Invite(UUID inviteUUID, long created, UUID player, Clan clan) {
		this.inviteUUID = inviteUUID;
		this.invitedPlayer = player;
		this.clan = clan;
		this.created = created;
	}
	
	public UUID getPlayer() {
		return invitedPlayer;
	}
	
	public Clan getClan() {
		return clan;
	}
	
	public UUID getInviteUUID() {
		return inviteUUID;
	}
	
	public long getCreated() {
		return created;
	}
	
	public boolean isOutdated() {
		long diff = System.currentTimeMillis() - created;
		int days = (int) (diff/(1000*60*60*24));
		if(days>10)return true;
		return false;
	}
}
