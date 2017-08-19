package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.BillingScenario;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.repositories.BillingScenarioJpaRepository;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.SettingsJpaRepository;
import com.unimer.cotizaciones.services.BillingScenarioService;


@Service("billingScenarioServiceImpl")
public class BillingScenarioServiceImpl implements BillingScenarioService {

	@Autowired
	@Qualifier("billingScenarioJpaRepository")
	private BillingScenarioJpaRepository billingScenarioJpaRepository;
	
	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;
	
	@Autowired
	@Qualifier("settingsJpaRepository")
	private SettingsJpaRepository settingsJpaRepository;
	
	private static final Log LOG = LogFactory.getLog(BillingScenarioService.class);
	
	
	@Override
	public List<BillingScenario> findByProposal(Proposal proposal,double total) {
		List<BillingScenario> listBillingScenario = billingScenarioJpaRepository.findByProposal(proposal);
		List<Country> listCountry = countryJpaRepository.findByApplyForCharge((byte)1);
		
		
											//Si esta vacía crea los escenearios
		if(listBillingScenario.isEmpty())
		{
			for(Country country : listCountry)
			{
				BillingScenario billingScenario = new BillingScenario();
				billingScenario.setClientContact(proposal.getClientContact());
				billingScenario.setCountry(country);
				billingScenario.setInitialDate(proposal.getInitialDate());
			
				// calculos
				billingScenario.setTranferenceValue(((country.getTranferenceValue()/100)*total)+total);
				billingScenario.setRemittance(((country.getRemittance()/100)*billingScenario.getTranferenceValue())+billingScenario.getTranferenceValue());
				
				if((country.getExemptTax()==((byte)1)) && (proposal.getUser().getCountry().getIdCountry()!=country.getIdCountry())) billingScenario.setIva(billingScenario.getRemittance());
				else billingScenario.setIva(((country.getIva()/100)*billingScenario.getRemittance())+billingScenario.getRemittance());
				
				billingScenario.setTotalAmount(billingScenario.getIva());
				billingScenario.setProposal(proposal);
				billingScenario.setRegistrationDate(new Date());
				billingScenario.setMethodState((byte)0);
				billingScenario.setSaClient(proposal.getClientContact().getClient().getSaClient());
				
				LOG.info("METHOD: Escenarios vacio  country exemptax" + country.getExemptTax());
				listBillingScenario.add(billingScenario);
			}
			
			return listBillingScenario;
		}
		else 										// Sino esta vacía 
		{
			boolean project = false;
			for(BillingScenario billingScenario : listBillingScenario)
			{
				if(billingScenario.getMethodState()==1)
				{
						project=true; 
						break;
				}
			}
			
			if(project) return listBillingScenario;		// Si ya se eligió un estado del método (escenario de facturacion)
			else 										// Si hay elementos editados y no hay un estado del método seleccionado
			{
					for(Country country : listCountry)
					{
						BillingScenario billingScenario = new BillingScenario();
						billingScenario.setClientContact(proposal.getClientContact());
						billingScenario.setCountry(proposal.getUser().getCountry());
						billingScenario.setInitialDate(proposal.getInitialDate());
						billingScenario.setIva((country.getIva()/100)*total);
						billingScenario.setProposal(proposal);
						billingScenario.setRegistrationDate(new Date());
						billingScenario.setMethodState((byte)0);
						if(country.getExemptTax()==1 && proposal.getUser().getCountry()!=country)billingScenario.setRemittance(0);
						else billingScenario.setRemittance((country.getRemittance()/100)*total);
						billingScenario.setSaClient(proposal.getClientContact().getClient().getSaClient());
						billingScenario.setTranferenceValue(country.getTranferenceValue());
						billingScenario.setTotalAmount(total+billingScenario.getRemittance()+billingScenario.getIva()+billingScenario.getTranferenceValue());
						
						boolean modified=false;
						for(BillingScenario billingS : listBillingScenario)
						{
							if(billingS.getCountry()==billingScenario.getCountry())
							{
								modified=true;
								break;
							}
						
						}
						if(modified==false)listBillingScenario.add(billingScenario);
					}
					LOG.info("METHOD: Escenarios moficados " + listBillingScenario.toString());
					return listBillingScenario;
			}
		}
		
	}

	@Override
	public void addBillingScenario(BillingScenario BillingScenario) {
		// TODO Auto-generated method stub

	}

}
