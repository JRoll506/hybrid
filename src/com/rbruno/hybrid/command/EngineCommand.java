package com.rbruno.hybrid.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.rbruno.hybrid.Main;

public class EngineCommand {

	private String command;
	private String permission;
	private String permissionMessage = ChatColor.RED + "Invalid permission";
	private Main main;

	public EngineCommand(String command, String permission, Main main) {
		this.command = command;
		this.permission = permission;
		this.main = main;
	}
	
	//Override this method and this will be called everytime someone runs
	//a command that equals get command 
	public void called(CommandSender sender, String[] args) {
	}
	
	public String getCommand(){
		return command;
	}
	
	public String getPermission(){
		return permission;
	}
	
	public String getPermissionMessage(){
		return permissionMessage;
	}
	
	public Main getMain(){
		return main;
	}
	
	public FileConfiguration getConfig(){
		return main.getConfig();
	}
}
