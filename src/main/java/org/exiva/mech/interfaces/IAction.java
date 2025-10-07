package org.exiva.mech.interfaces;

import java.util.Collection;

/**
 * 
 */
public interface IAction extends Runnable{
	
	/**
	 * @return the action name
	 */
	public String getActionName();
	/**
	 * @return the action type
	 */
	public String getActionType();
	/**
	 * @return {@link ActionStatus} - the actual status
	 */
	public ActionStatus getStatus();
	public String getStatusMessage();
	
	public Boolean addNextActions(IAction action);
	public Boolean removeNextActions(IAction action);
	public Collection<IAction> getNextActions(); 
	
	public Boolean addActionListener(IActionListener listener);
	public Boolean removeActionListener(IActionListener listener);
	public Collection<IActionListener> getActionsListeners(); 
	
	public void startAction();
	public void stopAction();
	public void pauseAction();
	
	
}
