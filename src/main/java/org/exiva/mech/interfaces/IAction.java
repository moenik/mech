package org.exiva.mech.interfaces;

import java.util.Vector;

public interface IAction extends Runnable{
	
	public String getActionName();
	public String getActionType();
	public ActionStatus getStatus();
	public String getStatusMessage();
	
	public void addNextActions(IAction action);
	public Boolean removeNextActions(IAction action);
	public Vector<IAction> getNextActions(); 
	
	public void startAction();
	public void stopAction();
	public void pauseAction();
	
	
}
