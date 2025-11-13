package org.exiva.mech.ux;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.exiva.mech.services.cache.Cache;

public class JCachePanel extends JDialog{
	private static final long serialVersionUID = 1L;

	JScrollPane scrollPane;
	JTable table;
	
	public JCachePanel() {
		
		this.setTitle("Caching of this application");
		this.setSize(800,600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		//this.setModal(true);
		
		table = new JTable(Cache.getInstance().toTableArray(), new String[]{"Key", "Type", "Value"});
		this.scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		this.add(this.scrollPane);
		
		this.setVisible(true);
	}
}
