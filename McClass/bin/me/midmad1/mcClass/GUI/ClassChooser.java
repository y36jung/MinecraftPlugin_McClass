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
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.midmad1.mcClass.classes.gambler.GamblerChoose;
import me.midmad1.mcClass.classes.gourmet.GourmetChoose;
import net.md_5.bungee.api.ChatColor;

public class ClassChooser implements Listener {
	
	public static Inventory classes;
	
	public static void init() {
		createInventory();
	}
	
	public static void createInventory() {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.BOLD + "Choose your class!");
		
		// Gambler
		ItemStack gambler = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta meta = gambler.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Gambler");
		List<String> desc = new ArrayList<>();
		desc.add(ChatColor.GREEN + "Ability: Lucky Coin");
		desc.add(ChatColor.BOLD + "Flip the Lucky Coin");
		desc.add(ChatColor.BOLD + "bonus items or stats!");
		meta.setLore(desc);
		meta.addEnchant(Enchantment.LUCK, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		gambler.setItemMeta(meta);
		
		ItemStack gourmet = new ItemStack(Material.COOKED_BEEF);
		meta.setDisplayName(ChatColor.BLACK + "Gourmet");
		desc.clear();
		desc.add(ChatColor.GREEN + "Ability: Food Effects");
		desc.add(ChatColor.BOLD + "Eating different foods gives");
		desc.add(ChatColor.BOLD + "different bonus buffs depending");
		desc.add(ChatColor.BOLD + "on the nourishment level of the food!");
		meta.setLore(desc);
		gourmet.setItemMeta(meta);
		
		inv.setItem(4, gambler);
		inv.setItem(5,  gourmet);
		
		classes = inv;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().contains("Choose your class!")) {
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
		
		if (event.getSlot() == 4) {
			// Gambler Class
			GamblerChoose.execute(player);
			player.closeInventory();
			player.updateInventory();
		}
		
		if (event.getSlot() == 5) {
			// Gourmet Class
			GourmetChoose.execute(player);
			player.closeInventory();
			player.updateInventory();
		}
	}

}
