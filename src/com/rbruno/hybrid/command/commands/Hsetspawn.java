package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Hsetspawn extends EngineCommand {

	public Hsetspawn(Main main) {
		super("hsetspawn", "hybrid.setspawn", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(ChatColor.RED + "You must be a player to run this command: " + this.getCommand());
			return;
		}
		Player player = (Player) sender;
		if (player.hasPermission(this.getPermission())){
			Location location = player.getLocation();
			this.getMain().getLocations().setSpawn(location.getWorld().getName(), location.getX(), location.getY(), location.getZ(),location.getYaw(), location.getPitch());
			player.sendMessage(ChatColor.GREEN + "Spawn set!");
		} else{
			player.sendMessage(this.getPermissionMessage());
		}
		
	}

}
