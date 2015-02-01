package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Arena;
import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Hregen extends EngineCommand {

	public Hregen(Main main) {
		super("hregen", "hybrid.hregen", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission(this.getPermission())) {
				for (Arena arena : this.getMain().arenas) {
					if (arena.getName().equals(args[0])) {
						arena.regen();
						return;
					}
				}
				player.sendMessage(ChatColor.RED + "Arena not found \"" + args[0] + "\"");
			} else {
				player.sendMessage(this.getPermissionMessage());
			}
		} else {
			for (Arena arena : this.getMain().arenas) {
				if (arena.getName().equals(args[0])) {
					arena.regen();
					return;
				}
			}
			System.out.println(ChatColor.RED + "Arena not found \"" + args[0] + "\"");
		}

	}

}
