package org.exiva.mech.interfaces;

public interface IActionListener {

	/**
	 * When status is updated
	 * @param status - {@link ActionStatus}
	*/
	public void updatedStatus(ActionStatus status);
	/**
	 * When Message is updated
	 * @param message - message
	 */
	public void updatedMessage(String message);
	
}
