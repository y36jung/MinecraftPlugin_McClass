package me.midmad1.mcClass.classes.gambler;

import me.midmad1.mcClass.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;


public class GamblerAbility implements Listener {
	
	public static ItemStack luckyCoin; 
	
	public static Inventory inv;
	
	public static ItemStack[] items;
	
	public static ArrayList<String> itemNames;
	
	public static void init() {
		luckyCoin();
		itemList();
		itemNames();
	}
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int classInd = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".classInd");
		
			
		if (event.getItem() != null) {
			if ((event.getAction() == Action.RIGHT_CLICK_AIR) || 
					(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				if (event.getItem().getItemMeta().equals(GamblerAbility.luckyCoin.getItemMeta())) {
					if (classInd != 1) {
						player.sendMessage(ChatColor.DARK_RED + "Only Gamblers are allowed to use this item!");
					} else {
						GamblerAbility.flipCoin(player);
					}
				}
			}
		}
	}
	
	public static void luckyCoin() {
		ItemStack coin = new ItemStack(Material.GOLD_NUGGET, 1);
		ItemMeta coinMeta = coin.getItemMeta();
		coinMeta.setDisplayName(ChatColor.GOLD + "Lucky Coin");
		
		List<String> desc = new ArrayList<String>();
		desc.add(ChatColor.AQUA + "Right-click to use the lucky coin!");
		desc.add(ChatColor.DARK_PURPLE + "Obtain temporary stats");
		desc.add(ChatColor.DARK_PURPLE + "depending on your luck stat!");
		coinMeta.setLore(desc);
		coinMeta.addEnchant(Enchantment.LUCK, 1, false);
		coinMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		coin.setItemMeta(coinMeta);
		
		luckyCoin = coin;
	}
	
	public static void itemList() {
		ItemStack[] itemList = new ItemStack[10];
		
		itemList[0] = new ItemStack(Material.DIAMOND);
		itemList[1] = new ItemStack(Material.GRASS_BLOCK);
		itemList[2] = new ItemStack(Material.DIAMOND_PICKAXE);
		itemList[3] = new ItemStack(Material.ENDER_EYE);
		itemList[4] = new ItemStack(Material.POTION);
		itemList[5] = new ItemStack(Material.ANVIL);
		itemList[6] = new ItemStack(Material.LAVA_BUCKET);
		itemList[7] = new ItemStack(Material.CROSSBOW);
		itemList[8] = new ItemStack(Material.ENDER_PEARL);
		itemList[9] = new ItemStack(Material.WOODEN_HOE);
		
		items = itemList;
	}
	
	public static void itemNames() {
		ArrayList<String> nameList = new ArrayList<String>();
		for (ItemStack item: GamblerAbility.items) {
			String name = "";
			if (item.hasItemMeta()) {
				name = item.getItemMeta().getDisplayName();
			} else {
				name = item.getType().toString().replace('_', ' ');
			}
			nameList.add(name);
		}
		itemNames = nameList;
	}
	
	public static Integer nextEmptySlot(Player player) {
		for (int i = 0; i < 36; ++i) {
			if (player.getInventory().getItem(i) == null) {
				return i;
			}
		}
		return -1;
	}
	
	public static void flipCoin(Player player) {
		
		if (nextEmptySlot(player) == -1) {
			player.sendMessage(ChatColor.DARK_RED + "Your inventory is too full to flip the Lucky Coin!");
			return;
		}
		
		Inventory inventory = player.getInventory();
		Random r = new Random();
		int amountCoin = player.getInventory().getItemInMainHand().getAmount();
		if (amountCoin > 1) {
			player.getInventory().getItemInMainHand().setAmount(amountCoin - 1);
		}
		int mainIndex = nextEmptySlot(player);
		
		player.sendMessage(ChatColor.GOLD + "Flipping the Lucky Coin...");
		
		
		int luck = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + "luck");
		
		double luckVar = 0.0;
		
		if (luck > 0) {
			luckVar = Math.log10(luck) / 20;
		}
		
		double randD = r.nextDouble();
		int chosen;
		if (randD <= 0.05 + luckVar) {
			chosen = r.nextInt(3);
		} else if ((randD > 0.05 + luckVar) && (randD <= 0.95 + (luckVar / 5))) {
			int[] indices = {3, 4, 5, 6};
			chosen = indices[r.nextInt(4)];
		} else {
			int[] indices = {7, 8, 9};
			chosen = indices[r.nextInt(3)];
		}

		new BukkitRunnable() {
			int count = 5;

			@Override
			public void run() {
				
				int ind = r.nextInt(10);
				
				count--;
				if (count >= 0) {
					inventory.setItem(mainIndex, GamblerAbility.items[ind]);
					if (count == 0) {
						player.sendMessage(ChatColor.GOLD + "You have acquired a " + 
					GamblerAbility.itemNames.get(chosen) + "!");
					}
				} else {
					cancel();
				}
			}
		}.runTaskTimer(Main.getPlugin(Main.class), 0, 10);
	}
	
}

