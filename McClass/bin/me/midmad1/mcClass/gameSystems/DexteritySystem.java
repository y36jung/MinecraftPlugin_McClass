package me.midmad1.mcClass.gameSystems;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.midmad1.mcClass.Main;

public class DexteritySystem implements Listener {
	
	public static void updateDex(Player player) {
		int dexterity = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".dexterity");
		double incAS = 0.1 * (dexterity / 10);
		AttributeInstance attSpd = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
		double currAttSpd = attSpd.getDefaultValue();
		double newAttSpd = currAttSpd + incAS;
		attSpd.setBaseValue(newAttSpd);
	}
	
	@EventHandler
	public void dexOnJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		updateDex(player);
	}
}
