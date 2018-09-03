package me.bloodskreaper.cf_clans.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Clan;

public class ClanFileHandler {
	private File datafile;
	private YamlConfiguration data;
	private Plugin pl;

	public ClanFileHandler(Plugin plugin) {
		pl = plugin;
		datafile = new File(pl.getDataFolder(), "ClanData.yml");
		if (!datafile.exists() == true && datafile.isDirectory()) {
			try {
				datafile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		data = YamlConfiguration.loadConfiguration(datafile);
	}

	public void loadClanData() {
		Set<String> clandata = data.getKeys(false);
		if (clandata.size() != 0) {
			for (String uuid : clandata) {
				List<UUID> members = new ArrayList<UUID>();
				for (String s : data.getStringList(uuid + ".members")) {
					members.add(UUID.fromString(s));
				}
				Clan created = new Clan(UUID.fromString(uuid), data.getString(uuid + ".name"),
						data.getString(uuid + ".displayname"), UUID.fromString(data.getString(uuid + ".leader")),
						members);
				CF_Clans.getClanManager().addClan(created);
			}
		}
	}

	public void saveToYamlConfig(boolean async) {
		List<Clan> clans = CF_Clans.getClanManager().getClans();
		for (String s : data.getKeys(false)) {
			data.set(s, null);
		}
		for (Clan c : clans) {
			String path = c.getClanUUID().toString() + ".";
			data.set(path + "name", c.getName());
			data.set(path + "displayname", c.getClanDisplayName());
			data.set(path + "leader", c.getLeader().toString());
			List<String> members = new ArrayList<String>();
			for(UUID uuid: c.getMembers()) {
				members.add(uuid.toString());
			}
			data.set(path + "members", members);
		}
		saveConfigFile(async);
	}

	private void saveConfigFile(boolean async) {
		if (async == true) {
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
		} else {
			try {
				data.save(datafile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
