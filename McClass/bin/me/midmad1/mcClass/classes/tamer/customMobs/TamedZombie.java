package me.midmad1.mcClass.classes.tamer.customMobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityHuman;
import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.EntityZombie;
import net.minecraft.server.v1_16_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R2.PathfinderGoalLookAtPlayer;

public class TamedZombie extends EntityZombie {
	
	public static Map<Player, EntityZombie> tamed = new HashMap<>();
	public static List<String> names = new ArrayList<>();
	
	public TamedZombie(Location loc, Player player) {
		super(((CraftWorld) loc.getWorld()).getHandle());
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		
		this.setGoalTarget((EntityLiving) ((CraftPlayer) player).getHandle(),
				TargetReason.CUSTOM, true);
		
		String name = player.getDisplayName() + "'s Zombie";
		
		this.setCustomName(new ChatComponentText(ChatColor.DARK_AQUA + name));
		
		tamed.put(player, this);
		names.add(name);
	}
	
	@Override
	public void initPathfinder() {
		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalTamed(this, 1.5, 15));
		this.goalSelector.a(2, new PathfinderGoalLookAtPlayer(this, 
				EntityHuman.class, 8.0F));
	}
}
