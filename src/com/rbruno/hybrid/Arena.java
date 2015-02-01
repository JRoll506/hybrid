package com.rbruno.hybrid;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class Arena {

	private String name;
	private Main main;
	private Location pos1, pos2, spawn;
	// private Random random = new Random();

	private Material[] blocks;

	public Arena(String name, Location pos1, Location pos2, Location spawn, Main main) {
		this.name = name;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.spawn = spawn;
		this.main = main;

	}
	
	public Arena(String name, Location pos1, Location pos2, Location spawn, int[] ints, Main main) {
		this.name = name;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.spawn = spawn;
		this.main = main;
		this.blocks = phraseMaterial(ints);

	}

	@SuppressWarnings("deprecation")
	private Material[] phraseMaterial(int[] ints) {
		Material[] material = new Material[ints.length];
		for (int i = 0; i<ints.length;i++){
			material[i] = Material.getMaterial(ints[i]);
		}
		return material;
	}

	@SuppressWarnings("deprecation")
	public void init() {
		saveArena();
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
		int[] ints = new int[blocks.length];
		for (int i = 0; i < blocks.length; i++){
			ints[i] = blocks[i].getId();
		}
		config.set("Arenas." + name + ".spawn.blocks", ints);
		main.saveConfig();
		startClock();
	}

	private void saveArena() {
		int height, width, depth;
		width = pos2.getBlockX() - pos1.getBlockX() + 1;
		depth = pos2.getBlockZ() - pos1.getBlockZ() + 1;
		height = pos2.getBlockY() - pos1.getBlockY() + 1;
		blocks = new Material[width * depth * height];
		for (int y = 0; y < height; y++) {
			for (int z = 0; z < depth; z++) {
				for (int x = 0; x < width; x++) {
					Block block = pos1.getWorld().getBlockAt(pos1.getBlockX() + x, pos1.getBlockY() + y, pos1.getBlockZ() + z);
					blocks[x + z * width + y * width * depth] = block.getType();
				}
			}
		}
		
	}

	@SuppressWarnings("deprecation")
	public void startClock() {
		Bukkit.getScheduler().runTaskTimer(main.getPlugin(), new BukkitRunnable() {
			@Override
			public void run() {
				regen();
			}
		}, 20 * main.getConfig().getInt("arena-reset-time"), 20 * main.getConfig().getInt("arena-reset-time"));
	}

	/*
	 * public void regen() { Bukkit.broadcastMessage(ChatColor.GREEN +
	 * "Regenerating arena " + getName()); for (int y = 0; y < pos2.getBlockY()
	 * - pos1.getBlockY() + 1; y++) { for (int z = 0; z < pos2.getBlockZ() -
	 * pos1.getBlockZ() + 1; z++) { for (int x = 0; x < pos2.getBlockX() -
	 * pos1.getBlockX() + 1; x++) { Block block =
	 * pos1.getWorld().getBlockAt(pos1.getBlockX() + x, pos1.getBlockY() + y,
	 * pos1.getBlockZ() + z); int randomint = random.nextInt(99) + 1; if
	 * (randomint <= 55) block.setType(Material.STONE); if (randomint <= 75 &&
	 * randomint > 55) block.setType(Material.COAL_ORE); if (randomint <= 85 &&
	 * randomint > 75) block.setType(Material.IRON_ORE); if (randomint < 95 &&
	 * randomint > 85) block.setType(Material.REDSTONE_ORE); if
	 * (block.getLocation().getBlockY() <= 20) { if (randomint >= 95)
	 * block.setType(Material.DIAMOND_ORE); } else { if (randomint >= 95)
	 * block.setType(Material.STONE); } if (block.getType() == Material.AIR)
	 * System.out.println(randomint); } } } }
	 */

	public void regen() {
		Bukkit.broadcastMessage(ChatColor.GREEN + "Regenerating arena " + getName());
		int height, width, depth;
		width = pos2.getBlockX() - pos1.getBlockX() + 1;
		depth = pos2.getBlockZ() - pos1.getBlockZ() + 1;
		height = pos2.getBlockY() - pos1.getBlockY() + 1;
		for (int y = 0; y < height; y++) {
			for (int z = 0; z < depth; z++) {
				for (int x = 0; x < width; x++) {
					pos1.getWorld().getBlockAt(pos1.getBlockX() + x, pos1.getBlockY() + y, pos1.getBlockZ() + z).setType(blocks[x + z * width + y * width * depth]);
				}
			}
		}
	}

	public String getName() {
		return name;
	}

	public Location getSpawn() {
		return spawn;
	}

	public void remove() {
		FileConfiguration config = main.getConfig();
		config.set("Arenas." + name, null);
		List<String> list = config.getStringList("ArenaList");
		list.remove(name);
		config.set("ArenaList", list);
		main.saveConfig();
	}
}
