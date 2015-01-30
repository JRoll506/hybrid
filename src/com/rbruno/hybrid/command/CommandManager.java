package com.rbruno.hybrid.command;

import java.util.ArrayList;

import com.rbruno.hybrid.Main;
import com.rbruno.hybrid.command.commands.Arena;
import com.rbruno.hybrid.command.commands.Hcreate;
import com.rbruno.hybrid.command.commands.Hlist;
import com.rbruno.hybrid.command.commands.Hpos;
import com.rbruno.hybrid.command.commands.Hrankadd;
import com.rbruno.hybrid.command.commands.Hregen;
import com.rbruno.hybrid.command.commands.Hreload;
import com.rbruno.hybrid.command.commands.Hsetspawn;
import com.rbruno.hybrid.command.commands.Rankup;
import com.rbruno.hybrid.command.commands.Spawn;

public class CommandManager {

	public ArrayList<EngineCommand> commads = new ArrayList<EngineCommand>();

	//adds "EngineCommand" objects into commands arraylist to be used in mains onCommand method
	public CommandManager(Main main) {
		this.commads.add(new Hsetspawn(main));
		this.commads.add(new Spawn(main));
		Hpos hpos = new Hpos(main);
		this.commads.add(hpos);
		this.commads.add(new Hcreate(main, hpos));
		this.commads.add(new Hregen(main));
		this.commads.add(new Hlist(main));
		this.commads.add(new Hreload(main));
		this.commads.add(new Hrankadd(main));
		this.commads.add(new Rankup(main));
		this.commads.add(new Arena(main));
	}

}