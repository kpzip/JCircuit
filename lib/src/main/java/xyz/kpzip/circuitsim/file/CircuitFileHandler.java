package xyz.kpzip.circuitsim.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.CircuitBoard;

public final class CircuitFileHandler {

	private CircuitFileHandler() {}
	
	private static final Pattern INVALID_PATH = Pattern.compile("\\G(.*null|null.*)$");
	
	public static boolean save(String path) {
		if (path == null || INVALID_PATH.matcher(path).matches()) {
			return false;
		}
		try {
			FileOutputStream file = new FileOutputStream(path);
			ObjectOutputStream objwriter = new ObjectOutputStream(file);
			objwriter.writeObject(MainWindow.INSTANCE.getBoardComponent());
			objwriter.flush();
			objwriter.close();
		} 
		catch(IOException e) {
			return false;
		}
		MainWindow.INSTANCE.setFilepath(path);
		return true;
	}
	
	
	
	public static boolean open(String path) {
		if (path == null || INVALID_PATH.matcher(path).matches()) {
			return false;
		}
		try {
			FileInputStream file = new FileInputStream(path);
			ObjectInputStream objwriter = new ObjectInputStream(file);
			CircuitBoard b = (CircuitBoard) objwriter.readObject();
			objwriter.close();
			b.init();
			MainWindow.INSTANCE.setBoardComponent(b);
		} 
		catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			return false;
		}
		MainWindow.INSTANCE.setFilepath(path);
		return true;
	}
	
}
