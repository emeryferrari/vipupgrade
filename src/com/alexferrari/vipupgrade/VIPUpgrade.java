package com.alexferrari.vipupgrade;
import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import com.greatmancode.craftconomy3.*;
import com.greatmancode.craftconomy3.tools.interfaces.Loader;
public class VIPUpgrade extends JavaPlugin {
	@Override
	public void onEnable() {}
	@Override
	public void onDisable() {}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("buyvip")) {
			if (sender instanceof Player) {
				Plugin plugin = Bukkit.getPluginManager().getPlugin("Craftconomy3");
				if (plugin != null) {
					Common craftconomy = (Common) ((Loader)plugin).getCommon();
					double balance = craftconomy.getAccountManager().getAccount(sender.getName(), false).getBalance("default", "Bellabuck");
					if (balance >= 25000.0) {
						//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money take " + sender.getName() + " 25000");
						//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " parent set vip");
						Player player = (Player) sender;
						String[] groups = {"admin", "ogvipmoderator", "vipmoderator", "ogmoderator", "moderator", "ogvip", "vip", "ogdefault", "default"};
						String group = getPlayerGroup(player, groups);
						if (group.equals("admin")) {
							sender.sendMessage("You must be [Member] or [Moderator] to perform this command.");
						} else if (group.equals("ogvipmoderator")) {
							sender.sendMessage("You are already VIP!");
						} else if (group.equals("vipmoderator")) {
							sender.sendMessage("You are already VIP!");
						} else if (group.equals("ogmoderator")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money take " + sender.getName() + " 25000");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " parent set ogvipmoderator");
						} else if (group.equals("moderator")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money take " + sender.getName() + " 25000");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " parent set vipmoderator");
						} else if (group.equals("ogvip")) {
							sender.sendMessage("You are already VIP!");
						} else if (group.equals("vip")) {
							sender.sendMessage("You are already VIP!");
						} else if (group.equals("ogdefault")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money take " + sender.getName() + " 25000");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " parent set ogvip");
						} else if (group.equals("default")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "money take " + sender.getName() + " 25000");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " parent set vip");
						} else {
							sender.sendMessage("Error: LuckPerms group not recognized.");
						}
					} else {
						sender.sendMessage("You don't have enough money! $25,000 is required.");
					}
				}
				return true;
			} else {
				System.out.println("You cannot run this command from the console.");
				return true;
			}
		} else {
			return false;
		}
	}
	public static String getPlayerGroup(Player player, String[] groups) {
		for (int i = 0; i < groups.length; i++) {
			if (player.hasPermission("group." + groups[i])) {
				return groups[i];
			}
		}
		return null;
	}
}