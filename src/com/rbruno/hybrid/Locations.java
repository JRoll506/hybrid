package com.rbruno.hybrid;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Locations {

	private Main main;

	public Locations(Main main) {
		this.main = main;
	}

	public void setSpawn(String world, double x, double y, double z, double yaw, double pitch) {
		main.getConfig().set("spawn.world", world);
		main.getConfig().set("spawn.x", x);
		main.getConfig().set("spawn.y", y);
		main.getConfig().set("spawn.z", z);
		main.getConfig().set("spawn.yaw", yaw);
		main.getConfig().set("spawn.pitch", pitch);
		main.saveConfig();
	}
	
	public Location getSpawn(){
		return new Location(Bukkit.getWorld(main.getConfig().getString("spawn.world")), main.getConfig().getDouble("spawn.x"), main.getConfig().getDouble("spawn.y"), main.getConfig().getDouble("spawn.z"), Float.parseFloat(main.getConfig().getString("spawn.yaw")), Float.parseFloat(main.getConfig().getString("spawn.pitch")));
	}

}
