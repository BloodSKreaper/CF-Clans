package me.bloodskreaper.clans.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bloodskreaper.clans.Main;

public class HelpCommand implements CommandInterface{

	 
    @Override
    public boolean onCommand(CommandSender sender, Command cmd,
            String commandLabel, String[] args) {
        Player p = (Player) sender;
        Main.sendMessageToPlayer(p, "§aClan-Hilfe:");
        String commandcolor= "§6";
        String descriptioncolor = "§f";
        /*Commands:
         * createclan
         * setprefix
         * invitemember
         * acceptinvite
         * denyinvite
         * leaveclan
         * removemember
         * deleteclan
         * setlleader
         */
        p.sendMessage(commandcolor+"/clan create <CLANNAME>"+descriptioncolor+": Einen Clan mit dem Namen <NAME> erstellen.");
        p.sendMessage(commandcolor+"/clan prefix <PREFIX>"+descriptioncolor+": Den Prefix des Clans ändern.");
        p.sendMessage(commandcolor+"/clan invite <SPIELERNAME>"+descriptioncolor+": Einen Spieler in einen Clan einladen.");
        p.sendMessage(commandcolor+"/clan acceptinvite <CLANNAME>"+descriptioncolor+": Eine Einladung eines Clans annehmen.");
        p.sendMessage(commandcolor+"/clan denyinvite <CLANNAME>"+descriptioncolor+": Eine Einladung eines Clans ablehnen.");
        p.sendMessage(commandcolor+"/clan leave"+descriptioncolor+": Einen Clan verlassen.");
        p.sendMessage(commandcolor+"/clan remove <SPIELERNAME>"+descriptioncolor+": Einen Spieler aus dem Clan werfen.");
        p.sendMessage(commandcolor+"/clan delete"+descriptioncolor+": Einen Clan löschen.");
        p.sendMessage(commandcolor+"/clan leader <SPIELERNAME>"+descriptioncolor+": Einen Clanmember zum Admin machen.");
    return false;
    }

}
