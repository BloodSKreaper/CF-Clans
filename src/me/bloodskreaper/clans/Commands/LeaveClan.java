package me.bloodskreaper.clans.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Clan;
import me.bloodskreaper.clans.Main;

public class LeaveClan implements CommandInterface{

	 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        Player p = (Player) sender;
        if(args.length != 1) {
        	Main.sendMessageToPlayer(p, "§cFalsches Format! §b/clan leave");
        }else {
        	if(Main.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
        		Main.sendMessageToPlayer(p, "§cDu bist kein Mitglied eines Clans!");
        	}else {
        		if(Main.getClanManager().getClanOfMember(p.getUniqueId()).getLeader().equals(p.getUniqueId())) {
        			Main.sendMessageToPlayer(p, "§cDu kannst einen Clan nicht verlassen, solange du der Admin dieses bist! Ernenne jemanden Anderen als Admin mit §b/clan leader <NAME> §coder lösche den Clan mit §b/clan remove");
        		}else {
        			Clan c = Main.getClanManager().getClanOfMember(p.getUniqueId());
        			c.removeMember(p.getUniqueId());
        			Main.sendMessageToPlayer(p, "§aDu hast den Clan §6"+c.getName()+" §averlassen!");
        		}
        	}
        	/*Testen ob der Spieler in einem Clan ist
        	 * Testen, ob der Spieler der Leiter eines Clans sowie der letzte Member des Clans ist
        	 * Testen, ob
        	 */
        }
        return false;
    }
}
