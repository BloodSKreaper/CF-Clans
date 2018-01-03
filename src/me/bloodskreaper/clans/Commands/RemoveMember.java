package me.bloodskreaper.clans.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Main;

public class RemoveMember  implements CommandInterface {

	 
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        Player p = (Player) sender;
        if(args.length == 1) {
        	Main.sendMessageToPlayer(p, "§cDu musst einen Namen angeben! §b/clan removemember <NAME>");
        }else {
        	if(args.length >2) {
        		Main.sendMessageToPlayer(p, "§cFalsches Format! §b/clan removemember <NAME>");
        	}else {
        		if(Main.getClanManager().getClanOfMember(p.getUniqueId())==null) {
        			Main.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
        		}else {
        			if(Bukkit.getOfflinePlayer(args[1])==null) {
        				Main.sendMessageToPlayer(p, "§cDer Spieler §6+"+args[1]+"§cexistiert nicht!");
        			}else {
        				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        				if(Main.getClanManager().getClanOfMember(target.getUniqueId())==null) {
        					Main.sendMessageToPlayer(p, "§cDer Spieler ist kein Mitglied eines Clans!");
        				}else {
        					if(!Main.getClanManager().getClanOfMember(target.getUniqueId()).equals(Main.getClanManager().getClanOfMember(p.getUniqueId()))) {
        						Main.sendMessageToPlayer(p, "§cDer Spieler ist nicht in deinem Clan!");
        					}else {
        						if(!Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
        							Main.sendMessageToPlayer(p, "§cDu bist nicht der Admin des Clans und kannst somit keine Member entfernen!");
        						}else {
        							if(target.getUniqueId().equals(Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader())) {
        								Main.sendMessageToPlayer(p, "§cDer Admin kann nicht aus dem Clan entfernt werden!");
        							}else {
        								Main.getClanManager().getClanOfMember(p.getUniqueId()).removeMember(target.getUniqueId());
        								if(Bukkit.getPlayer(args[1])!=null)Main.sendMessageToPlayer(Bukkit.getPlayer(args[1]), "§cDu wurdest aus dem Clan §6"+Main.getClanManager().getClanOfMember(p.getUniqueId()).getName()+" §cgeworfen!");
        								Main.sendMessageToPlayer(p, "§aDu hast den Spieler §6"+target.getName()+" §aaus deinem Clan entfernt!");
        							}
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