package com.rbruno.hybrid.rank;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.rbruno.hybrid.Main;

public class Rank {

	private Main main;

	public Rank(Main main) {
		this.main = main;
	}

	//adds a rank to the config
	public void add(String name, int tear, int item, int amount) {
		FileConfiguration config = main.getConfig();
		List<String> list = config.getStringList("ranks");
		list.add(name + "-" + tear + "-" + item + "-" + amount);
		config.set("ranks", list);
		main.saveConfig();
	}

	//sets a players rank
	public void setRank(Player player, int rank) {
		main.getConfig().set("Players." + player.getUniqueId(), rank);
		main.saveConfig();
	}

	//gets a players rank in a int
	public int getRank(Player player) {
		return main.getConfig().getInt("Players." + player.getUniqueId());
	}
	
	//get a "EngineRank" object from a int tier
	public EngineRank getRank(int tier) {
		for (String string : main.getConfig().getStringList("ranks")) {
			String teara = string.split("-")[1];
			String item = string.split("-")[2];
			String amount = string.split("-")[3];
			int tearb;
			int itema;
			int amounta;
			try {
				tearb = Integer.parseInt(teara);
				itema = Integer.parseInt(item);
				amounta = Integer.parseInt(amount);
			} catch (NumberFormatException e) {
				continue;
			}
			if (tearb == tier){
				return new EngineRank(string.split("-")[0], tearb, itema, amounta);
			}
		}
		return null;
	}
}
