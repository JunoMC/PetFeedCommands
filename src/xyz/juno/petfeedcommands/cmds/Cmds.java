package xyz.juno.petfeedcommands.cmds;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Keyle.MyPet.MyPetApi;
import xyz.juno.lib.main.cmds.CmdsInterface.CmdsAPI;
import xyz.juno.petfeedcommands.main.PetFeedCommands;
import xyz.juno.petfeedcommands.main.settings.Settings;

public class Cmds implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command c, String label, String[] a) {
		/**************************************/
		Arguments HELP = Arguments.HELP;
		Arguments RELOAD = Arguments.RELOAD;
		Arguments FEED = Arguments.FEED;
		/**************************************/
		Settings HELP_MESSAGE = Settings.HELP;
		Settings NO_PERMISSION = Settings.NO_PERMISSION;
		Settings RELOAD_ERROR = Settings.RELOAD_ERROR;
		Settings RELOAD_SUCCESS = Settings.RELOAD_SUCCESS;
		Settings FEED_USAGE = Settings.FEED_USAGE;
		Settings PLAYER_NOT_FOUND_PATH = Settings.PLAYER_NOT_FOUND;
		Settings FEED_SUCCESS_PATH = Settings.FEED_SUCCESS;
		/**************************************/
		String prefix = PetFeedCommands.Color(PetFeedCommands.getPetFeedInstance().getConfig().getString("Prefix"));
		/**************************************/
		if (CmdsAPI.sender(sender).isLength(a, 0)) {
			if (CmdsAPI.sender(sender).isPlayer()) {
				if (CmdsAPI.sender(sender).hasPermission(HELP.getArgumentPermission())) {
					List<String> msgs = PetFeedCommands.getPetFeedInstance().getConfig().getStringList(HELP_MESSAGE.getPath());
					
					for (String msg : msgs) {
						CmdsAPI.sender(sender).send(msg
								.replaceAll("(\\%prefix%)", prefix)
								.replaceAll("(\\%cmd%|\\%lenh%)", label)
								);
					}
					
				} else CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, NO_PERMISSION.getPath());
			} else {
				List<String> msgs = PetFeedCommands.getPetFeedInstance().getConfig().getStringList(HELP_MESSAGE.getPath());
				
				for (String msg : msgs) {
					CmdsAPI.sender(sender).send(msg
							.replaceAll("(\\%prefix%)", prefix)
							.replaceAll("(\\%cmd%|\\%lenh%)", label)
							);
				}
			}
		}
		
		if (CmdsAPI.sender(sender).isLength(a, 1)) {
			if (CmdsAPI.sender(sender).isArgument(a[0], HELP.getArgumentRegex())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(HELP.getArgumentPermission())) {
						List<String> msgs = PetFeedCommands.getPetFeedInstance().getConfig().getStringList(HELP_MESSAGE.getPath());
						
						for (String msg : msgs) {
							CmdsAPI.sender(sender).send(msg
									.replaceAll("(\\%prefix%)", prefix)
									.replaceAll("(\\%cmd%|\\%lenh%)", label)
									);
						}
						
					} else CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, NO_PERMISSION.getPath());
				} else {
					List<String> msgs = PetFeedCommands.getPetFeedInstance().getConfig().getStringList(HELP_MESSAGE.getPath());
					
					for (String msg : msgs) {
						CmdsAPI.sender(sender).send(msg
								.replaceAll("(\\%prefix%)", prefix)
								.replaceAll("(\\%cmd%|\\%lenh%)", label)
								);
					}
				}
			}
			
			if (CmdsAPI.sender(sender).isArgument(a[0], RELOAD.getArgumentRegex())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(RELOAD.getArgumentPermission())) {
						try {
							PetFeedCommands.getPetFeedInstance().reloadConfig();
						} catch (Exception ex) {
							CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, RELOAD_ERROR.getPath());
						} finally {
							CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, RELOAD_SUCCESS.getPath());
						}
					} else CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, NO_PERMISSION.getPath());
				} else {
					try {
						PetFeedCommands.getPetFeedInstance().reloadConfig();
					} catch (Exception ex) {
						CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, RELOAD_ERROR.getPath());
					} finally {
						CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, RELOAD_SUCCESS.getPath());
					}
				}
			}
		}
		
		if (CmdsAPI.sender(sender).isMinvMaxLength(a, 0, 4)) {
			if (CmdsAPI.sender(sender).isArgument(a[0], FEED.getArgumentRegex())) {
				if (CmdsAPI.sender(sender).isPlayer()) {
					if (CmdsAPI.sender(sender).hasPermission(FEED.getArgumentPermission())) {
						if (CmdsAPI.sender(sender).isMaxLength(a, 3)) {
							CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, FEED_USAGE.getPath());
						}
						
						if (CmdsAPI.sender(sender).isLength(a, 3)) {
							String _name = a[1];
							double amount = Double.valueOf(a[2]);
							
							Player p = Bukkit.getPlayer(_name);
							
							if (p == null) {
								String PLAYER_NOT_FOUND = PetFeedCommands.getPetFeedInstance().getConfig().getString(PLAYER_NOT_FOUND_PATH.getPath());
								CmdsAPI.sender(sender).send(PLAYER_NOT_FOUND
										.replaceAll("(\\%prefix%)", prefix)
										.replaceAll("(\\%player%)", _name)
										);
							} else {
								String FEED_SUCCESSS = PetFeedCommands.getPetFeedInstance().getConfig().getString(FEED_SUCCESS_PATH.getPath())
										.replaceAll("(\\%prefix%)", prefix)
										.replaceAll("(\\%player%)", _name)
										.replaceAll("(\\%amount%)", a[2]);
								
								CmdsAPI.sender(sender).send(FEED_SUCCESSS);
								MyPetApi.getMyPetManager().getMyPet(p).setSaturation(MyPetApi.getMyPetManager().getMyPet(p).getSaturation() + amount);
							}
						}
					} else CmdsAPI.sender(sender).sendPath(PetFeedCommands.getPetFeedInstance(), prefix, NO_PERMISSION.getPath());
				} else {
					String _name = a[1];
					double amount = Double.valueOf(a[2]);
					
					Player p = Bukkit.getPlayer(_name);
					
					if (p == null) {
						String PLAYER_NOT_FOUND = PetFeedCommands.getPetFeedInstance().getConfig().getString(PLAYER_NOT_FOUND_PATH.getPath());
						CmdsAPI.sender(sender).send(PLAYER_NOT_FOUND
								.replaceAll("(\\%prefix%)", prefix)
								.replaceAll("(\\%player%)", _name)
								);
					} else {
						String FEED_SUCCESSS = PetFeedCommands.getPetFeedInstance().getConfig().getString(FEED_SUCCESS_PATH.getPath())
								.replaceAll("(\\%prefix%)", prefix)
								.replaceAll("(\\%player%)", _name)
								.replaceAll("(\\%amount%)", a[2]);
						
						CmdsAPI.sender(sender).send(FEED_SUCCESSS);
						MyPetApi.getMyPetManager().getMyPet(p).setSaturation(MyPetApi.getMyPetManager().getMyPet(p).getSaturation() + amount);
					}
				}
			}
		}
		
		return false;
	}
}