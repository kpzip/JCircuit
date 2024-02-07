package xyz.kpzip.circuitsim.gui.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import xyz.kpzip.circuitsim.gui.GuiInfo;

public class PromptWindow extends JFrame {

	/**
	 * serial id
	 */
	private static final long serialVersionUID = -4277372798868244103L;
	
	private JTextField text;
	
	public PromptWindow(String label, String title) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(GuiInfo.ICON);
		setTitle(title);
		add(new JLabel(label));
		setLayout(new FlowLayout());
		add(new JLabel());
		add(text = new JTextField());
		text.setPreferredSize(new Dimension(100, 25));
		JButton button = new JButton("ok");
		button.addActionListener((e) -> {
			try {
				double value = Double.parseDouble(getText());
				MainWindow.INSTANCE.getBoardComponent().addComponent(value);
			}
			catch(NumberFormatException n) {
				
			}
			this.setVisible(false);
			this.dispose();
		});
		add(button);
		setSize(new Dimension(250, 150));
		
		setVisible(true);
	}
	
	public String getText() {
		return text.getText();
	}

}
