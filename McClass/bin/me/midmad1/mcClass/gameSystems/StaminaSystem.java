package me.midmad1.mcClass.gameSystems;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.midmad1.mcClass.Main;

public class StaminaSystem implements Listener {
	
	public static void updateHealth(Player player) {
		int stamina = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".stamina");
		int addHealth = stamina / 2;
		AttributeInstance healthAtt = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
		double currMaxHealth = healthAtt.getDefaultValue();
		double newMaxHealth = currMaxHealth + addHealth;
		healthAtt.setBaseValue(newMaxHealth);
	}
	
	@EventHandler
	public void stOnJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		updateHealth(player);
	}
}
