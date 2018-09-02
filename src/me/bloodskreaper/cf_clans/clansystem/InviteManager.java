package me.bloodskreaper.cf_clans.clansystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InviteManager {
	private List<Invite> invites = new ArrayList<Invite>();

	public InviteManager() {
	}

	public boolean addInvite(Invite invite) {
		return invites.add(invite);
	}

	public boolean removeInvite(Invite invite) {
		return invites.remove(invite);
	}

	public List<Invite> getInvites() {
		return invites;
	}

	public boolean playerIsInvited(UUID uuid) {
		boolean result = false;
		for (Invite c : invites) {
			if (c.getPlayer().equals(uuid))
				result = true;
		}
		return result;
	}

	public boolean playerHasInviteFromClan(UUID uuid, String clanname) {
		boolean result = false;
		for (Invite inv : invites) {
			if (inv.getPlayer().equals(uuid) && inv.getClan().getName().equalsIgnoreCase(clanname))
				result = true;
		}
		return result;
	}

	public List<Invite> getInvitesOfPlayer(UUID uuid) {
		List<Invite> playerinvites = new ArrayList<Invite>();
		for (Invite in : invites) {
			if (in.getPlayer().equals(uuid))
				playerinvites.add(in);
		}
		return playerinvites;
	}

	public Invite getInviteOfPlayerAndClan(UUID player, String clanname) {
		for (Invite inv : invites) {
			if (inv.getPlayer().equals(player) && inv.getClan().getName().equalsIgnoreCase(clanname))
				return inv;
		}
		return null;
	}

	public List<Invite> getInvitesOfClan(Clan c) {
		List<Invite> claninvites = new ArrayList<Invite>();
		for (Invite in : invites) {
			if (in.getClan().equals(c)) {
				claninvites.add(in);
			}
		}
		return claninvites;
	}

	public void checkForExpiredInvites() {
		List<Invite> toRemove = new ArrayList<Invite>(invites.size());
		for (Invite i : invites) {
			if (i.isOutdated()) {
				toRemove.add(i);
			}
		}
		invites.removeAll(toRemove);
	}

	public void removeInvite(List<Invite> invitesOfClan) {
		invites.removeAll(invitesOfClan);
	}

}
