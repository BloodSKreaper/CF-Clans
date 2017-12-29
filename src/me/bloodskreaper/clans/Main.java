package me.bloodskreaper.clans;

import org.bukkit.plugin.java.JavaPlugin;

import me.bloodskreaper.clans.Commands.ClanMainCommand;
import me.bloodskreaper.clans.Commands.CommandHandler;
import me.bloodskreaper.clans.Commands.CreateClan;
import me.bloodskreaper.clans.Commands.InviteMember;
import me.bloodskreaper.clans.Commands.RemoveClan;
import me.bloodskreaper.clans.Commands.RemoveMember;
import me.bloodskreaper.clans.Commands.SetLeader;
import me.bloodskreaper.clans.Commands.SetPrefix;

public class Main extends JavaPlugin{
	private static ClanManager cm;
	private ClanFile cl;

	public void onEnable(){
		cm = new ClanManager();
		cl = new ClanFile(this);
		cl.LoadClanData();
		this.registerCommands();
	}
	
	public void onDisable() {
		
	}
	
	
	public static ClanManager getClanManager(){
		return cm;
	}
	
    public void registerCommands() {
    	 
        //For convenience' sake, we will initialize a variable.
        CommandHandler handler = new CommandHandler();

        /*Commands:
         * CreateClan
         * InviteMember
         * AcceptInvite
         * DenyInvite
         * RemoveClan
         * RemoveMember
         * SetLeader
         * SetPrefix
         */
        //Registers the command /example which has no arguments.
        handler.register("clan", new ClanMainCommand());
        handler.register("delete", new RemoveClan());
        handler.register("invite", new InviteMember());
        handler.register("create", new CreateClan());
        handler.register("remove", new RemoveMember());
        handler.register("leader", new SetLeader());
        handler.register("prefix", new SetPrefix());
        handler.register("remove", new RemoveClan());
 
        //Registers the command /example args based on args[0] (args)
        getCommand("clan").setExecutor(handler);
    }

}
