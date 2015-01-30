package com.rbruno.hybrid.listner;

import java.util.ArrayList;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.listner.listeners.*;


public class ListenerManager {
	public ArrayList<EngineListener> listners = new ArrayList<EngineListener>();
	
	//adds "EngineListner" object into listners arraylist and registers them
	public ListenerManager(Main main){

		this.listners.add(new PlayerJoin(main));
		
		for (EngineListener listner: listners)
			main.getPlugin().getServer().getPluginManager().registerEvents(listner, main.getPlugin());
	}
}
