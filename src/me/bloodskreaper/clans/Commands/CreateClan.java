package me.bloodskreaper.clans.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Clan;
import me.bloodskreaper.clans.Main;

public class CreateClan implements CommandInterface{

	 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
    	
        Player p = (Player) sender;
        if(args.length ==1) {
        	Main.sendMessageToPlayer(p, "§cDu musst einen Namen angeben. Bsp: §b/clan create FlyMans");
        }else if(args.length>2) {
        	Main.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan create <CLANNAME>");
        }else {
        	if(args[1].length() <3 || args[1].length() >16) {
        		Main.sendMessageToPlayer(p, "§cDer Name ist entweder zu lang oder zu kurz. Er muss aus 3 bis 16 Zeichen bestehen.");
        	}else {
        		if(Main.getClanManager().getClanFromName(args[1])!= null) {
        			Main.sendMessageToPlayer(p, "§cDer Name ist bereits für einen anderen Clan vergeben!");
        		}else {
        			if(Main.getClanManager().getClanOfMember(p.getUniqueId())!= null) {
        				Main.sendMessageToPlayer(p, "§cDu bist bereits Mitglied eines Clans!");
        			}else {
        				
        				List<String> members = new ArrayList<String>();
        				members.add(p.getUniqueId().toString());
        				Clan c = new Clan(args[1], p.getUniqueId());
        				Main.getClanManager().addClan(c);
        				Main.sendMessageToPlayer(p, "§aDu hast deinen Clan §6"+c.getName()+" §aerfolgreich erstellt! Mit §b/clan prefix <CLANPREFIX> §a kannst du einen Prefix festlegen!");
        				
        			}
        		}
        	}
        }
        return false;
    }
 
}
