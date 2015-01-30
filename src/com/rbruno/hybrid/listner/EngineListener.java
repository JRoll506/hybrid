package com.rbruno.hybrid.listner;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import com.rbruno.hybrid.Main;

public class EngineListener implements Listener {
	
	private Main main;
	
	public EngineListener(Main main){
		this.main = main;
	}
	
	public Main getMain(){
		return main;
	}
	
	public FileConfiguration getConfig(){
		return main.getConfig();
	}

}
