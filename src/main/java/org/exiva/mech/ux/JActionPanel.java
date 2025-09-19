package org.exiva.mech.ux;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import org.exiva.mech.interfaces.IAction;

public class JActionPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private IAction action;
	
	public JActionPanel(IAction action) {
		this.action = action;		
		this.setupComponents();
	}
	
	
	private JLabel  lblActionNameType;
	private JLabel  lblActionStatusMessage;
	
	private JButton btnStartPause = new JButton("▶⏸");
	private JButton btnStop = new JButton("⏹");
	private JButton btnConfigs = new JButton("⚙");
	
	private void setupComponents() {
		this.setBorder(new LineBorder(Color.BLACK));
		SpringLayout sl = new SpringLayout();		
		this.setLayout(sl);
		lblActionNameType = new JLabel("("+this.action.getActionType() + ") - " + this.action.getActionName());
		lblActionStatusMessage = new JLabel("("+action.getStatus().toString()+") -" + this.action.getStatusMessage());
		//this.setMinimumSize(new Dimension(798, 50));
		
		this.add(lblActionNameType);
		this.add(lblActionStatusMessage);

		
		sl.putConstraint(SpringLayout.NORTH, lblActionNameType, 0, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, lblActionNameType, 20, SpringLayout.NORTH, lblActionNameType);
		sl.putConstraint(SpringLayout.EAST, lblActionNameType, 0, SpringLayout.WEST, btnStartPause);
		sl.putConstraint(SpringLayout.WEST, lblActionNameType, 0, SpringLayout.WEST, this);
		
		sl.putConstraint(SpringLayout.NORTH, lblActionStatusMessage, 0, SpringLayout.SOUTH, lblActionNameType);
		sl.putConstraint(SpringLayout.SOUTH, lblActionStatusMessage, 20, SpringLayout.NORTH, lblActionStatusMessage);
		sl.putConstraint(SpringLayout.EAST, lblActionStatusMessage, 0,SpringLayout.EAST, lblActionNameType);
		sl.putConstraint(SpringLayout.WEST, lblActionStatusMessage, 0, SpringLayout.WEST, lblActionNameType);

		this.add(btnStartPause);
		this.add(btnStop);
		this.add(btnConfigs);
		
		sl.putConstraint(SpringLayout.NORTH, btnStartPause, 0, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnStartPause, 0, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST, btnStartPause, 0, SpringLayout.WEST, btnStop);
		sl.putConstraint(SpringLayout.WEST, btnStartPause, -50, SpringLayout.EAST, btnStartPause);
		
		sl.putConstraint(SpringLayout.NORTH, btnStop, 0, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnStop, 0, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST, btnStop, 0, SpringLayout.WEST, btnConfigs);
		sl.putConstraint(SpringLayout.WEST, btnStop, -50, SpringLayout.EAST, btnStop);
		
		sl.putConstraint(SpringLayout.NORTH, btnConfigs, 0, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnConfigs, 0, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST, btnConfigs, 0, SpringLayout.EAST, this);
		sl.putConstraint(SpringLayout.WEST, btnConfigs, -50, SpringLayout.EAST, btnConfigs);
		
	}
	
}
