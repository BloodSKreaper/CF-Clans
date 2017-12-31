package me.bloodskreaper.clans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InviteManager {	
	private List<Invite> invites = new ArrayList<Invite>();



	public boolean addInvite(Invite	invite){
	return invites.add(invite);
}

public boolean removeInvite(Invite invite){
	return invites.remove(invite);
}
public List<Invite> getInvites(){
	return invites;
}

public boolean isInvited(UUID uuid) {
	boolean result = false;
	for(Invite c: invites) {
			if(c.getPlayer().equals(uuid)) result = true;
	}
	return result;
}

public boolean PlayerhasInviteFromClan(UUID uuid, String clanname) {
	boolean result = false;
	for(Invite inv: invites) {
		if(inv.getPlayer().equals(uuid)&&inv.getClan().getName().equalsIgnoreCase(clanname)) result = true;
	}
	return result;
}



}