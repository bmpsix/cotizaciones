package com.unimer.cotizaciones.services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.BillingScenario;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.services.BillingScenarioService;


@Service("billingScenarioServiceImpl")
public class BillingScenarioServiceImpl implements BillingScenarioService {

	
	private static final Log LOG = LogFactory.getLog(BillingScenarioService.class);
	
	
	@Override
	public List<BillingScenario> findByProposal(Proposal proposal) {
		return null;
	}

	@Override
	public void addBillingScenario(BillingScenario BillingScenario) {
		// TODO Auto-generated method stub

	}

}
