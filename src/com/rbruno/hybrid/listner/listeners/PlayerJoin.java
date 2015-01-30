package com.rbruno.hybrid.listner.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.listner.EngineListener;

public class PlayerJoin extends EngineListener implements Listener {

	public PlayerJoin(Main main) {
		super(main);
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.teleport(this.getMain().getLocations().getSpawn());
		player.sendMessage(this.getConfig().getString("Welcome-Message").replace("&", "§"));
		if (this.getConfig().getString("Players." + player.getUniqueId()) == null) {
			this.getConfig().set("Players." + player.getUniqueId(), 0);
			this.getMain().saveConfig();
		}

	}
}
