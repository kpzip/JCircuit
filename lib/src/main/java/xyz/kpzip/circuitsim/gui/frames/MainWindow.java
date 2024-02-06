package xyz.kpzip.circuitsim.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import xyz.kpzip.circuitsim.gui.GuiInfo;
import xyz.kpzip.circuitsim.gui.menus.MainMenuBar;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.CircuitBoard;
import xyz.kpzip.circuitsim.gui.menus.mainmenu.ComponentPicker;

public class MainWindow extends JFrame {
	
	/**
	 * serializeable id
	 */
	private static final long serialVersionUID = 2465230380698728324L;
	
	public static final String TITLE = "Java Circuit Demo";
	
	public static MainWindow INSTANCE;
	
	private String filepath = null;
	private boolean isSaved = true;
	public volatile boolean isClosed = false;
	
	private CircuitBoard boardComponent;
	@SuppressWarnings("unused")
	private ComponentPicker componentPicker;

	public MainWindow() {
		
		INSTANCE = this;
		
		this.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) { 
            	isClosed = true;
            }
        });
		
		addComponentListener(new ComponentAdapter() {
			
			@Override
		    public void componentResized(ComponentEvent componentEvent) {
            	boardComponent.setSize(componentEvent.getComponent().getSize());
		    }
		});
		
		setLayout(new BorderLayout());
		setJMenuBar(new MainMenuBar());
		
		add(boardComponent = new CircuitBoard(this.getSize()), BorderLayout.CENTER);
		add(componentPicker = new ComponentPicker(), BorderLayout.EAST);
		
		setIconImage(GuiInfo.ICON);
		//setLayout(null); // Fuck you
		setSize(1280, 720);
		setTitle(TITLE + " - " + (filepath != null ? filepath : "(Untitled)"));
		setVisible(true);
        
        
	}

	public String getFilepath() {
		return filepath;
	}
	
	public void setFilepath(String filepath) {
		this.filepath = filepath;
		updateTitle();
	}
	
	public void markDirty() {
		this.isSaved = false;
		updateTitle();
	}
	
	public void markSaved() {
		this.isSaved = true;
		updateTitle();
	}
	
	public void updateTitle() {
		setTitle(TITLE + " - " + (isSaved ? "" : "*") + (filepath != null ? filepath : "(Untitled)"));
	}
	
}
