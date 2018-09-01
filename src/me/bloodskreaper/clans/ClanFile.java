package me.bloodskreaper.clans;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ClanFile {
	private File datafile;
	private YamlConfiguration data;
	private Plugin pl;
	
	public ClanFile(Plugin plugin) {
		pl = plugin;
		datafile = new File(pl.getDataFolder(), "ClanData.yml");
		if(!datafile.exists() == true && datafile.isDirectory()) {
			try {
				datafile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		data = YamlConfiguration.loadConfiguration(datafile);
		}
	
	public void LoadClanData() {
		Set<String> clandata = data.getKeys(false);
		if(clandata.size()!=0) {
			for(String uuid: clandata) {
				Clan created = new Clan(UUID.fromString(uuid), data.getString(uuid+".name"), data.getString(uuid+".displayname"), UUID.fromString(data.getString(uuid+".leader")), data.getStringList(uuid+".members"));
				Main.getClanManager().addClan(created);
			}
		}
	}
	
	public void SaveToYamlConfig(boolean async) {
		List<Clan> clans = Main.getClanManager().getClans();
		for(String s:data.getKeys(false)) {
			data.set(s, null);
		}
		for(Clan c: clans) {
			String path = c.getClanUUID().toString()+".";
			data.set(path+"name", c.getName());
			data.set(path+"displayname", c.getClanDisplayName());
			data.set(path+"leader", c.getLeader().toString());
			data.set(path+"members", c.getMembers());
		}
		saveConfigFile(async);
	}
	private void saveConfigFile(boolean async) {
		if(async == true) {
		Bukkit.getScheduler().runTaskAsynchronously(pl, new Runnable() {
			@Override
			public void run() {
				try {
					data.save(datafile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		}else {
			try {
				data.save(datafile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
