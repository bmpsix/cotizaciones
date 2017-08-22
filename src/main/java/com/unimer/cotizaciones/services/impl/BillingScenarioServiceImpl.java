package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Assessment;
import com.unimer.cotizaciones.entities.BillingScenario;
import com.unimer.cotizaciones.entities.Country;
import com.unimer.cotizaciones.entities.LogBillingScenario;
import com.unimer.cotizaciones.entities.Proposal;
import com.unimer.cotizaciones.repositories.AssessmentJpaRepository;
import com.unimer.cotizaciones.repositories.BillingScenarioJpaRepository;
import com.unimer.cotizaciones.repositories.CountryJpaRepository;
import com.unimer.cotizaciones.repositories.LogBillingScenarioJpaRepository;
import com.unimer.cotizaciones.repositories.ProposalJpaRepository;
import com.unimer.cotizaciones.repositories.SettingsJpaRepository;
import com.unimer.cotizaciones.repositories.StatusJpaRepository;
import com.unimer.cotizaciones.services.BillingScenarioService;


@Service("billingScenarioServiceImpl")
public class BillingScenarioServiceImpl implements BillingScenarioService {

	@Autowired
	@Qualifier("billingScenarioJpaRepository")
	private BillingScenarioJpaRepository billingScenarioJpaRepository;
	
	@Autowired
	@Qualifier("logBillingScenarioJpaRepository")
	private LogBillingScenarioJpaRepository logBillingScenarioJpaRepository;
	
	@Autowired
	@Qualifier("countryJpaRepository")
	private CountryJpaRepository countryJpaRepository;
	
	@Autowired
	@Qualifier("settingsJpaRepository")
	private SettingsJpaRepository settingsJpaRepository;
	
	@Autowired
	@Qualifier("statusJpaRepository")
	private StatusJpaRepository statusJpaRepository;
	
	
	@Autowired
	@Qualifier("proposalJpaRepository")
	private ProposalJpaRepository proposalJpaRepository;
	
	@Autowired
	@Qualifier("assessmentJpaRepository")
	private AssessmentJpaRepository assessmentJpaRepository;
	
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
						
						
				boolean project = false;		
				boolean modified = false;
				for(BillingScenario billingS : listBillingScenario)
				{
					if(billingS.getCountry()==billingScenario.getCountry()) modified=true;
					if(billingS.getCountry()==billingScenario.getCountry() && billingS.getMethodState()==1)project=true;
					if(project==true || modified==true) break;
				}
				if(project==false && modified==false)listBillingScenario.add(billingScenario);
			}
			LOG.info("METHOD: Escenarios moficados " + listBillingScenario.toString());
			return listBillingScenario;
			
		}
		
	}

	@Override
	public void editBillingScenario(BillingScenario billingScenario) {
		
		
		
		
		if(billingScenario.getIdBillingScenario()==0)
		{
			
			billingScenarioJpaRepository.save(billingScenario);
			java.util.Date date = new Date();
			LogBillingScenario logBillingScenario = new LogBillingScenario(date, "BillingScenario Created",  billingScenario.getUser().getIdUser(),  billingScenario.getIdBillingScenario() ,  billingScenario.getProposal().getIdProposal(),
					billingScenario.getInitialDate(),  billingScenario.getRegistrationDate(),  billingScenario.getUser().getIdUser(),  billingScenario.getCountry().getIdCountry(),  billingScenario.getTranferenceValue(),  billingScenario.getRemittance(),
					billingScenario.getIva(),  billingScenario.getTotalAmount(),  billingScenario.getTranferenceValueModified(),  billingScenario.getRemittanceModified(),
					billingScenario.getIvaModified(),  billingScenario.getTotalAmountModified(), billingScenario.getLastModificationDate(), billingScenario.getMethodState(),  billingScenario.getSaClient().getIdSaClient(),
					billingScenario.getClientContact().getIdClientContact());
			
			logBillingScenarioJpaRepository.save(logBillingScenario);
		}
		else
		{
			java.util.Date date = new Date();
			BillingScenario billingScenarioToUpdate = billingScenarioJpaRepository.findByIdBillingScenario(billingScenario.getIdBillingScenario());
			
			LogBillingScenario logBillingScenario = new LogBillingScenario(date, "BillingScenario Modified",  billingScenarioToUpdate.getUser().getIdUser(),  billingScenarioToUpdate.getIdBillingScenario() ,  billingScenarioToUpdate.getProposal().getIdProposal(),
					billingScenarioToUpdate.getInitialDate(),  billingScenarioToUpdate.getRegistrationDate(),  billingScenarioToUpdate.getUser().getIdUser(),  billingScenarioToUpdate.getCountry().getIdCountry(),  billingScenarioToUpdate.getTranferenceValue(),  billingScenarioToUpdate.getRemittance(),
					billingScenarioToUpdate.getIva(),  billingScenarioToUpdate.getTotalAmount(),  billingScenarioToUpdate.getTranferenceValueModified(),  billingScenarioToUpdate.getRemittanceModified(),
					billingScenarioToUpdate.getIvaModified(),  billingScenarioToUpdate.getTotalAmountModified(), date, billingScenarioToUpdate.getMethodState(),  billingScenarioToUpdate.getSaClient().getIdSaClient(),
					billingScenarioToUpdate.getClientContact().getIdClientContact());
			logBillingScenarioJpaRepository.save(logBillingScenario);
			billingScenarioJpaRepository.save(billingScenario);
			
		}
		

	}

	@Override
	public BillingScenario findById(int idBillingScenario) {
		return billingScenarioJpaRepository.findByIdBillingScenario(idBillingScenario);
	}

	@Override
	public void addBillingScenario(BillingScenario billingScenario) {
		
		List<BillingScenario> listBillingScenario = billingScenarioJpaRepository.findByProposal(billingScenario.getProposal());
		if(listBillingScenario!=null)
		{
			for(BillingScenario bs : listBillingScenario)
			{
				if(bs.getMethodState()==1) 
				{
					
					
					java.util.Date date = new Date();
					LogBillingScenario logBillingScenario = new LogBillingScenario(date, "BillingScenario Unselected",  bs.getUser().getIdUser(),  bs.getIdBillingScenario() ,  bs.getProposal().getIdProposal(),
							bs.getInitialDate(),  bs.getRegistrationDate(),  bs.getUser().getIdUser(),  bs.getCountry().getIdCountry(),  bs.getTranferenceValue(),  bs.getRemittance(),
							bs.getIva(),  bs.getTotalAmount(),  bs.getTranferenceValueModified(),  bs.getRemittanceModified(),
							bs.getIvaModified(),  bs.getTotalAmountModified(), bs.getLastModificationDate(), bs.getMethodState(),  bs.getSaClient().getIdSaClient(),
							bs.getClientContact().getIdClientContact());
					logBillingScenarioJpaRepository.save(logBillingScenario);
					
					bs.setMethodState((byte)0);
					if(bs.getTotalAmountModified()!=0)billingScenarioJpaRepository.save(bs);
					else billingScenarioJpaRepository.delete(bs);
					
					
				}
			}
		
		}
		
			billingScenarioJpaRepository.save(billingScenario);
			java.util.Date date = new Date();
			LogBillingScenario logBillingScenario = new LogBillingScenario(date, "BillingScenario Selected",  billingScenario.getUser().getIdUser(),  billingScenario.getIdBillingScenario() ,  billingScenario.getProposal().getIdProposal(),
					billingScenario.getInitialDate(),  billingScenario.getRegistrationDate(),  billingScenario.getUser().getIdUser(),  billingScenario.getCountry().getIdCountry(),  billingScenario.getTranferenceValue(),  billingScenario.getRemittance(),
					billingScenario.getIva(),  billingScenario.getTotalAmount(),  billingScenario.getTranferenceValueModified(),  billingScenario.getRemittanceModified(),
					billingScenario.getIvaModified(),  billingScenario.getTotalAmountModified(), billingScenario.getLastModificationDate(), billingScenario.getMethodState(),  billingScenario.getSaClient().getIdSaClient(),
					billingScenario.getClientContact().getIdClientContact());
			
			logBillingScenarioJpaRepository.save(logBillingScenario);
			
			if(statusJpaRepository.findByDetail("Aprobado")!=null)
			{
				Proposal proposal = proposalJpaRepository.findByIdProposal(billingScenario.getProposal().getIdProposal());
				proposal.setStatus(statusJpaRepository.findByDetail("Aprobado"));
				proposalJpaRepository.save(proposal);
				Assessment assessment = assessmentJpaRepository.findByIdAssessment(proposal.getAssessment().getIdAssessment());
				assessment.setStatus(statusJpaRepository.findByDetail("Aprobado"));
				assessmentJpaRepository.save(assessment);
			}
		
	}

	@Override
	public void deleteByChangeInTheTotalAmount(Proposal proposal) {
		
		List<BillingScenario> listBillingScenario = billingScenarioJpaRepository.findByProposal(proposal);
		
		if(!listBillingScenario.isEmpty())
		{
			boolean project=false;
			for(BillingScenario billingScenario : listBillingScenario)
			{
				
				java.util.Date date = new Date();
				LogBillingScenario logBillingScenario = new LogBillingScenario();
				logBillingScenario.setActionDetail("BillingScenario - Delete by change in the total amount");
				logBillingScenario.setActionUser(billingScenario.getUser().getIdUser());
				logBillingScenario.setClientContact(billingScenario.getClientContact().getIdClientContact());
				logBillingScenario.setCountry(billingScenario.getCountry().getIdCountry());
				logBillingScenario.setDateRecord(date);
				logBillingScenario.setIdBillingScenario(billingScenario.getIdBillingScenario() );
				logBillingScenario.setInitialDate(billingScenario.getProposal().getInitialDate());
				logBillingScenario.setIva(billingScenario.getIva());
				logBillingScenario.setIvaModified(billingScenario.getIvaModified());
				logBillingScenario.setLastModificationDate(billingScenario.getLastModificationDate());
				logBillingScenario.setMethodState( billingScenario.getMethodState());
				logBillingScenario.setProposal(billingScenario.getProposal().getIdProposal());
				logBillingScenario.setRegistrationDate(billingScenario.getRegistrationDate());
				logBillingScenario.setRemittance(billingScenario.getRemittance());
				logBillingScenario.setRemittanceModified(billingScenario.getRemittanceModified());
				logBillingScenario.setSaClient(billingScenario.getSaClient().getIdSaClient());
				logBillingScenario.setTotalAmount(billingScenario.getTotalAmount());
				logBillingScenario.setTotalAmountModified(billingScenario.getTotalAmountModified());
				logBillingScenario.setTranferenceValue( billingScenario.getTranferenceValue());
				logBillingScenario.setTranferenceValueModified(billingScenario.getTranferenceValueModified());
				logBillingScenario.setUser(billingScenario.getUser().getIdUser());
				
				LOG.info("METHOD: deleteByChangeInTheTotalAmount   logBillingScenario" + logBillingScenario.toString());
				logBillingScenarioJpaRepository.save(logBillingScenario);
				
				if(billingScenario.getMethodState()==1)project=true;
				
				billingScenarioJpaRepository.delete(billingScenario);
			}
			if(project && statusJpaRepository.findByDetail("Creado")!=null)
			{
				proposal.setStatus(statusJpaRepository.findByDetail("Creado"));
				proposalJpaRepository.save(proposal);
				Assessment assessment = assessmentJpaRepository.findByIdAssessment(proposal.getAssessment().getIdAssessment());
				assessment.setStatus(statusJpaRepository.findByDetail("Creado"));
				assessmentJpaRepository.save(assessment);
			}
		}
		
	}

}
