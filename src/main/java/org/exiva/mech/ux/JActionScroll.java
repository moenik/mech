package org.exiva.mech.ux;

import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.exiva.mech.interfaces.IAction;

public class JActionScroll extends JScrollPane{

	private static final long serialVersionUID = 1L;

	private List<JActionPanel> actions;
	private JPanel actionPanel;
	
	public JActionScroll() {
		super(VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
		this.actions = new Vector<JActionPanel>();
		this.actionPanel = new JPanel();
		this.actionPanel.setLayout(new GridLayout(0, 1));
		this.getViewport().add(actionPanel);
	}
	
	public boolean add(IAction iAction) {
		JActionPanel aPanel = new JActionPanel(iAction);
		if(!this.actions.isEmpty()) {
			this.actions.getLast().getAction().addNextActions(iAction);
		}
		this.actions.add(aPanel);
		this.actionPanel.add(aPanel);
		
		return true;
	}
}
