package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unimer.cotizaciones.entities.BillingScenario;
import com.unimer.cotizaciones.entities.Proposal;

@Repository("billingScenarioJpaRepository")
public interface BillingScenarioJpaRepository extends JpaRepository<BillingScenario, Serializable> {
	
	public abstract List<BillingScenario> findByProposal(Proposal proposal);

	public abstract BillingScenario findByIdBillingScenario(int idBillingScenario);
	
}
