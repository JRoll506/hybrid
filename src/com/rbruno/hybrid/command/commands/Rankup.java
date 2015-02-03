package com.rbruno.hybrid.command.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.EngineCommand;
import com.rbruno.hybrid.rank.EngineRank;

public class Rankup extends EngineCommand {

	public Rankup(Main main) {
		super("rankup", "hybrid.rankup", main);
	}

	@SuppressWarnings("deprecation")
	public void called(CommandSender sender, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println(ChatColor.RED + "You must be a player to run this command: " + this.getCommand());
			return;
		}
		Player player = (Player) sender;
		if (player.hasPermission(this.getPermission())) {
			int rank = this.getConfig().getInt("Players." + player.getUniqueId());
			EngineRank nextRank = this.getMain().getRank().getRank(rank + 1);
			if (nextRank == null){
				this.getMessage().sendMessage(player,ChatColor.RED + "You have reached the maximum rank");
				return;
			}
			if (player.getInventory().contains(Material.getMaterial(nextRank.getItem()), nextRank.getAmount())){
				this.getMain().getRank().setRank(player, nextRank.getTear());
				this.getMessage().sendMessage(player,ChatColor.GREEN + "You have been ranked up to \"" + nextRank.getName() + "\"");
				player.getInventory().remove(new ItemStack(nextRank.getItem(), nextRank.getAmount()));
				EngineRank nextnextRank = this.getMain().getRank().getRank(rank + 2);
				if (nextnextRank == null){
					this.getMessage().sendMessage(player,ChatColor.RED + "You have reached the maximum rank");
					return;
				}
				this.getMessage().sendMessage(player,ChatColor.GREEN + "Your next missions is to collect " + nextnextRank.getAmount() + "x of " + Material.getMaterial(nextRank.getItem()).name());
			} else {
				this.getMessage().sendMessage(player,ChatColor.RED + "ERROR: You must have " + nextRank.getAmount() + "x of " + Material.getMaterial(nextRank.getItem()).name());
			}
		} else {
			this.getMessage().sendMessage(player,this.getPermissionMessage());
		}
	}

}
