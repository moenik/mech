package org.exiva.mech.ux;

import java.awt.AWTException;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SpringLayout;

import org.exiva.mech.actions.Action;

public class JMech extends JFrame{

	private static final long serialVersionUID = 1L;

	public JMech() throws AWTException {
		this.setSize(800, 600);
		this.setMinimumSize(getSize());
		this.setPreferredSize(getSize());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setTitle("MECH - The Mechanic");
		
		this.setupComponents();
//		this.setupEvents();
		
		this.setVisible(true);
	}

	private JMenuBar menubar;
	private JMenu menuFile;
	private JMenuItem menuFileLoad;
	private JMenuItem menuFileSave;
	
	private void setupComponents() throws AWTException {
		menubar = new JMenuBar();
		menuFile = new JMenu("File");
		menuFileLoad = new JMenuItem("Load..");
		menuFileSave = new JMenuItem("Save..");
		
		menuFile.add(menuFileLoad);
		menuFile.add(menuFileSave);
		menubar.add(menuFile);
		
		Container cp = this.getContentPane();
		SpringLayout sl = new SpringLayout();
		cp.setLayout(sl);
		
		sl.putConstraint(SpringLayout.NORTH, menubar, 0, SpringLayout.NORTH, cp);
		sl.putConstraint(SpringLayout.SOUTH, menubar,25, SpringLayout.NORTH, cp);
		sl.putConstraint(SpringLayout.WEST, menubar, 0, SpringLayout.WEST, cp);
		sl.putConstraint(SpringLayout.EAST, menubar, 0, SpringLayout.EAST, cp);
		
		JActionPanel ap = new JActionPanel(new Action("clara", "Default"));
		sl.putConstraint(SpringLayout.NORTH, ap, 0, SpringLayout.SOUTH, menubar);
		sl.putConstraint(SpringLayout.SOUTH, ap,40, SpringLayout.NORTH, ap);
		sl.putConstraint(SpringLayout.WEST, ap, 2, SpringLayout.WEST, cp);
		sl.putConstraint(SpringLayout.EAST, ap, -2, SpringLayout.EAST, cp);
		
		cp.add(menubar);
		cp.add(ap);
		
	}
	
	

	
}
