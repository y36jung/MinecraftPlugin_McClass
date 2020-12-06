package me.midmad1.mcClass.classes.tamer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.midmad1.mcClass.Main;
import me.midmad1.mcClass.classes.tamer.customMobs.TamedZombie;
import net.minecraft.server.v1_16_R2.WorldServer;

public class TamerAbility implements Listener {
	
	public static ItemStack egg;
	public static ItemMeta eggMeta;
	public static ItemMeta inputMeta;
	public static int count = 0;
	public static List<Integer> inputs = new ArrayList<>();
	public static Map<Enchantment, Integer> enchant = new HashMap<>();
	
	public static void init() {
		getEgg();
	}
	
	public static void getEgg() {
		
		ItemStack e = new ItemStack(Material.HEART_OF_THE_SEA);
		ItemMeta meta = e.getItemMeta();
		
		meta.setDisplayName(ChatColor.DARK_AQUA + "Tamer's Mob");
		meta.addEnchant(Enchantment.LOYALTY, 675, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		List<String> lore = new ArrayList<>();
		
		inputMeta = meta;
		
		lore.add(ChatColor.DARK_PURPLE + "Input the correct click sequence");
		lore.add(ChatColor.DARK_PURPLE + "to spawn a tamed mob!");
		meta.setLore(lore);
		e.setItemMeta(meta);
		
		enchant.put(Enchantment.LOYALTY, 675);
		
		eggMeta = meta;
		egg = e;
	}
	
	public static void inputMeta(ItemStack item) {
		String displayName = "";
		if (count > 0) {
			if (count == 1) {
				if (inputs.get(0) == 0) {
					displayName = "L -> _ -> _ -> _";
				} else {
					displayName = "R -> _ -> _ -> _";
				}
			} else {
				if (inputs.get(1) == 0) {
					displayName.replaceFirst("_", "L");
				} else {
					displayName.replaceFirst("_", "R");
				}
			}
			inputMeta.setDisplayName(ChatColor.GOLD + displayName);
		} else {
			inputMeta.setDisplayName(ChatColor.DARK_AQUA + "Tamer's Mob");
		}
		item.setItemMeta(inputMeta);
	}
	
	
	@EventHandler
	public void clickSequence(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		
		int classInd = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId() + ".classInd");
		if (classInd == 4) {
			if (item.getEnchantments().equals(enchant)) {
				if ((event.getAction() == Action.LEFT_CLICK_AIR) || 
						(event.getAction() == Action.LEFT_CLICK_BLOCK))  {
					inputs.add(0);
				} else if ((event.getAction() == Action.RIGHT_CLICK_AIR) || 
						(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
					inputs.add(1);
				}
				++count;
				if (count == 4) {
					spawnMob(player, inputs);
					count = 0;
					inputs.clear();
				}
				inputMeta(item);
			} else {
				if (count != 0) {
					count = 0;
					inputs.clear();
					player.sendMessage("Input cancelled");
					item.setItemMeta(eggMeta);
				}
			}
		}
	}
	
	public void spawnMob(Player player, List<Integer> inputs) {
		Location loc = player.getLocation();
		WorldServer w = ((CraftWorld) player.getWorld()).getHandle();
		
		// Zombie
		if ((inputs.get(0) == 0) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 0)) {
			TamedZombie z = new TamedZombie(loc, player);
			w.addEntity(z);
			player.sendMessage("Zombie spawned.");
		} 
		
		// Skeleton
		if ((inputs.get(0) == 1) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 0)) {
			
		} 
		
		// Blaze
		if ((inputs.get(0) == 0) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 0)) {
			
		}
		
		// Silverfish
		if ((inputs.get(0) == 0) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 0)) {
			
		}
		
		// Fox
		if ((inputs.get(0) == 0) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 1)) {
			
		}
		
		// Snow golem
		if ((inputs.get(0) == 1) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 0)) {
		}
		
		// Iron golem
		if ((inputs.get(0) == 1) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 0)) {
		}
		
		// Chicken Jockey
		if ((inputs.get(0) == 1) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 1)) {
		}
		
		// Killer Rabbit
		if ((inputs.get(0) == 0) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 0)) {
		}
		
		// Horse
		if ((inputs.get(0) == 0) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 1)) {
		}
		
		// Spider Jockey
		if ((inputs.get(0) == 0) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 1)) {
		}
		
		// Wither Skeleton
		if ((inputs.get(0) == 1) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 0)) {
		}
		
		// Creeper
		if ((inputs.get(0) == 1) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 0) && (inputs.get(3) == 1)) {
		}
		
		// Bee
		if ((inputs.get(0) == 1) && (inputs.get(1) == 0) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 1)) {
		}
		
		// Dolphin
		if ((inputs.get(0) == 0) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 1)) {
		}
		
		// Strider
		if ((inputs.get(0) == 1) && (inputs.get(1) == 1) &&
				(inputs.get(2) == 1) && (inputs.get(3) == 1)) {
		}
	}
}

