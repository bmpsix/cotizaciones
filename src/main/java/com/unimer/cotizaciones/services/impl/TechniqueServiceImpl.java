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
import com.unimer.cotizaciones.repositories.LogTechniqueJpaRepository;
import com.unimer.cotizaciones.services.TechniqueService;

@Service("TechniqueServiceImpl")
public class TechniqueServiceImpl implements TechniqueService{


	@Autowired
	@Qualifier("TechniqueJpaRepository")
	private TechniqueJpaRepository TechniqueJpaRepository;

	
	@Autowired
	@Qualifier("logTechniqueJpaRepository")
	private LogTechniqueJpaRepository logTechniqueJpaRepository;
	
	
	private static final Log LOG = LogFactory.getLog(TechniqueServiceImpl.class);
	
	
	@Override
	public void addTechnique(Technique Technique, int idUser) {

		

			if (Technique.getIdTechnique()==0) {
				
				TechniqueJpaRepository.save(Technique);
				LOG.info("METHOD: addTechnique in TechniqueServiceImpl -- PARAMS: " + Technique.toString());
			

			} else {
				updateTechnique(Technique, idUser);
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

	
	private void updateTechnique(Technique Technique, int idUser) {
		java.util.Date date = new Date();
		Technique TechniqueToUpdate = TechniqueJpaRepository.findByIdTechnique(Technique.getIdTechnique());
		if (TechniqueToUpdate != null) {
			LogTechnique logTechnique = new LogTechnique(date, "Technique  modified", idUser, TechniqueToUpdate.getDetail(), TechniqueToUpdate.getIdTechnique());
			TechniqueJpaRepository.save(Technique);
			logTechniqueJpaRepository.save(logTechnique);
			
		}
	}


	@Override
	public List<Technique> orderlistAllTechniques() {
		return TechniqueJpaRepository.orderListAll();
	}
	

}



