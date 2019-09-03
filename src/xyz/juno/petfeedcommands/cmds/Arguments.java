package xyz.juno.petfeedcommands.cmds;

public enum Arguments {
	HELP("(help|\\?)", "pfc.help"),
	RELOAD("(reload|rl)", "pfc.reload"),
	FEED("(feed|hoi)", "pfc.feed");
	
	private String ArgumentRegex;
	private String ArgumentPermission;
	
	Arguments(String regex, String permission) {
		ArgumentRegex = regex;
		ArgumentPermission = permission;
	}
	
	public String getArgumentRegex() {
		return ArgumentRegex;
	}
	
	public String getArgumentPermission() {
		return ArgumentPermission;
	}
}