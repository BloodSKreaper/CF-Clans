package me.bloodskreaper.cf_clans.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class ClanDebugCommand implements CommandInterface {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		sender.sendMessage("Total Clans " + CF_Clans.getClanManager().getClans().size());
		sender.sendMessage("Total Invites " + CF_Clans.getInviteManager().getInvites().size());
		for (Clan c : CF_Clans.getClanManager().getClans()) {
			sender.sendMessage(c.getClanDisplayName()+" "+c.getName()+" A:"+Bukkit.getOfflinePlayer(c.getLeader()).getName());
			sender.sendMessage(c.getMembers()+"");
		}
		return false;
	}

}
