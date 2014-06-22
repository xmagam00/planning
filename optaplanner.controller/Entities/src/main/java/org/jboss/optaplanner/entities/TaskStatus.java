package org.jboss.optaplanner.entities;

/**
 * status of task
 * 
 * @author martin
 * 
 */
public enum TaskStatus {
	NEW, // new task entered to the system
	WAITING, // waiting in the queue for execution by a free executor
	IN_PROGRESS, // task being computed at the moment
	PAUSED, // paused computation
	COMPLETE, // completed computation
	MODIFIED // moodified task, this is a new task created from either PAUSED or

}
