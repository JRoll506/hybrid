package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Hreload extends EngineCommand {

	public Hreload(Main main) {
		super("hreload", "hybrid.hreload", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission(this.getPermission())) {
				this.getMain().reloadConfig();
				this.getMain().arenas.clear();
				this.getMain().loadArenas();
				player.sendMessage(ChatColor.GREEN + "Plugin config reloaded!");
			} else {
				player.sendMessage(this.getPermissionMessage());
			}
		} else {
			this.getMain().reloadConfig();
			this.getMain().arenas.clear();
			this.getMain().loadArenas();
			System.out.println(ChatColor.GREEN + "Plugin config reloaded!");
		}

	}

}
