package com.unimer.cotizaciones.services.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.services.TraceResponseService;


@Service("traceResponseServiceImpl")
public class TraceResponseServiceImpl implements TraceResponseService {

	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;

	@Override
	public List<TraceResponse> listAllTraceResponse() {
		return traceResponseJpaRepository.findAll();
	}
	
	private static final Log LOG = LogFactory.getLog(ClientContactServiceImpl.class);

	@Override
	public TraceResponse addTraceResponse(TraceResponse traceResponse) {
		Consecutive consecutive = consecutivesJpaRepository.findByType("Binnacle");
		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Binnacle");
			consecutive.setPrefix("BNC");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive for Binnacle");
			consecutivesJpaRepository.save(consecutive);
			traceResponse.setIdTraceResponse(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!traceResponse.getIdTraceResponse().equals(traceResponseJpaRepository.findOne(traceResponse.getIdTraceResponse()))) {
				traceResponseJpaRepository.save(traceResponse);
				LOG.info("METHOD: addTraceResponse in TraceResponseServiceImpl -- PARAMS: " + traceResponse.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				
			} 

		} else if (traceResponse.getIdTraceResponse() == null) {

			traceResponse.setIdTraceResponse(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!traceResponse.getIdTraceResponse().equals(traceResponseJpaRepository.findOne(traceResponse.getIdTraceResponse()))) {
				LOG.info("METHOD: addTraceResponse in TraceResponseServiceImpl -- PARAMS: " + traceResponse.toString());
				traceResponseJpaRepository.save(traceResponse);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} 
		}
		return traceResponse;
	}
	

	
	
	
}
