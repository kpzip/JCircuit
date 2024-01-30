package xyz.kpzip.circuitsim.gui;

import xyz.kpzip.circuitsim.gui.frames.MainWindow;

public final class GuiMode {

	private GuiMode() {}
	
	public static int run() {
		MainWindow window = new MainWindow();
		while (!window.isClosed) {}
		return 0;
	}

}
