package me.bloodskreaper.clans.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Main;

public class DeleteClan implements CommandInterface {

	 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        Player p = (Player) sender;
        if(args.length > 1) {
        	Main.sendMessageToPlayer(p, "§cFalsches Format! §b/clan delete");
        }else {
        	if(Main.getClanManager().getClanOfMember(p.getUniqueId())==null) {
        		Main.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
        	}else {
        		if(!Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
        			Main.sendMessageToPlayer(p, "§cDu bist nicht der Admin des Clans!");
        		}else {
        			Main.getClanManager().removeClan(Main.getClanManager().getClanOfMember(p.getUniqueId()));
        			Main.sendMessageToPlayer(p, "§aDu hast deinen Clan gelöscht!");
        		}
        	}
        }
        return false;
    }
 
}