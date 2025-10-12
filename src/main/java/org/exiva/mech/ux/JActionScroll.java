package org.exiva.mech.ux;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.exiva.mech.actions.Action;
import org.exiva.mech.actions.TypeAction;
import org.exiva.mech.interfaces.IAction;

public class JActionScroll extends JScrollPane{

	private static final long serialVersionUID = 1L;

	private List<JActionPanel> actions;
	private JPanel actionPanel;
	private JButton btnAddAction;
	
	public JActionScroll() {
		super(VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
		this.actions = new Vector<JActionPanel>();
		this.actionPanel = new JPanel();
		this.actionPanel.setLayout(new GridLayout(0, 1));
		this.getViewport().add(actionPanel);
		this.btnAddAction = new JButton("Add action");
		this.btnAddAction.setMinimumSize(new Dimension(400, 40));
		this.btnAddAction.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		this.btnAddAction.setPreferredSize(getMinimumSize());
		this.btnAddAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = new String[]{"Delay","Type"};
				int a = JOptionPane.showOptionDialog(JActionScroll.this, "Action type:", "Action chooser", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(a<0) return;

				try {
					switch (a) {
					case 0:
						JActionScroll.this.add(new Action("Delay 1000ms"));
						break;
					case 1:						
						JActionScroll.this.add(new TypeAction("Type something", "something"));
						break;
					default:
						break;
					}
				} catch (AWTException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", ERROR);
				}
				
			}
		});
		this.actionPanel.add(btnAddAction);
	}
	
	public boolean add(IAction iAction) {
		JActionPanel aPanel = new JActionPanel(iAction);
		this.actionPanel.remove(btnAddAction);
		
		if(!this.actions.isEmpty()) {
			this.actions.getLast().getAction().addNextActions(iAction);
		}
		this.actions.add(aPanel);
		this.actionPanel.add(aPanel);
		
		this.actionPanel.add(btnAddAction);
		this.updateUI();
		return true;
	}
}
