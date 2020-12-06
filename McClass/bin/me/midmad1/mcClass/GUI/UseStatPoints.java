package me.midmad1.mcClass.GUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.midmad1.mcClass.Main;
import me.midmad1.mcClass.gameSystems.DexteritySystem;
import me.midmad1.mcClass.gameSystems.SpeedSystem;
import me.midmad1.mcClass.gameSystems.StaminaSystem;
import net.md_5.bungee.api.ChatColor;

public class UseStatPoints implements Listener {
	
	public static Inventory statInv;
	public static Integer[] statArray;
	
	public static void createInventory() {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "Level your stats up!");
		
		ItemStack strItem = new ItemStack(Material.IRON_SWORD);
		ItemMeta meta = strItem.getItemMeta();
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Strength");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.ITALIC + "Click here to");
		lore.add(ChatColor.ITALIC + "level this stat up!");
		meta.setLore(lore);
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		strItem.setItemMeta(meta);
		
		ItemStack stItem = new ItemStack(Material.IRON_CHESTPLATE);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Stamina");
		stItem.setItemMeta(meta);
		
		ItemStack spItem = new ItemStack(Material.IRON_BOOTS);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Speed");
		spItem.setItemMeta(meta);
		
		ItemStack dexItem = new ItemStack(Material.POTION);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dexterity");
		meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		dexItem.setItemMeta(meta);
		
		ItemStack luckItem = new ItemStack(Material.GOLD_INGOT);
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Luck");
		luckItem.setItemMeta(meta);
		
		ItemStack statItem = new ItemStack(Material.EXPERIENCE_BOTTLE);
		inv.setItem(0, strItem);
		inv.setItem(1, stItem);
		inv.setItem(2, spItem);
		inv.setItem(3, dexItem);
		inv.setItem(4, luckItem);
		inv.setItem(8, statItem);
		
		statInv = inv;
	}
	
	public static void createArray(Player player) {
		
		Integer[] arrayOfStats = new Integer[6];
		
		int strength = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".strength");
		int stamina = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".stamina");
		int speed = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".speed");
		int dexterity = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".dexterity");
		int luck = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".luck");
		int statPoints = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".statPoints");
		
		arrayOfStats[0] = strength;
		arrayOfStats[1] = stamina;
		arrayOfStats[2] = speed;
		arrayOfStats[3] = dexterity;
		arrayOfStats[4] = luck;
		arrayOfStats[5] = statPoints;
		
		statArray = arrayOfStats;
	}
	
	public static void numOfPoints(ItemStack stat, String desc, int col, int ind) {
		ItemMeta numOfPoints = stat.getItemMeta();
		numOfPoints.setDisplayName(desc + statArray[ind]);
		stat.setItemMeta(numOfPoints);
		statInv.setItem(col, stat);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains("Level your stats up!")) {
			return;
		}
		
		if (event.getCurrentItem() == null) {
			return;
		}
		
		if (event.getCurrentItem().getItemMeta() == null) {
			return;
		}
		
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if (event.getClickedInventory().getType() == InventoryType.PLAYER) {
			return;
		}
		
		if (event.getSlot() == 0) {
			if (statArray[5] <= 0) {
				player.closeInventory();
				player.updateInventory();
				player.sendMessage(ChatColor.RED + "You do not have enough Stat Points to level this stat up!");
				return;
			}
			// Increase strength
			++statArray[0];
			--statArray[5];
			numOfPoints(statInv.getItem(0), "Strength: ", 0, 0);
			numOfPoints(statInv.getItem(8), "Number of Stat Points Remaining: ", 8, 5);
			player.updateInventory();
		}
		
		if (event.getSlot() == 1) {
			if (statArray[5] <= 0) {
				player.closeInventory();
				player.updateInventory();
				player.sendMessage(ChatColor.RED + "You do not have enough Stat Points to level this stat up!");
				return;
			}
			// Increase stamina
			++statArray[1];
			--statArray[5];
			numOfPoints(statInv.getItem(1), "Stamina: ", 1, 1);
			numOfPoints(statInv.getItem(8), "Number of Stat Points Remaining: ", 8, 5);
			player.updateInventory();
		}
		
		if (event.getSlot() == 2) {
			if (statArray[5] <= 0) {
				player.closeInventory();
				player.updateInventory();
				player.sendMessage(ChatColor.RED + "You do not have enough Stat Points to level this stat up!");
				return;
			}
			// Increase speed
			++statArray[2];
			--statArray[5];
			numOfPoints(statInv.getItem(2), "Speed: ", 2, 2);
			numOfPoints(statInv.getItem(8), "Number of Stat Points Remaining: ", 8, 5);
			player.updateInventory();
		}
		
		if (event.getSlot() == 3) {
			if (statArray[5] <= 0) {
				player.closeInventory();
				player.updateInventory();
				player.sendMessage(ChatColor.RED + "You do not have enough Stat Points to level this stat up!");
				return;
			}
			// Increase dexterity
			++statArray[3];
			--statArray[5];
			numOfPoints(statInv.getItem(3), "Dexterity: ", 3, 3);
			numOfPoints(statInv.getItem(8), "Number of Stat Points Remaining: ", 8, 5);
			player.updateInventory();
		}
		
		if (event.getSlot() == 4) {
			if (statArray[5] <= 0) {
				player.closeInventory();
				player.updateInventory();
				player.sendMessage(ChatColor.RED + "You do not have enough Stat Points to level this stat up!");
				return;
			}
			// Increase luck
			++statArray[4];
			--statArray[5];
			numOfPoints(statInv.getItem(4), "Luck: ", 4, 4);
			numOfPoints(statInv.getItem(8), "Number of Stat Points Remaining: ", 8, 5);
			player.updateInventory();
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		
		Player player = (Player) event.getPlayer();
		
		if (event.getInventory().equals(statInv)) {
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".strength", statArray[0]);
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".stamina", statArray[1]);
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".speed", statArray[2]);
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".dexterity", statArray[3]);
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".luck", statArray[4]);
			Main.getPlugin(Main.class).data.getConfig().set("players." + player.getUniqueId().toString() + ".statPoints", statArray[5]);
		}
		StaminaSystem.updateHealth(player);
		DexteritySystem.updateDex(player);
		SpeedSystem.updateMoveSpd(player);
	}
}
