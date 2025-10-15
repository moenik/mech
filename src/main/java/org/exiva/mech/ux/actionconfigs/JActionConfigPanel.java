package org.exiva.mech.ux.actionconfigs;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.exiva.mech.interfaces.ActionStatus;
import org.exiva.mech.interfaces.IAction;
import org.exiva.mech.interfaces.IActionListener;

public class JActionConfigPanel extends JPanel implements IActionListener{
	private static final long serialVersionUID = 1L;

	protected JLabel lblName;
	protected JTextField txtName;
	protected JLabel lblType;
	protected JLabel lblStatus;
	
	protected SpringLayout layout;
	protected IAction action;
	
	public JActionConfigPanel(IAction action) {
		this.action = action;
		
		
		layout = new SpringLayout();
		this.setLayout(layout);
		
		lblName = new JLabel("Name:");
		txtName = new JTextField(this.action.getActionName());
		txtName.setEnabled(false);
		lblType = new JLabel("Type: "+this.action.getActionType());
		lblStatus=new JLabel("STATUS - messages");
		this.action.addActionListener(this);
		this.updateStatusLbl();
		
		layout.putConstraint(SpringLayout.NORTH, lblName, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, lblName,20, SpringLayout.NORTH, lblName);
		layout.putConstraint(SpringLayout.WEST,  lblName, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST,  lblName,40, SpringLayout.WEST, lblName);
		
		layout.putConstraint(SpringLayout.NORTH, txtName, 0, SpringLayout.NORTH, lblName);
		layout.putConstraint(SpringLayout.SOUTH, txtName, 0, SpringLayout.SOUTH, lblName);
		layout.putConstraint(SpringLayout.WEST,  txtName, 0, SpringLayout.EAST,  lblName);
		layout.putConstraint(SpringLayout.EAST,  txtName, 0, SpringLayout.EAST,  this);
		
		layout.putConstraint(SpringLayout.NORTH, lblType, 0, SpringLayout.SOUTH, lblName);
		layout.putConstraint(SpringLayout.SOUTH, lblType,20, SpringLayout.NORTH, lblType);
		layout.putConstraint(SpringLayout.WEST,  lblType, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST,  lblType, 0, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.NORTH, lblStatus, 0, SpringLayout.SOUTH, lblType);
		layout.putConstraint(SpringLayout.SOUTH, lblStatus,20, SpringLayout.NORTH, lblStatus);
		layout.putConstraint(SpringLayout.WEST,  lblStatus, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST,  lblStatus, 0, SpringLayout.EAST, this);
		
		this.add(lblName);
		this.add(txtName);
		this.add(lblType);
		this.add(lblStatus);		
		
		
	}

	@Override
	public void removeNotify() {
		super.removeNotify();
		this.action.removeActionListener(this);
	}
	
	protected void updateStatusLbl() {
		this.lblStatus.setText(this.action.getStatus().toString()+"-"+this.action.getStatusMessage());
	}
	
	@Override
	public void updatedStatus(ActionStatus status) {
		updateStatusLbl();
	}

	@Override
	public void updatedMessage(String message) {
		updateStatusLbl();
	}
	
}