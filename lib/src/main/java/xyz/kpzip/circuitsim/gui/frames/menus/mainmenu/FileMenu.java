package xyz.kpzip.circuitsim.gui.frames.menus.mainmenu;

import java.awt.FileDialog;
import java.awt.Menu;
import java.awt.MenuItem;

import xyz.kpzip.circuitsim.file.CircuitFileHandler;
import xyz.kpzip.circuitsim.gui.frames.MainWindow;
import xyz.kpzip.circuitsim.gui.frames.dialog.ErrorDialog;

public class FileMenu extends Menu {

	/**
	 * Serial id
	 */
	private static final long serialVersionUID = -8964122958782939433L;
	
	public FileMenu() {
		super("File");
		add(new SaveMenuItem());
		add(new SaveAsMenuItem());
		add(new OpenMenuItem());
	}
	
	
	
	private static class SaveMenuItem extends MenuItem {

		/**
		 * serial id
		 */
		private static final long serialVersionUID = 3187381618574987013L;
		
		public SaveMenuItem() {
			super("Save");
			addActionListener((actionlistener) -> {
				
				String path;
				if (MainWindow.INSTANCE.getFilepath() == null) {
					FileDialog f = new FileDialog(MainWindow.INSTANCE, "Save", FileDialog.SAVE);
					f.setVisible(true);
				
					path = f.getDirectory() + f.getFile();
					CircuitFileHandler.save(path);
				}
				else {
					path = MainWindow.INSTANCE.getFilepath();
					if (!CircuitFileHandler.save(path)) {
						
					}
				}
			});
		}
		
	}
	
	private static class SaveAsMenuItem extends MenuItem {

		/**
		 * serial id
		 */
		private static final long serialVersionUID = 3187381618574987013L;
		
		public SaveAsMenuItem() {
			super("Save As");
			addActionListener((actionlistener) -> {
				FileDialog f = new FileDialog(MainWindow.INSTANCE, "Save", FileDialog.SAVE);
				f.setVisible(true);
				String path = f.getDirectory() + f.getFile();
				if (!CircuitFileHandler.save(path)) {
					
				}
				
			});
		}
		
	}
	
	private static class OpenMenuItem extends MenuItem {

		/**
		 * serial id
		 */
		private static final long serialVersionUID = 3187381618574987013L;
		
		public OpenMenuItem() {
			super("Open");
			addActionListener((actionlistener) -> {
				FileDialog f = new FileDialog(MainWindow.INSTANCE, "Open", FileDialog.LOAD);
				f.setVisible(true);
				String path = f.getDirectory() + f.getFile();
				if (!CircuitFileHandler.open(path)) {
					new ErrorDialog(MainWindow.INSTANCE, "Error", "Error Opening file", false);
				}
			});
		}
		
	}
	

}
