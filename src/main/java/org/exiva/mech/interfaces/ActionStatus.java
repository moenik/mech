package org.exiva.mech.interfaces;

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
