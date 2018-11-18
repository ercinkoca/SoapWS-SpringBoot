package com.ercin.example.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ercin.example.Application;
import com.ercin.example.generated.GetAllRequest;
import com.ercin.example.generated.GetAllResponse;
import com.ercin.example.generated.GetValueByKeyRequest;
import com.ercin.example.generated.OutputExampleList;
import com.ercin.example.memory.ValueRepository;



@Endpoint
public class WebServiceEndpoint {
	
	private static final Logger logger = LogManager.getLogger(WebServiceEndpoint.class);
	
	@Autowired
	private ValueRepository valueRepo;
	
	@PayloadRoot(namespace = "http://www.howtodoinjava.com/xml/school", localPart = "GetAllRequest")
	@ResponsePayload
	public GetAllResponse getAll(@RequestPayload GetAllRequest request) {
		GetAllResponse response = new GetAllResponse();
		logger.info("getAll metodu çagrıldı.");
		
		OutputExampleList outputExampleArrList=valueRepo.getAll();
		
		response.setOutputExample(outputExampleArrList);
		
		return response;
	}
	
	@PayloadRoot(namespace = "http://www.howtodoinjava.com/xml/school", localPart = "GetValueByKeyRequest")
	@ResponsePayload
	public GetAllResponse getValueByKey(@RequestPayload GetValueByKeyRequest request) {
		logger.info("getValueByKey/"+request.getKey()+" metodu çagrıldı.");
		GetAllResponse response = new GetAllResponse();
		String value="";
		value=valueRepo.getValueByKey(request.getKey());
		OutputExampleList outputExampleArrList=new OutputExampleList();
		outputExampleArrList.getValue().add(value);
		response.setOutputExample(outputExampleArrList);
		
		return response;
	}
	
	
	

}
