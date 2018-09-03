package me.bloodskreaper.cf_clans.data;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.bloodskreaper.cf_clans.CF_Clans;
import me.bloodskreaper.cf_clans.clansystem.Invite;

public class InviteFileHandler {
	private File datafile;
	private YamlConfiguration data;
	private Plugin pl;

	public InviteFileHandler(Plugin plugin) {
		pl = plugin;
		datafile = new File(pl.getDataFolder(), "InviteFile.yml");
		if (!datafile.exists() == true && datafile.isDirectory()) {
			try {
				datafile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		data = YamlConfiguration.loadConfiguration(datafile);
	}

	public void loadInviteData() {
		Set<String> inviteuuids = data.getKeys(false);
		if (inviteuuids.size() != 0) {
			for (String uuid : inviteuuids) {
				if (CF_Clans.getClanManager()
						.getClanFromUUID(UUID.fromString(data.getString(uuid + ".clan"))) != null) {

					Invite created = new Invite(UUID.fromString(uuid), data.getLong(uuid + ".created"),
							UUID.fromString(data.getString(uuid + ".player")),
							CF_Clans.getClanManager().getClanFromUUID(UUID.fromString(data.getString(uuid + ".clan"))));
					CF_Clans.getInviteManager().addInvite(created);
				}
			}
		}
	}

	public void saveToYamlConfig(boolean async) {
		List<Invite> invites = CF_Clans.getInviteManager().getInvites();
		for (String s : data.getKeys(false)) {
			data.set(s, null);
		}
		for (Invite in : invites) {
			String path = in.getInviteUUID().toString() + ".";
			data.set(path + "created", in.getCreated());
			data.set(path + "player", in.getPlayer().toString());
			data.set(path + "clan", in.getClan().getClanUUID().toString());
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
