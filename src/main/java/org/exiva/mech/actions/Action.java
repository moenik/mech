package org.exiva.mech.actions;

import java.util.Collection;
import java.util.Vector;

import org.exiva.mech.interfaces.ActionStatus;
import org.exiva.mech.interfaces.IAction;
import org.exiva.mech.interfaces.IActionListener;
import org.exiva.mech.utils.Words;

/**
 * The default action, every other action can be extended from this class and override run method.</br>
 * It implements {@link IAction} whith default values and works as a delay of 1 second.
 */
public class Action implements IAction{

	private String actionName;
	private String actionType;
	private ActionStatus status;
	private String statusMessage;
	private Collection<IAction> prevActions;
	private Collection<IAction> nextActions;
	private Collection<IActionListener> listeners;
	
	/**
	 * Creates an action
	 * @param actionName - the action name
	 * @param actionType - the action type
	 */
	protected Action(String actionName, String actionType) {
		this.actionName=actionName==null?Words.randomAdjectiveNoum(null):actionName;
		this.actionType=actionType;
		this.prevActions = new Vector<IAction>();
		this.nextActions = new Vector<IAction>();
		this.listeners = new Vector<IActionListener>();
		this.setStatus(this.actionName.equals("AWAYS_STOPPED")?ActionStatus.STOPPED:ActionStatus.READY);
		this.setStatusMessage("");
	}
	
	/**
	 * Creates an action
	 * @param actionName - the action name
	 */
	public Action(String actionName) {
		this(actionName, "DefaultAction");
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
	/**
	 * Update the status of action<br>
	 * The listeners will be triggered
	 * @param status - {@link ActionStatus} status to be changed
	 */
	public void setStatus(ActionStatus status) {
		this.status = status;
		for (IActionListener iActionListener : listeners) {
			iActionListener.updatedStatus(status);
		}
	}
	
	/**
	 * Update the message of action<br>
	 * The listeners will be triggered
	 * @param message - message to be changed
	 */
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
	public Boolean addPrevActions(IAction action, Boolean referBack) {
		if(this.getPrevActions().contains(action)) { 
			return false;
		}
		Boolean b = this.prevActions.add(action);
		if(referBack) {
			b = b && action.addNextActions(this, false);
		}
		return b;
	}
	
	@Override
	public Boolean addPrevActions(IAction action) {
		return this.addPrevActions(action, true);
	}

	@Override
	public Boolean removePrevActions(IAction action, Boolean referBack) {
		if(!this.getPrevActions().contains(action)) { 
			return false;
		}
		Boolean b = this.getPrevActions().remove(action);
		if(referBack) {
			b = b && action.removeNextActions(this,false);
		}
		return b;
	}
	
	@Override
	public Boolean removePrevActions(IAction action) {
		return this.removePrevActions(action, true);
	}

	@Override
	public Collection<IAction> getPrevActions() {
		return this.prevActions;
	}	

	@Override
	public Boolean addNextActions(IAction next, Boolean referBack) {
		if(this.getNextActions().contains(next)) { 
			return false;
		}
		Boolean b = this.nextActions.add(next);
		if(referBack) {
			next.addPrevActions(this, false);
		}
		return b;
	}
	
	@Override
	public Boolean addNextActions(IAction action) {
		return addNextActions(action, true);
	}

	@Override
	public Boolean removeNextActions(IAction action, Boolean referBack) {
		if(!this.getNextActions().contains(action)) {
			return false;
		}
		Boolean b = this.getNextActions().remove(action);
		if(referBack) {
			b = b && action.removePrevActions(this, false);
		}
		return b;
	}
	
	@Override
	public Boolean removeNextActions(IAction action) {
		return this.removeNextActions(action, true);
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
		case PAUSED:
			setStatus(ActionStatus.RUNNING);
		default:
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
	
	/**
	 * Call next actions, one at a time, wait to start when is ready/stopped
	 */
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
