package xyz.kpzip.circuitsim.cmdline;

import xyz.kpzip.circuitsim.gui.GuiMode;

public final class Main {
	
	private Main() {}

	public static void main(String[] args) {
		
		boolean nogui = ArgParser.checkForArg(ArgParser.NOGUI, args);
		int exitcode;
		
		if (nogui) {
			exitcode = CmdLineMode.run(args);
			System.exit(exitcode);
		} else {
			exitcode = GuiMode.run();
			System.exit(exitcode);
		}
	}

}
