package me.midmad1.mcClass.gameSystems;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import me.midmad1.mcClass.Main;

public class SpeedSystem implements Listener {
	
	double speedWater = 1D;
	
	public static void updateMoveSpd(Player player) {
		int speed = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".speed");
		AttributeInstance moveSpd = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
		double newMoveSpd = Math.log10(speed + 32) / 15;
		moveSpd.setBaseValue(newMoveSpd);
	}
	
	@EventHandler
	public void spOnJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		updateMoveSpd(player);
	}
}
