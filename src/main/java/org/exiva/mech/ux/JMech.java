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
		
		JActionScroll jas = new JActionScroll();
		sl.putConstraint(SpringLayout.NORTH, jas, 0, SpringLayout.SOUTH, menubar);
		sl.putConstraint(SpringLayout.SOUTH, jas, 0, SpringLayout.SOUTH, cp);
		sl.putConstraint(SpringLayout.WEST, jas,  0, SpringLayout.WEST, cp);
		sl.putConstraint(SpringLayout.EAST, jas,  0, SpringLayout.EAST, cp);
		
		
//		JActionPanel ap = new JActionPanel(new Action("clara", "Default"));
//		JActionPanel ap2 = new JActionPanel(new Action("clara2", "Default"));
		
		cp.add(menubar);
		cp.add(jas);
		
		jas.add(new Action("action0", "Default"));
		jas.add(new Action("action1", "Default"));
		jas.add(new Action("action2", "Default"));
		jas.add(new Action("action3", "Default"));
		jas.add(new Action("action4", "Default"));
		jas.add(new Action("action5", "Default"));
		jas.add(new Action("action6", "Default"));
		jas.add(new Action("action7", "Default"));
		jas.add(new Action("action8", "Default"));
		jas.add(new Action("action9", "Default"));
		jas.add(new Action("action11", "Default"));
		jas.add(new Action("action12", "Default"));
		jas.add(new Action("action13", "Default"));
		jas.add(new Action("action14", "Default"));
		jas.add(new Action("action15", "Default"));
		jas.add(new Action("action16", "Default"));
		jas.add(new Action("action17", "Default"));
		jas.add(new Action("action18", "Default"));
		jas.add(new Action("action19", "Default"));
		
//		cp.add(ap2);
		
//		ap.getAction().addNextActions(ap2.getAction());
//		ap2.getAction().addNextActions(ap.getAction());
		
	}
	
	

	
}
