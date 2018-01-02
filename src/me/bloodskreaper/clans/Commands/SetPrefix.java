package me.bloodskreaper.clans.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Clan;
import me.bloodskreaper.clans.Main;

public class SetPrefix  implements CommandInterface {

	 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
    	
        Player p = (Player) sender;
        if(args.length ==1) {
        	Main.sendMessageToPlayer(p, "§cDu musst einen Prefix angeben. Bsp: §b/clan prefix FLY");
        }else if(args.length>2) {
        	Main.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan create <CLANPREFIX>");
        }else {
        	if(args[1].length() <2 || args[1].length() >16) {
        		Main.sendMessageToPlayer(p, "§cDer Name ist entweder zu lang oder zu kurz. Er muss aus 2 bis 4 Zeichen bestehen.");
        	}else {
        		if(Main.getClanManager().getClanFromPrefix(args[1])!= null) {
        			Main.sendMessageToPlayer(p, "§cDer Prefix ist bereits für einen anderen Clan vergeben!");
        		}else {
        			if(Main.getClanManager().getClanOfMember(p.getUniqueId())== null) {
        				Main.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
        			}else {
        				System.out.println(p.getUniqueId());
        				if(!Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
        					System.out.println(p.getUniqueId());
        					System.out.println(Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader());
        					Main.sendMessageToPlayer(p, "§cDu bist nicht der Admin des Clans und kannst somit nicht den Prefix ändern!");
        				}else {
        					Clan c = Main.getClanManager().getClanOfMember(p.getUniqueId());
        					c.setDisplayName(args[1]);
        					Main.sendMessageToPlayer(p, "§aDu hast den Prefix deines Clans §6"+c.getName()+" §aerfolgreich geändert! Dieser lautet nun §6"+c.getClanDisplayName());
        				}
        				
        				
        			}
        		}
        	}
        }
        return false;
    }
 
}