package com.unimer.cotizaciones.services.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.TraceResponseService;


@Service("traceResponseServiceImpl")
public class TraceResponseServiceImpl implements TraceResponseService {

	
	
	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;

	@Override
	public List<TraceResponse> listAllTraceResponse() {
		return traceResponseJpaRepository.findAll();
	}
	
	private static final Log LOG = LogFactory.getLog(ClientContactServiceImpl.class);

	@Override
	public void addTraceResponse(TraceResponse traceResponse) {
	
				traceResponseJpaRepository.save(traceResponse);
				LOG.info("METHOD: addTraceResponse in TraceResponseServiceImpl -- PARAMS: " + traceResponse.toString());
				
		
	}
	

	
	
	
}
