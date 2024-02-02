package xyz.kpzip.circuitsim.file;

import java.util.regex.Pattern;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;

public final class CircuitFileHandler {

	private CircuitFileHandler() {}
	
	private static final Pattern INVALID_PATH = Pattern.compile("\\G(.*null|null.*)$");
	
	public static boolean save(String path) {
		if (path == null || INVALID_PATH.matcher(path).matches()) {
			return false;
		}
		
		
		
		MainWindow.INSTANCE.setFilepath(path);
		return true;
	}
	
	
	
	public static boolean open(String path) {
		if (path == null || INVALID_PATH.matcher(path).matches()) {
			return false;
		}
		
		MainWindow.INSTANCE.setFilepath(path);
		return true;
	}
	
}
