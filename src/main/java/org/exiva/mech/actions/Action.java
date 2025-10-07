package org.exiva.mech.actions;

import java.util.Collection;
import java.util.Vector;

import org.exiva.mech.interfaces.ActionStatus;
import org.exiva.mech.interfaces.IAction;
import org.exiva.mech.interfaces.IActionListener;

public class Action implements IAction{

	private String actionName;
	private String actionType;
	private ActionStatus status;
	private String statusMessage;
	private Collection<IAction> nextActions;
	private Collection<IActionListener> listeners;
	
	/**
	 * Creates an action
	 * @param actionName - the action name
	 * @param actionType - the action type
	 */
	public Action(String actionName, String actionType) {
		this.actionName=actionName;
		this.actionType=actionType;
		this.nextActions = new Vector<IAction>();
		this.listeners = new Vector<IActionListener>();
		this.setStatus(actionName.equals("AWAYS_STOPPED")?ActionStatus.STOPPED:ActionStatus.READY);
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
		for (IActionListener iActionListener : listeners) {
			iActionListener.updatedStatus(status);
		}
	}
	
	public void setStatusMessage(String message) {
		this.statusMessage=message;
		for (IActionListener iActionListener : listeners) {
			iActionListener.updatedMessage(message);
		}
	}
	@Override
	public String getStatusMessage() {
		return this.statusMessage;
	}

	@Override
	public Boolean addNextActions(IAction action) {
		return this.nextActions.add(action);
	}

	@Override
	public Boolean removeNextActions(IAction action) {
		return this.nextActions.remove(action);
	}

	@Override
	public Collection<IAction> getNextActions() {
		return this.nextActions;
	}
	
	@Override
	public Boolean addActionListener(IActionListener listener) {
		return this.listeners.add(listener);
	}
	@Override
	public Boolean removeActionListener(IActionListener listener) {
		return this.listeners.remove(listener);
	}
	@Override
	public Collection<IActionListener> getActionsListeners(){
		return this.listeners;
	}

	@Override
	public void startAction() {
		switch (this.status) {
		case RUNNING:
			break;
		case READY:
			new Thread(this).start();
		default:
			setStatus(ActionStatus.RUNNING);
			break;
		}
	}

	@Override
	public void stopAction() {
		switch (this.status) {
		case RUNNING:
		case CALLING_NEXT:
		case PAUSED:
			setStatus(ActionStatus.STOPPED);
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
			setStatus(ActionStatus.PAUSED);
			break;
		default:
			break;
		}
	}
	
	protected void callNext() {

		IAction nextActions[] = new IAction[this.getNextActions().size()];
		this.getNextActions().toArray(nextActions);
		for (int i = 0; i < nextActions.length; i++) {
			this.setStatusMessage(this.getActionName()+"("+this.getStatus().toString()+") -> Calling "+ nextActions[i].getActionName()+"("+nextActions[i].getStatus().toString()+")");
			if(nextActions[i].getStatus()==ActionStatus.READY || nextActions[i].getStatus()==ActionStatus.STOPPED ) {
				nextActions[i].startAction();
			}else {
				i--;
			}			
		}
	}
	
	@Override
	public void run() {
		setStatus(ActionStatus.RUNNING);
		setStatusMessage("delay 1000ms");
		try { Thread.sleep(1000); } catch (InterruptedException e) { }
		if(this.getStatus()==ActionStatus.STOPPED) {
			setStatusMessage("Stopped");
			return;
		}
		setStatus(ActionStatus.FINISHED);
		setStatus(ActionStatus.CALLING_NEXT);
		this.callNext();
		setStatusMessage("");
		setStatus(ActionStatus.ENDED);
		setStatus(ActionStatus.READY);
	}	

}
