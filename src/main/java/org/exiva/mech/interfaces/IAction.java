package org.exiva.mech.interfaces;

import java.util.Collection;

/**
 * Action Interface 
 * An Action is a Runnable task that can be started, stopped, and paused.
 * It maintains a status and can notify listeners about status changes.
 * It can also have dependencies on other actions, allowing for complex workflows.
 * @author moenik
 *  
 * @see {@link ActionStatus}
 * @see {@link IActionListener}
 * @see {@link Runnable}
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
	/**
	 * @return the current Status message
	 */
	public String getStatusMessage();
	
	/**
	 * @param action - the action to be added
	 * @param referBack - if true sets this as PrevAction of action 
	 * @return true if OK
	 */
	public Boolean addNextActions(IAction action, Boolean referBack);
	/**
	 * @param action - the action to be added
	 * @return true if OK
	 */
	public Boolean addNextActions(IAction action);
	/**
	 * @param action - the action to be removed
	 * @param referBack - if true remove this from prev actions of action 
	 * @return true if OK
	 */
	public Boolean removeNextActions(IAction action, Boolean referBack);
	/**
	 * @param action - the action to be removed
	 * @return true if OK
	 */
	public Boolean removeNextActions(IAction action);
	/**
	 * @return list of next Actions to run - {@link Collection}<{@link IAction}>
	 */
	public Collection<IAction> getNextActions(); 
	
	/**
	 * @param action - the action to be added
	 * @param referBack - if true sets this as PrevAction of action 
	 * @return true if OK
	 */
	public Boolean addPrevActions(IAction action, Boolean referBack);
	
	/**
	 * @param action - the action to be added
	 * @return true if OK
	 */
	public Boolean addPrevActions(IAction action);
	
	/**
	 * @param action - the action to be removed
	 * @param referBack - if true remove this from next actions of action 
	 * @return true if OK
	 */
	public Boolean removePrevActions(IAction action, Boolean referBack);
	/**
	 * @param action - the action to be removed
	 * @return true if OK
	 */
	public Boolean removePrevActions(IAction action);
	/**
	 * @return list of prev Actions to run - {@link Collection}<{@link IAction}>
	 */
	public Collection<IAction> getPrevActions(); 
	
	/**
	 * @param listener - {@link IActionListener}
	 * @return True if added
	 */
	public Boolean addActionListener(IActionListener listener);
	/**
	 * @param listener - {@link IActionListener}
	 * @return True if removed
	 */
	public Boolean removeActionListener(IActionListener listener);
	/**
	 * @return all listeners - {@link Collection}<{@link IActionListener}>
	 */
	public Collection<IActionListener> getActionsListeners(); 
	
	/**
	 * Start action - see {@link ActionStatus}
	 */
	public void startAction();
	/**
	 * Stop action - see {@link ActionStatus}
	 */
	public void stopAction();
	/**
	 * Pause action - see {@link ActionStatus}
	 */
	public void pauseAction();
	
	
	
}
