package org.exiva.mech.actions;

import java.util.Vector;

import org.exiva.mech.interfaces.ActionStatus;
import org.exiva.mech.interfaces.IAction;

public class Action implements IAction{

	private String actionName;
	private String actionType;
	private ActionStatus status;
	private String statusMessage;
	private Vector<IAction> nextActions;
	
	
	public Action(String actionName, String type) {
		this.actionName=actionName;
		this.actionType=type;
		this.nextActions = new Vector<IAction>();
		this.setStatus(ActionStatus.READY);
		this.setStatusMessage("");
	}
	
	@Override
	public String getActionName() {
		return this.actionName;
	}

	@Override
	public String getActionType() {
		return this.actionType;
	}
	
	@Override
	public ActionStatus getStatus() {
		return this.status;
	}
	public void setStatus(ActionStatus status) {
		this.status = status;
	}
	
	public void setStatusMessage(String message) {
		this.statusMessage=message;
	}
	@Override
	public String getStatusMessage() {
		return this.statusMessage;
	}

	@Override
	public void addNextActions(IAction action) {
		this.nextActions.add(action);
	}

	@Override
	public Boolean removeNextActions(IAction action) {
		return this.nextActions.remove(action);
	}

	@Override
	public Vector<IAction> getNextActions() {
		return this.nextActions;
	}

	@Override
	public void startAction() {
		switch (this.status) {
		case RUNNING:
			break;
		case READY:
			new Thread(this).start();
		default:
			this.status=ActionStatus.RUNNING;
			break;
		}
	}

	@Override
	public void stopAction() {
		switch (this.status) {
		case RUNNING:
		case CALLING_NEXT:
		case PAUSED:
			this.status=ActionStatus.STOPPED;
			break;
		default:
			break;
		}
	}

	@Override
	public void pauseAction() {
		switch (this.status) {
		case RUNNING:
		case CALLING_NEXT:
			this.status=ActionStatus.PAUSED;
			break;
		default:
			break;
		}
	}
	
	protected void callNext() {
		for (int i = 0; i < this.getNextActions().size(); i++) {
			if(this.getNextActions().get(i).getStatus()==ActionStatus.READY) {
				this.getNextActions().get(i).startAction();
			}else {
				i--;
			}
		}
	}
	
	@Override
	public void run() {
		setStatus(ActionStatus.RUNNING);
		setStatus(ActionStatus.FINISHED);
		setStatus(ActionStatus.CALLING_NEXT);
		this.callNext();
		setStatus(ActionStatus.ENDED);
		setStatus(ActionStatus.READY);
	}	

}
