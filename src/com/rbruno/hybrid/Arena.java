package com.rbruno.hybrid;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

public class Arena {

	private String name;
	private Main main;
	private Location pos1, pos2, spawn;
	private Random random = new Random();

	public Arena(String name, Location pos1, Location pos2, Location spawn, Main main) {
		this.name = name;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.spawn = spawn;
		this.main = main;

	}

	public void init() {
		FileConfiguration config = main.getConfig();
		config.set("Arenas." + name + ".world", pos1.getWorld().getName());
		config.set("Arenas." + name + ".pos1.x", pos1.getBlockX());
		config.set("Arenas." + name + ".pos1.y", pos1.getBlockY());
		config.set("Arenas." + name + ".pos1.z", pos1.getBlockZ());

		config.set("Arenas." + name + ".pos2.x", pos2.getBlockX());
		config.set("Arenas." + name + ".pos2.y", pos2.getBlockY());
		config.set("Arenas." + name + ".pos2.z", pos2.getBlockZ());
		
		config.set("Arenas." + name + ".spawn.x", spawn.getX());
		config.set("Arenas." + name + ".spawn.y", spawn.getY());
		config.set("Arenas." + name + ".spawn.z", spawn.getZ());
		config.set("Arenas." + name + ".spawn.yaw", spawn.getYaw());
		config.set("Arenas." + name + ".spawn.pitch", spawn.getPitch());

		List<String> list = config.getStringList("ArenaList");
		list.add(name);
		config.set("ArenaList", list);

		main.saveConfig();
	}

	public void regen() {
		for (int y = 0; y < pos2.getBlockY() - pos1.getBlockY() + 1; y++) {
			for (int z = 0; z < pos2.getBlockZ() - pos1.getBlockZ() + 1; z++) {
				for (int x = 0; x < pos2.getBlockX() - pos1.getBlockX() + 1; x++) {
					Block block = pos1.getWorld().getBlockAt(pos1.getBlockX() + x, pos1.getBlockY() + y, pos1.getBlockZ() + z);
					int randomint = random.nextInt(99) + 1;
					if (randomint <= 55) block.setType(Material.STONE);
					if (randomint <= 75 && randomint > 55) block.setType(Material.COAL_ORE);
					if (randomint <= 85 && randomint > 75) block.setType(Material.IRON_ORE);
					if (randomint < 95 && randomint > 85) block.setType(Material.REDSTONE_ORE);
					if (block.getLocation().getBlockY() <= 20) {
						if (randomint >= 95) block.setType(Material.DIAMOND_ORE);
					} else {
						if (randomint >= 95) block.setType(Material.STONE);
					}
					if (block.getType() == Material.AIR) System.out.println(randomint);
				}
			}
		}
	}

	public String getName() {
		return name;
	}
	
	public Location getSpawn(){
		return spawn;
	}
}
