package me.bloodskreaper.cf_clans;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.bloodskreaper.cf_clans.clansystem.ClanManager;
import me.bloodskreaper.cf_clans.clansystem.InviteManager;
import me.bloodskreaper.cf_clans.commands.AcceptInviteCommand;
import me.bloodskreaper.cf_clans.commands.ClanMainCommand;
import me.bloodskreaper.cf_clans.commands.CommandHandler;
import me.bloodskreaper.cf_clans.commands.CreateClanCommand;
import me.bloodskreaper.cf_clans.commands.DeleteClanCommand;
import me.bloodskreaper.cf_clans.commands.DenyInviteCommand;
import me.bloodskreaper.cf_clans.commands.HelpCommand;
import me.bloodskreaper.cf_clans.commands.InviteMemberCommand;
import me.bloodskreaper.cf_clans.commands.LeaveClanCommand;
import me.bloodskreaper.cf_clans.commands.RemoveMemberCommand;
import me.bloodskreaper.cf_clans.commands.SetLeaderCommand;
import me.bloodskreaper.cf_clans.commands.SetClanPrefixCommand;
import me.bloodskreaper.cf_clans.data.ClanFileHandler;
import me.bloodskreaper.cf_clans.data.InviteFileHandler;
import me.bloodskreaper.cf_clans.listeners.AsycPlayerChatEventListener;
import me.bloodskreaper.cf_clans.listeners.PlayerJoinEventListener;

public class CF_Clans extends JavaPlugin {

	public static String prefix = "§b~§a+§c-§b~§a+§c-§b~§a+§c-§b~§a+§c-§b~§a+§c-§b~§a+§c-§8[§6CF-Clans§8]§c-§a+§b~§c-§a+§b~§c-§a+§b~§c-§a+§b~§c-§a+§b~§c-§a+§b~";
	private static ClanManager cm;
	private static InviteManager im;
	private ClanFileHandler cl;
	private InviteFileHandler in;

	public void onEnable() {
		cm = new ClanManager();
		cl = new ClanFileHandler(this);
		cl.LoadClanData();

		im = new InviteManager();
		in = new InviteFileHandler(this);
		in.LoadInviteData();

		getServer().getPluginManager().registerEvents(new PlayerJoinEventListener(this), this);
		getServer().getPluginManager().registerEvents(new AsycPlayerChatEventListener(), this);

		registerCommands();
		startScheduler();
	}

	public void onDisable() {
		cl.SaveToYamlConfig(false);
		in.SaveToYamlConfig(false);

	}

	public static ClanManager getClanManager() {
		return cm;
	}

	public static InviteManager getInviteManager() {
		return im;
	}

	private void registerCommands() {
		CommandHandler handler = new CommandHandler();

		/*
		 * Commands: CreateClan InviteMember AcceptInvite DenyInvite RemoveClan
		 * RemoveMember SetLeader SetPrefix
		 */
		handler.register("clan", new ClanMainCommand());
		handler.register("delete", new DeleteClanCommand());
		handler.register("invite", new InviteMemberCommand());
		handler.register("create", new CreateClanCommand());
		handler.register("remove", new RemoveMemberCommand());
		handler.register("leader", new SetLeaderCommand());
		handler.register("prefix", new SetClanPrefixCommand());
		handler.register("leave", new LeaveClanCommand());
		handler.register("acceptinvite", new AcceptInviteCommand());
		handler.register("denyinvite", new DenyInviteCommand());
		handler.register("help", new HelpCommand());

		getCommand("clan").setExecutor(handler);
	}

	public static void sendMessageToPlayer(Player p, String message) {
		p.sendMessage("");
		p.sendMessage(prefix);
		p.sendMessage(message);
	}

	private void startScheduler() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				im.checkForExpiredInvites();
				in.SaveToYamlConfig(true);
				cl.SaveToYamlConfig(true);
			}
		}, 0L, 20 * 10L);
	}

}
