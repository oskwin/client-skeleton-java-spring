package eu.arrowhead.application.skeleton.executor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.arrowhead.application.skeleton.executor.service.ExecutorService;
import eu.arrowhead.common.CommonConstants;

@RestController
public class ExecutorController {

	//=================================================================================================
	// members

	@Autowired
	private ExecutorService executorService;
	
	//TODO: add your variables here

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = CommonConstants.ECHO_URI)
	public String echo() {
		return "Got it!";
	}
	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(path = "/start", produces = MediaType.APPLICATION_JSON_VALUE) //TODO: use values from CommonConstans after new release of client library
	public void start(@RequestBody final Object request) { //TODO: change input to ChoreographerExecuteStepRequestDTO after new release
		//TODO validate
		executorService.startExecution(request);
	}
	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(path = "/abort", produces = MediaType.APPLICATION_JSON_VALUE) //TODO: use values from CommonConstans after new release of client library
	public void abort(@RequestBody final Object request) { //TODO: change input to ChoreographerAbortStepRequestDTO after new release
		//TODO validate
		executorService.abortExecution(request);
	}
	
	//-------------------------------------------------------------------------------------------------
	//TODO: implement here your provider related REST end points
}
