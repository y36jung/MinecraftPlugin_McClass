package me.midmad1.mcClass.gameSystems;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import me.midmad1.mcClass.Main;

public class ExpSystem implements Listener {
	
	@EventHandler
	public void updateLevel(PlayerLevelChangeEvent event) {
		Player player = event.getPlayer();
		int level = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".level");
		int statPoints = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".statPoints");
		if (event.getNewLevel() > level) {
			statPoints += event.getNewLevel() - level;
			level = event.getNewLevel();
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".level", level);
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".statPoints", statPoints);
			Main.getPlugin(Main.class).data.saveConfig();
		}
	}
}
