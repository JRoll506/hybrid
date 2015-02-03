package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Spawn extends EngineCommand {

	public Spawn(Main main) {
		super("spawn", "hybrid.spawn", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(ChatColor.RED + "You must be a player to run this command: " + this.getCommand());
			return;
		}
		Player player = (Player) sender;
		if (player.hasPermission(this.getPermission())) {
			player.teleport(this.getMain().getLocations().getSpawn());
		} else {
			this.getMessage().sendMessage(player,this.getPermissionMessage());
		}
	}

}
