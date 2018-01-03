package me.bloodskreaper.clans;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.bloodskreaper.clans.Commands.AcceptInvite;
import me.bloodskreaper.clans.Commands.ClanMainCommand;
import me.bloodskreaper.clans.Commands.CommandHandler;
import me.bloodskreaper.clans.Commands.CreateClan;
import me.bloodskreaper.clans.Commands.DenyInvite;
import me.bloodskreaper.clans.Commands.InviteMember;
import me.bloodskreaper.clans.Commands.LeaveClan;
import me.bloodskreaper.clans.Commands.DeleteClan;
import me.bloodskreaper.clans.Commands.RemoveMember;
import me.bloodskreaper.clans.Commands.SetLeader;
import me.bloodskreaper.clans.Commands.SetPrefix;

public class Main extends JavaPlugin{
	public static String prefix = "§b~§a+§c-§b~§a+§c-§b~§a+§c-§b~§a+§c-§b~§a+§c-§b~§a+§c-§8[§6CF-Clans§8]§c-§a+§b~§c-§a+§b~§c-§a+§b~§c-§a+§b~§c-§a+§b~§c-§a+§b~";
	private static ClanManager cm;
	private static InviteManager im;
	private ClanFile cl;
	private InviteFile in;

	public void onEnable(){
		cm = new ClanManager();
		cl = new ClanFile(this);
		cl.LoadClanData();
		
		im = new InviteManager();
		in = new InviteFile(this);
		in.LoadInviteData();
		
		
		this.registerCommands();
	}
	
	public void onDisable() {
		cl.SaveToYamlConfig(false);
		in.SaveToYamlConfig(false);
		
	}
	
	
	public static ClanManager getClanManager(){
		return cm;
	}
	
	public static InviteManager getInviteManager() {
		return im;
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
        handler.register("delete", new DeleteClan());
        handler.register("invite", new InviteMember());
        handler.register("create", new CreateClan());
        handler.register("remove", new RemoveMember());
        handler.register("leader", new SetLeader());
        handler.register("prefix", new SetPrefix());
        handler.register("leave", new LeaveClan());
        handler.register("acceptinvite", new AcceptInvite());
        handler.register("denyinvite", new DenyInvite());
 
        //Registers the command /example args based on args[0] (args)
        getCommand("clan").setExecutor(handler);
    }
    
    public static void sendMessageToPlayer(Player p, String message) {
    	p.sendMessage("");
    	p.sendMessage(prefix);
    	p.sendMessage(message);
    }

}
