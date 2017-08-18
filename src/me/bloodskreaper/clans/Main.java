package me.bloodskreaper.clans;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	private static ClanManager cm;

	public void onEnable(){
		cm = new ClanManager();
	}
	
	
	public static ClanManager getClanManager(){
		return cm;
	}

}
