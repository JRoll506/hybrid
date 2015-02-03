package com.rbruno.hybrid.command.commands;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Hpos extends EngineCommand {

	HashMap<Player, Location> pos1 = new HashMap<Player, Location>();
	HashMap<Player, Location> pos2 = new HashMap<Player, Location>();

	public Hpos(Main main) {
		super("hpos", "hybrid.hpos", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(ChatColor.RED + "You must be a player to run this command: " + this.getCommand());
			return;
		}
		Player player = (Player) sender;
		if (player.hasPermission(this.getPermission())) {
			int pos = 0;
			try {
				pos = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				this.getMessage().sendMessage(player,ChatColor.RED + "Usage: /hpos <1/2>");
				return;
			}
			if (!(pos == 1) && !(pos == 2)) {
				this.getMessage().sendMessage(player,ChatColor.RED + "Usage: /hpos <1/2>");
				return;
			}
			if (pos == 1) {
				pos1.put(player, player.getLocation());
				this.getMessage().sendMessage(player,ChatColor.GREEN + "Set postion 1");
				return;
			}
			if (pos == 2) {
				if (!(pos1.containsKey(player))) {
					this.getMessage().sendMessage(player,ChatColor.RED + "Set 1st postion first");
					return;
				}
				Location pos1a = pos1.get(player);
				Location pos2a = player.getLocation();
				if (pos2a.getBlockX() < pos1a.getBlockX()||pos2a.getBlockY() < pos1a.getBlockY()||pos2a.getBlockZ() < pos1a.getBlockZ()) {
					this.getMessage().sendMessage(player,ChatColor.RED + "You 2nd posstion cant be negitive in X, Y or Z cords");
					return;
				}
				this.getMessage().sendMessage(player,ChatColor.GREEN + "Set postion 2");
				pos2.put(player, pos2a);
			}

		} else {
			this.getMessage().sendMessage(player,this.getPermissionMessage());
		}
	}
}
