package xyz.kpzip.circuitsim;

import xyz.kpzip.circuitsim.cmdline.ArgParser;
import xyz.kpzip.circuitsim.cmdline.CmdLineMode;
import xyz.kpzip.circuitsim.gui.GuiMode;

public final class Main {
	
	private Main() {}

	public static void main(String[] args) {
		
		boolean gui = ArgParser.checkForArg(ArgParser.NOGUI, args);
		int exitcode;
		
		if (gui) {
			exitcode = GuiMode.run();
			System.exit(exitcode);
		} else {
			exitcode = CmdLineMode.run(args);
			System.exit(exitcode);
		}
	}

}
