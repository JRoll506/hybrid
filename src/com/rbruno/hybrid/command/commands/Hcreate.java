package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Arena;
import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Hcreate extends EngineCommand {
	private Hpos hpos;

	public Hcreate(Main main, Hpos hpos) {
		super("hcreate", "hybrid.hcreate", main);
		this.hpos = hpos;
	}

	public void called(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(ChatColor.RED + "You must be a player to run this command: " + this.getCommand());
			return;
		}
		Player player = (Player) sender;
		if (player.hasPermission(this.getPermission())) {
			if (args.length < 1) {
				player.sendMessage(ChatColor.RED + "Invalid args");
				return;
			}
			if (!(hpos.pos1.containsKey(player))||!(hpos.pos2.containsKey(player))){
				player.sendMessage(ChatColor.RED + "");
			}
			for (Arena arena : this.getMain().arenas) {
				if (arena.getName().equalsIgnoreCase(args[0])) {
					player.sendMessage(ChatColor.RED + "Name already taken");
					return;
				}
			}
			player.sendMessage(ChatColor.GREEN + "Arena \"" + args[0] + "\" created!");
			this.getMain().createArena(args[0], hpos.pos1.get(player), hpos.pos2.get(player), player.getLocation());
		} else {
			player.sendMessage(this.getPermissionMessage());
		}
	}
}
