package me.midmad1.mcClass.classes.fisherman;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import me.midmad1.mcClass.Main;

public class FishermanAbility implements Listener {
	
	public static ItemStack hook;
	
	public static void init() {
		getHook();
	}
	
	public static void getHook() {
		ItemStack rod = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = rod.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Fisherman's Hook");
		meta.addEnchant(Enchantment.LUCK, 1, false);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.DARK_AQUA + "Right-Click to use this item!");
		
		meta.setLore(lore);
		rod.setItemMeta(meta);
		
		hook = rod;
	}
	
	@EventHandler
	public void onRightClickHook(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		int classInd = Main.getPlugin(Main.class).data.getConfig().getInt("players." + player.getUniqueId().toString() + ".classInd");
		if (classInd == 3) {
			if (player.getInventory().getItemInMainHand().equals(hook)) {
				if ((event.getAction() == Action.RIGHT_CLICK_AIR) 
						|| (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
				}
			}
		}
	}
	
	@EventHandler
	public void onHitPlayer(PlayerFishEvent event) {
		
		Player player = event.getPlayer();
		Vector zero = new Vector(0, 0, 0);
		if (player.getInventory().getItemInMainHand().equals(hook)) {
			if (event.getCaught() instanceof Player) {
				Player caught = (Player) event.getCaught();
				Vector caughtVec = caught.getLocation().toVector();
				Vector playerVec = player.getLocation().toVector();
				Vector v = playerVec.subtract(caughtVec);
				caught.setVelocity(v.normalize());
				
			} else if (event.getCaught() instanceof Entity) {
				Entity caught = (Entity) event.getCaught();
				Vector caughtVec = caught.getLocation().toVector();
				Vector playerVec = player.getLocation().toVector();
				Vector v = playerVec.subtract(caughtVec);
				caught.setVelocity(v.normalize());
				
			} else if (event.getHook().getVelocity().equals(zero))  {
				Vector caughtVec = event.getHook().getLocation().toVector();
				Vector playerVec = player.getLocation().toVector();
				Vector v = caughtVec.subtract(playerVec);
				player.setVelocity(v.normalize().multiply(3));
			}
		}
	}
	
	@EventHandler
	public void hookSpeed(PlayerFishEvent event) {
		Player player = event.getPlayer();
		if (player.getInventory().getItemInMainHand().equals(hook)) {
			Vector hookSpeed = event.getHook().getVelocity();
			Vector newSpeed = hookSpeed.multiply(3);
			event.getHook().setVelocity(newSpeed);
		}
	}
}
