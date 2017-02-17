package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogTechnique;
import com.unimer.cotizaciones.entities.Technique;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogTechniqueJpaRepository;
import com.unimer.cotizaciones.repositories.TechniqueJpaRepository;
import com.unimer.cotizaciones.services.TechniqueService;

@Service("TechniqueImpl")
public class TechniqueServiceImpl implements TechniqueService{

	
	@Autowired
	@Qualifier("TechniqueJpaRepository")
	private TechniqueJpaRepository TechniqueJpaRepository; 
	
	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;
	
	@Autowired
	@Qualifier("logTechniqueJpaRepository")
	private LogTechniqueJpaRepository logTechniqueJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(TechniqueServiceImpl.class);
	
	@Override
	public Technique  addSTechnique(Technique Technique) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Technique");

		if (consecutive == null) {
			consecutive = new Consecutive();
			consecutive.setType("Technique");
			consecutive.setPrefix("TEC");
			consecutive.setSubfix(1);
			consecutive.setDetail("Default consecutive of Technique table");
			consecutivesJpaRepository.save(consecutive);
			Technique.setIdTechnique(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!Technique.getIdTechnique().equals(TechniqueJpaRepository.findOne(Technique.getIdTechnique()))) {
				
				TechniqueJpaRepository.save(Technique);
				LOG.info("METHOD: addTechnique in TechniqueServiceImpl -- PARAMS: " + Technique.toString());
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);

			} else {
				updateTechnique(Technique);
			}

		} else if (Technique.getIdTechnique() == null) {

			Technique.setIdTechnique(consecutive.getPrefix() + "-" + consecutive.getSubfix());
			
			if (!Technique.getIdTechnique().equals(TechniqueJpaRepository.findOne(Technique.getIdTechnique()))) {
				LOG.info("METHOD: addTechnique in TechniqueServiceImpl -- PARAMS: " + Technique.toString());
				TechniqueJpaRepository.save(Technique);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else {
				updateTechnique(Technique);
			}
		} else {
			updateTechnique(Technique);
		}
		return Technique;
	}

	
	@Override
	public List<Technique> listAllTechniques() {
		return TechniqueJpaRepository.findAll();
	}	

	@Override
	public Technique findById(String idTechnique) {
		
		
		return TechniqueJpaRepository.findByIdTechnique(idTechnique);
	}

	@Override
	public Consecutive getConsecutive() {
		return consecutivesJpaRepository.findByType("Technique");
	}

	private void updateTechnique(Technique Technique) {
		java.util.Date date = new Date();
		Technique TechniqueToUpdate = TechniqueJpaRepository.findByIdTechnique(Technique.getIdTechnique());
		if (TechniqueToUpdate != null) {
			LogTechnique logTechnique = new LogTechnique(date, "Technique  modified", "test",TechniqueToUpdate.getIdTechnique() ,TechniqueToUpdate.getDetail());
			TechniqueJpaRepository.save(Technique);
			logTechniqueJpaRepository.save(logTechnique);
		}
	}
	

}


