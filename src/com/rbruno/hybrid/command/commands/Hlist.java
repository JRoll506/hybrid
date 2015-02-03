package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Arena;
import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;

public class Hlist extends EngineCommand {

	public Hlist(Main main) {
		super("hlist", "hybrid.hlist", main);
	}

	public void called(CommandSender sender, String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			if (player.hasPermission(this.getPermission())) {
				this.getMessage().sendMessage(player,ChatColor.GREEN + "--------Arenas--------");
				for (Arena arena : this.getMain().arenas) {
					this.getMessage().sendMessage(player,ChatColor.GREEN + "*" + arena.getName());
				}
			} else {
				this.getMessage().sendMessage(player,this.getPermissionMessage());
			}
		} else {
			System.out.println(ChatColor.GREEN + "--------Arenas--------");
			for (Arena arena : this.getMain().arenas) {
				System.out.println(ChatColor.GREEN + "*" + arena.getName());
			}
		}

	}
}
