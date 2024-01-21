package xyz.kpzip.circuitsim.cmdline;

import java.util.regex.Pattern;

public final class ArgParser {

	private ArgParser() {}
	
	public static final Pattern NOGUI = Pattern.compile("\\G(--nogui|-.*n.*)$");
	
	
	public static boolean checkForArg(Pattern p, String[] args) {
		for (String s : args) {
			if (p.matcher(s).matches()) return true;
		}
		return false;
	}

}
