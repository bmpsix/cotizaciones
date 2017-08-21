package com.unimer.cotizaciones.services;

import java.util.List;

import com.unimer.cotizaciones.entities.BillingScenario;
import com.unimer.cotizaciones.entities.Proposal;

public interface BillingScenarioService {

	public abstract List<BillingScenario> findByProposal(Proposal proposal,double total);
	
	public abstract BillingScenario findById(int idBillingScenario);
	
	public abstract void addBillingScenario(BillingScenario billingScenario);
	
	public abstract void editBillingScenario(BillingScenario billingScenario);
}
