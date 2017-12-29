package me.bloodskreaper.clans.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Main;

public class CreateClan implements CommandInterface{

	 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        Player p = (Player) sender;
 
        //We don't have to check if the args length is equal to one, but you will have to check if it is greater than 1.
        if(args.length == 1) {
        	if(Main.getClanManager().getClanOfMember(p.getUniqueId()) == null) {
        		
        	}
        }
    }
 
}
