package com.unimer.cotizaciones.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.BillingScenario;
import com.unimer.cotizaciones.entities.Proposal;

@Repository("billingScenarioJpaRepository")
public interface BillingScenarioJpaRepository {
	
	public abstract List<BillingScenario> findByProposal(Proposal proposal);

}
