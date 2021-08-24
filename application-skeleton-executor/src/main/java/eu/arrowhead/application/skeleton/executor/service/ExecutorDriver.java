package eu.arrowhead.application.skeleton.executor.service;

import org.springframework.stereotype.Service;

@Service
public class ExecutorDriver {

	public void notifyChoreographer(final Long sessionId, final Long sessionStepId, final Object status, //TODO ChoreographerExecutedStepStatus after new release
									final String message, final String exception) {
		//TODO implement		
	}
}
