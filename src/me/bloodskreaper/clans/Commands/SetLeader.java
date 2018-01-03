package me.bloodskreaper.clans.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Clan;
import me.bloodskreaper.clans.Main;

public class SetLeader  implements CommandInterface {

	 
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
    	
        Player p = (Player) sender;
        if(args.length ==1) {
        	Main.sendMessageToPlayer(p, "§cDu musst einen Namen angeben. Bsp: §b/clan leader BloodSKreaper");
        }else {
        	if(args.length>2) {
        	Main.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan leader <PLAYERNAME>");
        }else {
        	if(Main.getClanManager().getClanOfMember(p.getUniqueId())==null) {
        		Main.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");        		
        	}else {
        		Clan clan = Main.getClanManager().getClanOfMember(p.getUniqueId());
        		if(!clan.getLeader().equals(p.getUniqueId())) {
        			Main.sendMessageToPlayer(p, "§cDu bist nicht der Admin deines Clans!");
        		}else {
        			if(Bukkit.getOfflinePlayer(args[1]) == null){
        				Main.sendMessageToPlayer(p, "§cDer Spieler §6"+args[1]+" §cexistiert nícht!");
        			}else {
        				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        				if(Main.getClanManager().getClanOfMember(target.getUniqueId())== null) {
        					Main.sendMessageToPlayer(p, "§cDer Spieler ist kein Mitglied eines Clans!");
        				}else {
        					if(!Main.getClanManager().getClanOfMember(target.getUniqueId()).equals(clan)) {
        						Main.sendMessageToPlayer(p, "§cDer Spieler ist nicht in deinem Clan!");
        					}else {
        						//SET LEADER
        						clan.setLeader(target.getUniqueId());
        						Main.sendMessageToPlayer(p, "§aDu hast §6"+target.getName()+" §azum Admin des Clans §6"+clan.getName()+" §aernannt!");
        						if(Bukkit.getPlayer(args[1]) != null) Main.sendMessageToPlayer(Bukkit.getPlayer(args[1]), "§aDu wurdest zum Admin vom Clan §6"+clan.getName()+" §aernannt!");
        					}
        				}
        			}
        		}
        	}
        	}
        }
        return false;
        
    }
 
}