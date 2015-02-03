package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Hrankadd extends EngineCommand {

	public Hrankadd(Main main) {
		super("hrankadd", "hybrid.hrankadd", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission(this.getPermission())) {
				if (args.length < 4) {
					this.getMessage().sendMessage(player,ChatColor.RED + "Usage: /hrankadd <Name> <tier> <item_name> <amount>");
					return;
				}
				String name = args[0];
				int tier;
				int item;
				int amount;
				try {
					tier = Integer.parseInt(args[1]);
					item = Integer.parseInt(args[2]);
					amount = Integer.parseInt(args[3]);
				} catch (NumberFormatException e) {
					this.getMessage().sendMessage(player,ChatColor.RED + "Usage: /hrankadd <Name> <tier> <item_name> <amount>");
					return;
				}
				if (this.getConfig().getString("ranks." + name) != null) {
					this.getMessage().sendMessage(player,ChatColor.RED + "Name already taken \"" + name + "\"");
				}
				this.getMain().rank.add(name, tier, item, amount);
				this.getMessage().sendMessage(player,ChatColor.GREEN + "Added rank " + name + " with tier " + tier);
			} else {
				this.getMessage().sendMessage(player,this.getPermissionMessage());
			}
		} else {
			if (args.length < 4) {
				System.out.println(ChatColor.RED + "Usage: /hrankadd <Name> <tier> <item_name> <amount>");
				return;
			}
			String name = args[0];
			int tier;
			int item;
			int amount;
			try {
				tier = Integer.parseInt(args[1]);
				item = Integer.parseInt(args[2]);
				amount = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				System.out.println(ChatColor.RED + "Usage: /hrankadd <Name> <tier> <item_name> <amount>");
				return;
			}
			if (this.getConfig().getString("ranks." + name) != null) {
				System.out.println(ChatColor.RED + "Name already taken \"" + name + "\"");
			}
			this.getMain().rank.add(name, tier, item, amount);
			System.out.println(ChatColor.GREEN + "Added rank " + name + " with tier " + tier);
		}
	}

}
