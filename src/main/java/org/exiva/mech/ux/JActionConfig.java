package org.exiva.mech.ux;

import java.awt.Label;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.exiva.mech.actions.Action;
import org.exiva.mech.interfaces.IAction;
import org.exiva.mech.ux.actionconfigs.JActionConfigPanel;

public class JActionConfig extends JDialog{
	private static final long serialVersionUID = 1L;

	IAction action;
	
	public JActionConfig(IAction action) {
		if(action==null) throw new NullPointerException("action is null");
		this.action = action;
		this.setTitle("Configs of "+this.action.getActionName()+"["+this.action.getActionType()+"]");
		this.setSize(500,150);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		//this.setModal(true);
		
		if(this.action.getClass()==Action.class) {
			this.add(new JActionConfigPanel(this.action));
		}else {
			JPanel panError = new JPanel();
			panError.add(new Label("Action Config Error for " + this.action.getClass().getName()));
			this.add(panError);
		}
		
		this.setVisible(true);
	}	
}
