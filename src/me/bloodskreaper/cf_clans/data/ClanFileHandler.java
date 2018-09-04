package me.bloodskreaper.cf_clans.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
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
				ConfigurationSection section = data.getConfigurationSection(uuid);
				List<UUID> members = new ArrayList<>();
				for (String s : section.getStringList("members")) {
					members.add(UUID.fromString(s));
				}
				Clan created = new Clan(UUID.fromString(uuid), section.getString("name"),
						section.getString("displayname"), UUID.fromString(section.getString("leader")), members);
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
			ConfigurationSection section = data.createSection(c.getClanUUID().toString());
			section.set("name", c.getName());
			section.set("displayname", c.getClanDisplayName());
			section.set("leader", c.getLeader().toString());
			List<String> members = new ArrayList<>();
			for (UUID uuid : c.getMembers()) {
				members.add(uuid.toString());
			}
			section.set("members", members);
		}
		saveConfigFile(async);
	}

	private void saveConfigFile(boolean async) {
		if (async) {
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
