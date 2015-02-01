package com.rbruno.hybrid;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.rbruno.hybrid.command.CommandManager;
import com.rbruno.hybrid.command.EngineCommand;
import com.rbruno.hybrid.listner.ListenerManager;
import com.rbruno.hybrid.rank.Rank;

public class Main extends JavaPlugin {

	private PluginDescriptionFile pdf = this.getDescription();
	private Plugin plugin;
	private CommandManager commandManager;
	private Locations locations;
	public ArrayList<Arena> arenas = new ArrayList<Arena>();
	public Rank rank;

	public void onEnable() {
		this.saveDefaultConfig();
		getLogger().info(pdf.getName() + " [" + pdf.getVersion() + "] made by " + pdf.getAuthors());
		// sets plugin equal to the hybrid plugin so it can be used later
		plugin = this;
		// starts the command manager
		commandManager = new CommandManager(this);
		locations = new Locations(this);
		new ListenerManager(this);
		// Initializes the rank object
		rank = new Rank(this);
		loadArenas();
	}

	// Reads the config for arenas and loads them in the arenas ArrayList as an
	// Arena object
	public void loadArenas() {
		System.out.println("Arenas loaded: " + getConfig().getStringList("ArenaList"));
		for (String string : getConfig().getStringList("ArenaList")) {
			List<Integer> intList = getConfig().getIntegerList("Arenas." + string + ".spawn.blocks");
			int[] ints = new int[intList.size()];
			for (int i = 0; i < intList.size(); i++) {
				ints[i] = intList.get(i);
			}
			Location pos1 = new Location(Bukkit.getWorld(getConfig().getString("Arenas." + string + ".world")), getConfig().getInt("Arenas." + string + ".pos1.x"), getConfig().getInt("Arenas." + string + ".pos1.y"), getConfig().getInt("Arenas." + string + ".pos1.z"));
			Location pos2 = new Location(Bukkit.getWorld(getConfig().getString("Arenas." + string + ".world")), getConfig().getInt("Arenas." + string + ".pos2.x"), getConfig().getInt("Arenas." + string + ".pos2.y"), getConfig().getInt("Arenas." + string + ".pos2.z"));
			Location spawn = new Location(Bukkit.getWorld(getConfig().getString("Arenas." + string + ".world")), getConfig().getInt("Arenas." + string + ".spawn.x"), getConfig().getInt("Arenas." + string + ".spawn.y"), getConfig().getInt("Arenas." + string + ".spawn.z"), Float.parseFloat(getConfig().getString("Arenas." + string + ".spawn.yaw")), Float.parseFloat(getConfig().getString("Arenas." + string + ".spawn.pitch")));
			Arena arena = new Arena(string, pos1, pos2, spawn, ints, this);
			arena.startClock();
			arenas.add(arena);

		}
	}

	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Uses an enhanced for loop to go through an arraylist and check if the
		// name
		// matches the command label then calls the "called" method
		// If you dont know what an enhanced for loop it runs one time for every
		// object
		// in the commads arraylist and sets command equal to the object that
		// its on
		for (EngineCommand command : commandManager.commads) {
			if (command.getCommand().equalsIgnoreCase(label)) {
				command.called(sender, args);
				return true;
			}
		}
		return false;
	}

	// creates an "Arena" object saves it in arena arraylist and inits it
	public void createArena(String name, Location pos1, Location pos2, Location spawn) {
		Arena arena = new Arena(name, pos1, pos2, spawn, this);
		arenas.add(arena);
		arena.init();

	}

	public Plugin getPlugin() {
		return plugin;
	}

	public Locations getLocations() {
		return locations;
	}

	public Rank getRank() {
		return rank;
	}

	public void removeArena(Arena arena) {
		arena.remove();
		arenas.remove(arena);
		
	}

}
