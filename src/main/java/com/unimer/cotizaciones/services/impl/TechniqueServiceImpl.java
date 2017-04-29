package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Technique;
import com.unimer.cotizaciones.entities.LogTechnique;
import com.unimer.cotizaciones.repositories.TechniqueJpaRepository;
import com.unimer.cotizaciones.repositories.TraceResponseJpaRepository;
import com.unimer.cotizaciones.repositories.LogTechniqueJpaRepository;
import com.unimer.cotizaciones.services.TechniqueService;
import com.unimer.cotizaciones.services.TraceResponseService;

@Service("TechniqueServiceImpl")
public class TechniqueServiceImpl implements TechniqueService{


	@Autowired
	@Qualifier("TechniqueJpaRepository")
	private TechniqueJpaRepository TechniqueJpaRepository;

	
	@Autowired
	@Qualifier("logTechniqueJpaRepository")
	private LogTechniqueJpaRepository logTechniqueJpaRepository;

	@Autowired
	@Qualifier("traceResponseJpaRepository")
	private TraceResponseJpaRepository traceResponseJpaRepository;
	
	@Autowired
	@Qualifier("traceResponseServiceImpl")
	private TraceResponseService traceResponseService;
	
	String ipCliente="";
	
	
	private static final Log LOG = LogFactory.getLog(TechniqueServiceImpl.class);
	
	
	@Override
	public void addTechnique(Technique Technique) {

		

			if (Technique.getIdTechnique()==0) {
				
				TechniqueJpaRepository.save(Technique);
				LOG.info("METHOD: addTechnique in TechniqueServiceImpl -- PARAMS: " + Technique.toString());
			

			} else {
				updateTechnique(Technique);
			}

		}

	
	@Override
	public List<Technique> listAllTechniques() {
		return TechniqueJpaRepository.findAll();
	}	

	@Override
	public Technique findById(int idTechnique) {
		return TechniqueJpaRepository.findByIdTechnique(idTechnique);
	}

	
	private void updateTechnique(Technique Technique) {
		java.util.Date date = new Date();
		Technique TechniqueToUpdate = TechniqueJpaRepository.findByIdTechnique(Technique.getIdTechnique());
		if (TechniqueToUpdate != null) {
			LogTechnique logTechnique = new LogTechnique(date, "Technique  modified", "test", TechniqueToUpdate.getDetail(), TechniqueToUpdate.getIdTechnique());
			TechniqueJpaRepository.save(Technique);
			logTechniqueJpaRepository.save(logTechnique);
			
		}
	}
	

}



