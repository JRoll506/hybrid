package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Arena extends EngineCommand {

	public Arena(Main main) {
		super("arena", "hybrid.arena", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(ChatColor.RED + "You must be a player to run this command: " + this.getCommand());
			return;
		}
		Player player = (Player) sender;
		if (player.hasPermission(this.getPermission())) {
			if (args.length < 1) {
				this.getMessage().sendMessage(player,ChatColor.RED + "Usage: /arena <arena_name>");
				return;
			}
			for (com.rbruno.hybrid.Arena arena : this.getMain().arenas){
				if (arena.getName().equals(args[0])){
					if (player.hasPermission("hybrid.arena.*")||player.hasPermission("hybrid.arena." + args[0])){
						player.teleport(arena.getSpawn());
						this.getMessage().sendMessage(player,ChatColor.GREEN + "Teleporting you to arena \"" + args[0] + "\"");
						return;
					}
					this.getMessage().sendMessage(player,this.getPermissionMessage());
				}
			}
			this.getMessage().sendMessage(player,ChatColor.RED + "Arena not found");
		} else {
			this.getMessage().sendMessage(player,this.getPermissionMessage());
		}
	}

}
