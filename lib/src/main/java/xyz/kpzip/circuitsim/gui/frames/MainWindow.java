package xyz.kpzip.circuitsim.gui.frames;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import xyz.kpzip.circuitsim.gui.GuiInfo;
import xyz.kpzip.circuitsim.gui.frames.menus.mainmenu.MainMenuBar;

public class MainWindow extends Frame {
	
	/**
	 * serializeable id
	 */
	private static final long serialVersionUID = 2465230380698728324L;
	
	public static final String TITLE = "Java Circuit Demo";
	
	public static MainWindow INSTANCE;
	
	private String filepath = null;
	private boolean isSaved = true;
	public volatile boolean isClosed = false;

	public MainWindow() {
		
		INSTANCE = this;
		
		this.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) { 
            	isClosed = true;
            } 
        });
		
		setMenuBar(new MainMenuBar());
		
		setIconImage(GuiInfo.ICON);
		setLayout(null);
		setSize(1280, 720);
		setTitle(TITLE + " - " + (filepath != null ? filepath : "(Untitled)"));
		setVisible(true);
		
	}

	public String getFilepath() {
		return filepath;
	}
	
	public void setFilepath(String filepath) {
		updateTitle();
		this.filepath = filepath;
	}
	
	public void markDirty() {
		updateTitle();
		this.isSaved = false;
	}
	
	public void updateTitle() {
		setTitle(TITLE + " - " + (isSaved ? "" : "*") + (filepath != null ? filepath : "(Untitled)"));
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		//Graphics2D g2 = (Graphics2D) g.create();
		g.drawImage(GuiInfo.ICON, 100, 100, this);
		//g2.dispose();
		
	}
	
}
