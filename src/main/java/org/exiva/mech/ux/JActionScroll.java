package org.exiva.mech.ux;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.exiva.mech.actions.Action;
import org.exiva.mech.actions.TypeAction;
import org.exiva.mech.interfaces.IAction;

public class JActionScroll extends JScrollPane{

	private static final long serialVersionUID = 1L;

	private JPanel actionPanel;
	private JButton btnAddAction;
	
	public JActionScroll() {
		super(VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);
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
						JActionScroll.this.add(new Action(null));
						break;
					case 1:						
						JActionScroll.this.add(new TypeAction(null, "something"));
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
		JActionPanel aPanel = new JActionPanel(iAction, this);
		this.actionPanel.remove(btnAddAction);
		
		//link previous action to this one if exists
		if(this.actionPanel.getComponentCount()>0) {
			Component c = this.actionPanel.getComponent(this.actionPanel.getComponentCount()-1);
			if((c instanceof JActionPanel)) {
				((JActionPanel)c).getAction().addNextActions(iAction);
			}
		}
		this.actionPanel.add(aPanel);
		this.actionPanel.add(btnAddAction);
		this.updateUI();
		return true;
	}

	public void deleteActionPanel(JActionPanel jActionPanel) {
		this.actionPanel.remove(jActionPanel);
		this.updateUI();
	}
	
	public void switchActions(IAction a1, IAction a2) {
		//first find the indexes of each panels
		int i1 = -1;
		int i2 = -1;
		
		for(int i=0; i<this.actionPanel.getComponentCount() && (i1<0 || i2<0); i++) {
			Component c = this.actionPanel.getComponent(i);
			if(c instanceof JActionPanel && ((JActionPanel)c).getAction().equals(a1)) {
				i1 = i;
			}
			if(c instanceof JActionPanel && ((JActionPanel)c).getAction().equals(a2)) {
				i2 = i;
			}
		}
		if(i1<0 || i2<0) return;
		
		//swap actions in the action list
		JActionPanel jp1 = (JActionPanel)this.actionPanel.getComponent(i1);
		JActionPanel jp2 = (JActionPanel)this.actionPanel.getComponent(i2);
		JActionPanel newjp1 = new JActionPanel(jp1.getAction(), this);
		JActionPanel newjp2 = new JActionPanel(jp2.getAction(), this);

		this.actionPanel.remove(jp1);
		this.actionPanel.add(newjp2, i1);
		
		this.actionPanel.remove(jp2);
		this.actionPanel.add(newjp1, i2);

		this.updateUI();
	
	}
}
