package me.bloodskreaper.clans.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Clan;
import me.bloodskreaper.clans.Invite;
import me.bloodskreaper.clans.Main;

public class InviteMember  implements CommandInterface {

	 
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
    	
        Player p = (Player) sender;
        if(args.length ==1) {
        	Main.sendMessageToPlayer(p, "§cDu musst einen Namen angeben. Bsp: §b/clan invite BloodSKreaper");
        }else if(args.length>2) {
        	Main.sendMessageToPlayer(p, "§cFalsches Format! Format: §b/clan invite <PLAYERNAME>");
        }else {
        	if(Main.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
        		Main.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
        	}else {
        		if(!Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
        			Main.sendMessageToPlayer(p, "§cDu bist nicht der Admin des Clans und kannst somit keine Spieler einladen!");
        		}else {
        			if(Bukkit.getOfflinePlayer(args[1]) == null) {
        			Main.sendMessageToPlayer(p, "§cDer Spieler §6"+args[1]+" §cexistiert nicht!");	
        			}else {
        				OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
        				Clan c = Main.getClanManager().getClanOfMember(p.getUniqueId());
        				if(c.getMembers().contains(target.getUniqueId().toString())) {
        					Main.sendMessageToPlayer(p, "§cDer Spieler §6"+target.getName()+" §cist bereits Mitglied des Clans §6"+c.getName());
        				}else {
        					if(Main.getInviteManager().PlayerhasInviteFromClan(target.getUniqueId(), c.getName())) {
        						Main.sendMessageToPlayer(p, "§cDer Spieler §6"+target.getName()+" §cwurde bereits in den Clan eingeladen!");
        					}else {       					
        						Main.getInviteManager().addInvite(new Invite(target.getUniqueId(), c));
        						Main.sendMessageToPlayer(p, "§aDu hast §6"+target.getName()+" §ain den Clan §6"+c.getName()+" §aeingeladen. §6"+target.getName()+" §ahat 10 Tage Zeit diese anzunehmen!");
        						if(Bukkit.getPlayer(args[1]) != null)Main.sendMessageToPlayer(Bukkit.getPlayer(args[1]), "§aDu hast eine Einladung vom Clan §6"+c.getName()+" §aerhalten. Du kannst diese mit §b/clan acceptinvite "+c.getName()+" §aannehmen, oder mit §b/clan denyinvite "+c.getName()+" §aablehnen");
        					}
        				}
        			}
        		}
        	}
        }
        return false;
    }
 
}