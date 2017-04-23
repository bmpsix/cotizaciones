package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.IndustrySector;
import com.unimer.cotizaciones.entities.LogIndustrySector;
import com.unimer.cotizaciones.entities.TraceResponse;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.IndustrySectorJpaRepository;
import com.unimer.cotizaciones.repositories.LogIndustrySectorJpaRepository;
import com.unimer.cotizaciones.services.IndustrySectorService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("industrySectorServiceImpl")
public class IndustrySectorServiceImpl implements IndustrySectorService{


	@Autowired
	@Qualifier("industrySectorJpaRepository")
	private IndustrySectorJpaRepository industrySectorJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logIndustrySectorJpaRepository")
	private LogIndustrySectorJpaRepository logIndustrySectorJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;

	private static final Log LOG = LogFactory.getLog(IndustrySectorServiceImpl.class);
	
	String ipCliente="";
	
	@Override
	public IndustrySector addIndustrySector(IndustrySector industrySector) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Industry sector");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Industry sector");
			consecutive.setPrefix("STI");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Industry sector table");
			consecutivesJpaRepository.save(consecutive);
			industrySector.setIdIndustrySector(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!industrySector.getIdIndustrySector().equals(industrySectorJpaRepository.findOne(industrySector.getIdIndustrySector()))) {
				
				industrySectorJpaRepository.save(industrySector);
				LOG.info("METHOD: addIndustrySector in IndustrySectorServiceImpl -- PARAMS: " + industrySector.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se actualizo un nuevo sector de industria");
			} else {
				updateIndustrySector(industrySector);
			}

		} else if (industrySector.getIdIndustrySector() == null) {

			industrySector.setIdIndustrySector(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!industrySector.getIdIndustrySector().equals(industrySectorJpaRepository.findOne(industrySector.getIdIndustrySector()))) {
				LOG.info("METHOD: addIndustrySector in IndustrySectorServiceImpl -- PARAMS: " + industrySector.toString());
				industrySectorJpaRepository.save(industrySector);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
				insertBinnacle("Se agrego un nuevo sector de industria");
			} else {
				updateIndustrySector(industrySector);
			}
		} else {
			updateIndustrySector(industrySector);
		}
		return industrySector;
	}

	
	@Override
	public List<IndustrySector> listAllIndustrySectors() {
		return industrySectorJpaRepository.findAll();
	}	

	@Override
	public IndustrySector findById(String idIndustrySector) {
		return industrySectorJpaRepository.findByIdIndustrySector(idIndustrySector);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Industry sector");
	}

	private void updateIndustrySector(IndustrySector industrySector) {
		java.util.Date date = new Date();
		IndustrySector industrySectorToUpdate = industrySectorJpaRepository.findByIdIndustrySector(industrySector.getIdIndustrySector());
		if (industrySectorToUpdate != null) {
			LogIndustrySector logIndustrySector = new LogIndustrySector(date, "IndustrySector  modified", "test", industrySectorToUpdate.getDetail(), industrySectorToUpdate.getIdIndustrySector());
			industrySectorJpaRepository.save(industrySector);
			logIndustrySectorJpaRepository.save(logIndustrySector);
			
			insertBinnacle("Se actualizo un nuevo sector de industria");
		}
	}
	
	@Override
	public void IP(String ip) {
		ipCliente=ip;
		
	}
	
	private void insertBinnacle(String msg)
	{
		Date date = new Date();
		TraceResponse traceResponse = new TraceResponse(null,"test",msg,ipCliente,date);
		traceResponseService.addTraceResponse(traceResponse);
	}


	@Override
	public long rowCount() {
		
		return industrySectorJpaRepository.count();
	}

}
