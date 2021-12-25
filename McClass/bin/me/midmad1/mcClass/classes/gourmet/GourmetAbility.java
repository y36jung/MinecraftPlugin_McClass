package me.midmad1.mcClass.classes.gourmet;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

import me.midmad1.mcClass.Main;

public class GourmetAbility implements Listener {
	
	// Based on Nourishment values in Java Edition
	static List<Material> superFoods = new ArrayList<>();
	static List<Material> goodFoods = new ArrayList<>();
	static List<Material> normalFoods = new ArrayList<>();
	static List<Material> lowFoods = new ArrayList<>();
	static List<Material> poorFoods = new ArrayList<>();
	
	public static void init() {
		getSuperFoods();
		getGoodFoods();
		getNormalFoods();
		getPoorFoods();
	}
	
	public static void getSuperFoods() {
		superFoods.add(Material.ENCHANTED_GOLDEN_APPLE);
		superFoods.add(Material.GOLDEN_APPLE);
		superFoods.add(Material.GOLDEN_CARROT);
	}
	
	public static void getGoodFoods() {
		goodFoods.add(Material.COOKED_MUTTON);
		goodFoods.add(Material.COOKED_PORKCHOP);
		goodFoods.add(Material.COOKED_SALMON);
		goodFoods.add(Material.COOKED_BEEF);
	}
	
	public static void getNormalFoods() {
		normalFoods.add(Material.BAKED_POTATO);
		normalFoods.add(Material.BEETROOT_SOUP);
		normalFoods.add(Material.BEETROOT);
		normalFoods.add(Material.BREAD);
		normalFoods.add(Material.CARROT);
		normalFoods.add(Material.COOKED_CHICKEN);
		normalFoods.add(Material.COOKED_COD);
		normalFoods.add(Material.COOKED_RABBIT);
		normalFoods.add(Material.MUSHROOM_STEW);
		normalFoods.add(Material.RABBIT_STEW);
		normalFoods.add(Material.SUSPICIOUS_STEW);
	}
	
	public void getLowFoods() {
		lowFoods.add(Material.APPLE);
		lowFoods.add(Material.CHORUS_FRUIT);
		lowFoods.add(Material.DRIED_KELP);
		lowFoods.add(Material.MELON_SLICE);
		lowFoods.add(Material.POISONOUS_POTATO);
		lowFoods.add(Material.POTATO);
		lowFoods.add(Material.PUMPKIN_PIE);
		lowFoods.add(Material.BEEF);
		lowFoods.add(Material.CHICKEN);
		lowFoods.add(Material.MUTTON);
		lowFoods.add(Material.PORKCHOP);
		lowFoods.add(Material.RABBIT);
	}
	
	public static void getPoorFoods() {
		poorFoods.add(Material.CAKE);
		poorFoods.add(Material.COOKIE);
		poorFoods.add(Material.HONEY_BOTTLE);
		poorFoods.add(Material.PUFFERFISH);
		poorFoods.add(Material.COD);
		poorFoods.add(Material.SALMON);
		poorFoods.add(Material.ROTTEN_FLESH);
		poorFoods.add(Material.SPIDER_EYE);
		poorFoods.add(Material.SWEET_BERRIES);
		poorFoods.add(Material.TROPICAL_FISH);
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int classInd = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".classInd");
		if (classInd == 2) {
			if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				if (player.getInventory().getItemInMainHand().getType().isEdible()) {
					if (player.getFoodLevel() == 20) {
						player.setFoodLevel(19);
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void onEat(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		Material food = event.getItem().getType();
		int classInd = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".classInd");
		if (classInd == 2) {
			if (superFoods.contains(food)) {
				
			} else if (goodFoods.contains(food)) {
				if (food.equals(Material.COOKED_MUTTON)) {
					if () {
						player.addPotionEffectType(PotionEffectType.BAD_OMEN.createEffect(6000, 0));
						player.addPotionEffectType(PotionEffectType.DAMAGE_RESISTANCE.createEffect(6000, 3));
						player.addPotionEffectType(PotionEffectType.FIRE_RESISTANCE.createEffect(6000, 0));
						player.addPotionEffectType(PotionEffectType.GLOWING.createEffect(6000, 0));
						player.addPotionEffectType(PotionEffectType.HEALTH_BOOST.createEffect(6000, 10));
						player.addPotionEffectType(PotionEffectType.INCREASE_DAMAGE.createEffect(6000, 3));
					}
				}
				if (food.equals(Material.COOKED_PORKCHOP)) {
					player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(600, 2));
					player.addPotionEffect(PotionEffectType.ABSORPTION.createEffect(600, 6));
					player.addPotionEffect(PotionEffectType.SLOW.createEffect(600, 1));
				}
				if (food.equals(Material.COOKED_SALMON)) {
					player.addPotionEffect(PotionEffectType.WATER_BREATHING.createEffect(600, 0));
					player.addPotionEffect(PotionEffectType.DOLPHINS_GRACE.createEffect(600, 0));
				}
				if (food.equals(Material.COOKED_BEEF)) {
					player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(600, 2));
					player.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(600, 1));
				}
			} else if (lowFoods.contains(food)) {
							
			} else if (poorFoods.contains(food)) {
							
			}
		}
	}
}
