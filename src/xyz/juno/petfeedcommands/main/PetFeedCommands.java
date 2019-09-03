package xyz.juno.petfeedcommands.main;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.juno.petfeedcommands.cmds.Cmds;

public class PetFeedCommands extends JavaPlugin implements Listener {
	private static PetFeedCommands petFeedCommands;
	
	@Override
	public void onEnable() {
		petFeedCommands = this;
		saveDefaultConfig();
		getCommand("petfeed").setExecutor(new Cmds());
	}
	
	@Override
	public void onDisable() {}
	
	public static PetFeedCommands getPetFeedInstance() {
		return petFeedCommands;
	}
	
	public static String Color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}