package org.exiva.mech.ux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

import org.exiva.mech.interfaces.ActionStatus;
import org.exiva.mech.interfaces.IAction;
import org.exiva.mech.interfaces.IActionListener;

public class JActionPanel extends JPanel implements IActionListener{

	private static final long serialVersionUID = 1L;

	private IAction action;
	public IAction getAction() {
		return this.action;
	}
	
	public JActionPanel(IAction action) {
		if(action==null) throw new NullPointerException("action is null");
		this.action = action;		
		this.action.addActionListener(this);
		this.setMinimumSize(new Dimension(400, 40));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		this.setPreferredSize(getMinimumSize());		
		this.setupComponents();
		this.setupEvents();
	}
	
	private JButton btnUp = new JButton("↑");
	private JButton btnDw = new JButton("↓");
	
	private JLabel  lblActionNameType;
	private JLabel  lblActionStatusMessage;
	
	private JButton btnStartPause = new JButton("▶");
	private JButton btnStop = new JButton("⏹");
	private JButton btnConfigs = new JButton("⚙");
	private JButton btnDelete = new JButton("X");
	
	private void setupComponents() {
		this.setBorder(new LineBorder(Color.BLACK));
		SpringLayout sl = new SpringLayout();		
		this.setLayout(sl);
		
		this.add(btnUp);
		this.add(btnDw);
		
		sl.putConstraint(SpringLayout.NORTH, btnUp, 0, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnUp, 20, SpringLayout.NORTH, btnUp);
		sl.putConstraint(SpringLayout.EAST, btnUp, 40, SpringLayout.WEST, btnUp);
		sl.putConstraint(SpringLayout.WEST, btnUp, 0, SpringLayout.WEST, this);
		
		sl.putConstraint(SpringLayout.NORTH, btnDw, -20, SpringLayout.SOUTH, btnDw);
		sl.putConstraint(SpringLayout.SOUTH, btnDw, 0, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST, btnDw, 0,SpringLayout.EAST, btnUp);
		sl.putConstraint(SpringLayout.WEST, btnDw, 0, SpringLayout.WEST, this);
		
		
		
		lblActionNameType = new JLabel("("+this.action.getActionType() + ") - " + this.action.getActionName());
		lblActionStatusMessage = new JLabel("");
		
		this.add(lblActionNameType);
		this.add(lblActionStatusMessage);

		sl.putConstraint(SpringLayout.NORTH, lblActionNameType, 0, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, lblActionNameType, 20, SpringLayout.NORTH, lblActionNameType);
		sl.putConstraint(SpringLayout.EAST, lblActionNameType, 0, SpringLayout.WEST, btnStartPause);
		sl.putConstraint(SpringLayout.WEST, lblActionNameType, 0, SpringLayout.EAST, btnUp);
		
		sl.putConstraint(SpringLayout.NORTH, lblActionStatusMessage, 0, SpringLayout.SOUTH, lblActionNameType);
		sl.putConstraint(SpringLayout.SOUTH, lblActionStatusMessage, 20, SpringLayout.NORTH, lblActionStatusMessage);
		sl.putConstraint(SpringLayout.EAST, lblActionStatusMessage, 0,SpringLayout.EAST, lblActionNameType);
		sl.putConstraint(SpringLayout.WEST, lblActionStatusMessage, 0, SpringLayout.WEST, lblActionNameType);

		this.add(btnStartPause);
		this.add(btnStop);
		this.add(btnConfigs);
		this.add(btnDelete);
		
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
		sl.putConstraint(SpringLayout.EAST, btnConfigs, 0, SpringLayout.WEST, btnDelete);
		sl.putConstraint(SpringLayout.WEST, btnConfigs, -50, SpringLayout.EAST, btnConfigs);	
		
		sl.putConstraint(SpringLayout.NORTH, btnDelete, 0, SpringLayout.NORTH, this);
		sl.putConstraint(SpringLayout.SOUTH, btnDelete, 0, SpringLayout.SOUTH, this);
		sl.putConstraint(SpringLayout.EAST, btnDelete, 0, SpringLayout.EAST, this);
		sl.putConstraint(SpringLayout.WEST, btnDelete, -50, SpringLayout.EAST, btnDelete);	
	}
	
	private void setupEvents() {
		this.btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnUp.setEnabled(false);
			}
		});
		this.btnDw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnDw.setEnabled(false);
			}
		});
		this.btnStartPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JActionPanel.this.action.startAction();
			}
		});
		
		this.btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JActionPanel.this.action.stopAction();
			}
		});
		this.btnConfigs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JActionConfig(JActionPanel.this.action);
			}
		});
		this.btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(JActionPanel.this, "Confirm Delete?", "Delete Action", JOptionPane.YES_NO_OPTION);
				if(r==JOptionPane.YES_OPTION) {
					System.out.println(r);
				}
			}
		});
	}

	@Override
	public void updatedStatus(ActionStatus status) {
		this.updatedMessage(this.action.getStatusMessage());
		switch (status) {
		case READY:
		case STOPPED:
			btnStartPause.setText("▶");
			break;
		default:
			btnStartPause.setText("❚❚");
			break;
		}
	}

	@Override
	public void updatedMessage(String message) {
		lblActionStatusMessage.setText("("+action.getStatus().toString()+") -" + message);
	}
	
}
