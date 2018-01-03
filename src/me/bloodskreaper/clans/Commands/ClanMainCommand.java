package me.bloodskreaper.clans.Commands;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Clan;
import me.bloodskreaper.clans.Invite;
import me.bloodskreaper.clans.Main;
import net.md_5.bungee.api.ChatColor;

public class ClanMainCommand implements CommandInterface{


	 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        Player p = (Player) sender;
        if(args.length >1) {
        	Main.sendMessageToPlayer(p, "§cFalsches Format! §b/clan info");
        }else {
        	Main.sendMessageToPlayer(p, "§3Deine Clan Informationen:");
        	p.sendMessage("§aDein Clan:");
        	if(Main.getClanManager().getClanOfMember(p.getUniqueId())==null) {
        		p.sendMessage("§cKein Mitglied eines Clans");
        	}else {
        		Clan c = Main.getClanManager().getClanOfMember(p.getUniqueId());
        		p.sendMessage(ChatColor.translateAlternateColorCodes('&', "§8[§f"+c.getClanDisplayName()+"§8] §f"+c.getName()));
        		String role = "";
        		if(c.getLeader().equals(p.getUniqueId())) role ="§6Admin";
        		if(!c.getLeader().equals(p.getUniqueId())) role ="§6Mitglied";        		
        		p.sendMessage("§aDeine Rolle: "+role );
        		p.sendMessage("§aAlle Mitglieder:");
        		p.sendMessage(UUIDListToCommaString(c.getMembers()));
        	}
        	
        	
        	p.sendMessage("§aEinladungen an dich:");        	
        	if(Main.getInviteManager().isInvited(p.getUniqueId())) {
        		p.sendMessage(InviteListToCommaString(Main.getInviteManager().getInvitesOfPlayer(p.getUniqueId())));
        	}else {
        		p.sendMessage("§cKeine Einladungen vorhanden.");
        	}if(Main.getClanManager().getClanOfMember(p.getUniqueId()) !=null&&Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
        		Clan c = Main.getClanManager().getClanOfMember(p.getUniqueId());
        		p.sendMessage("§aEinladungen deines Clans:");
        		if(Main.getInviteManager().getInvitesOfClan(c).size()>0) {
        			p.sendMessage(InviteListToCommaStringClan(Main.getInviteManager().getInvitesOfClan(c)));
        		
        		}
        	}
        	
        }
        
        
        return false;
    }
    
    public String InviteListToCommaString(List<Invite> invites) {
    	int i = invites.size();
    	String output = "§6";
		for(Invite in : invites){
			if(i==1){
				output = output+in.getClan().getName();

			}else{
				output = output+in.getClan().getName()+"§a, §6";

			}
			i = i-1;

		}
		return output;
		}
    public String InviteListToCommaStringClan(List<Invite> invites) {
    	int i = invites.size();
    	String output = "§6";
		for(Invite in : invites){
			if(i==1){
				output = output+Bukkit.getOfflinePlayer(in.getPlayer()).getName();

			}else{
				output = output+Bukkit.getOfflinePlayer(in.getPlayer()).getName()+"§a, §6";

			}
			i = i-1;

		}
		return output;
		}
    public String UUIDListToCommaString(List<String> members) {
    	int i = members.size();
    	String output = "§6";
		for(String in : members){
			if(i==1){
				output = output+Bukkit.getOfflinePlayer(UUID.fromString(in)).getName();

			}else{
				output = output+Bukkit.getOfflinePlayer(UUID.fromString(in)).getName()+"§a, §6";

			}
			i = i-1;

		}
		return output;
		}
}
