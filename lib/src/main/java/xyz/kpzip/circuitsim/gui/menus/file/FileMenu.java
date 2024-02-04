package xyz.kpzip.circuitsim.gui.menus.file;

import java.awt.FileDialog;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import xyz.kpzip.circuitsim.file.CircuitFileHandler;
import xyz.kpzip.circuitsim.gui.frames.MainWindow;

public class FileMenu extends JMenu {

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
	
	
	
	private static class SaveMenuItem extends JMenuItem {

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
					if (f.getFile() == null) return;
					
					path = f.getDirectory() + f.getFile();
					CircuitFileHandler.save(path);
				}
				else {
					path = MainWindow.INSTANCE.getFilepath();
					if (!CircuitFileHandler.save(path)) {
						JOptionPane.showMessageDialog(MainWindow.INSTANCE, "Error Saving File");
					}
				}
			});
		}
		
	}
	
	private static class SaveAsMenuItem extends JMenuItem {

		/**
		 * serial id
		 */
		private static final long serialVersionUID = 3187381618574987013L;
		
		public SaveAsMenuItem() {
			super("Save As");
			addActionListener((actionlistener) -> {
				FileDialog f = new FileDialog(MainWindow.INSTANCE, "Save", FileDialog.SAVE);
				f.setVisible(true);
				if (f.getFile() == null) return;
				String path = f.getDirectory() + f.getFile();
				if (!CircuitFileHandler.save(path)) {
					JOptionPane.showMessageDialog(MainWindow.INSTANCE, "Error Saving File");
				}
				
			});
		}
		
	}
	
	private static class OpenMenuItem extends JMenuItem {

		/**
		 * serial id
		 */
		private static final long serialVersionUID = 3187381618574987013L;
		
		public OpenMenuItem() {
			super("Open");
			addActionListener((actionlistener) -> {
				FileDialog f = new FileDialog(MainWindow.INSTANCE, "Open", FileDialog.LOAD);
				f.setVisible(true);
				if (f.getFile() == null) return;
				String path = f.getDirectory() + f.getFile();
				if (!CircuitFileHandler.open(path)) {
					JOptionPane.showMessageDialog(MainWindow.INSTANCE, "Error Opening File");
				}
			});
		}
		
	}
	

}
