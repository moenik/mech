package org.exiva.mech.interfaces;

/**
*		[READY]        --> [RUNNING]</br>
*		[RUNNING]      --> [FINISHED]</br>
*		[RUNNING]      --> [STOPPED]</br>
*		[RUNNING]      --> [PAUSED]</br>
*		[PAUSED]       --> [RUNNING]</br>
*		[STOPPED]      --> [READY]</br>
*		[FINISHED]     --> [CALLING_NEXT]</br>
*		[CALLING_NEXT] --> [ENDED]</br>
*		[CALLING_NEXT] --> [PAUSED']</br>
*		[PAUSED']      --> [CALLING_NEXT]</br>
*		[CALLING_NEXT] --> [STOPPED]</br>
*		[PAUSED]       --> [STOPPED]</br>
*		[PAUSED']      --> [STOPPED]</br>
*		[ENDED]        --> [READY]</br>
*
* @author moenik
* @see {@link IAction}
* 
 */
public enum ActionStatus {
	READY,
	RUNNING,
	PAUSED,
	STOPPED,
	FINISHED,
	CALLING_NEXT,
	ENDED
	
	/**
	@startuml
		[READY] --> [RUNNING]
		[RUNNING] --> [FINISHED]
		[RUNNING] --> [STOPPED]
		[RUNNING] --> [PAUSED]
		[PAUSED] --> [RUNNING]
		[STOPPED] --> [READY]
		[FINISHED] --> [CALLING_NEXT]
		[CALLING_NEXT] --> [ENDED]
		[CALLING_NEXT] --> [PAUSED'];
		[PAUSED'] --> [CALLING_NEXT]
		[CALLING_NEXT] --> [STOPPED]
		[PAUSED] --> [STOPPED]
		[PAUSED'] --> [STOPPED]
		[ENDED] --> [READY]
		@enduml	
	*/
}
