package xyz.juno.petfeedcommands.main.settings;

public enum Settings {
	
	HELP("messages.help"),
	NO_PERMISSION("messages.no-permission"),
	RELOAD_ERROR("messages.reload-error"),
	RELOAD_SUCCESS("messages.reload-success"),
	PLAYER_NOT_FOUND("messages.player-not-found"),
	FEED_USAGE("messages.cmds.feed-usage"),
	FEED_SUCCESS("messages.feed-success");
	
	private String path;
	
	Settings(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}