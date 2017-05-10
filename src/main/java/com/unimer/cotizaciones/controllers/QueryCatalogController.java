package com.unimer.cotizaciones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.unimer.cotizaciones.entities.ProposalType;
import com.unimer.cotizaciones.entities.QueryCatalog;
import com.unimer.cotizaciones.services.CollectMethodService;
import com.unimer.cotizaciones.services.CountryService;
import com.unimer.cotizaciones.services.IndustrySectorService;
import com.unimer.cotizaciones.services.ProposalTypeService;
import com.unimer.cotizaciones.services.QueryCatalogService;
import com.unimer.cotizaciones.services.StudyCategoryService;
import com.unimer.cotizaciones.services.StudyTypeService;

@RestController
public class QueryCatalogController {

		@Autowired
		@Qualifier("queryCatalogServiceImpl")
		private QueryCatalogService queryCatalogService;
		
		@Autowired
		@Qualifier("countryServiceImpl")
		private CountryService countryService;
		
		@Autowired
		@Qualifier("proposalTypeServiceImpl")
		private ProposalTypeService proposalTypeService;
		
		@Autowired
		@Qualifier("collectMethodServiceImpl")
		private CollectMethodService collectMethodService;
		
		@Autowired
		@Qualifier("industrySectorServiceImpl")
		private IndustrySectorService industrySectorService;
		
		@Autowired
		@Qualifier("StudyTypeServiceImpl")
		private StudyTypeService studyTypeService;
		
		@Autowired
		@Qualifier("studyCategoryImpl")
		private StudyCategoryService studyCategoryService;
		
		
		@GetMapping("/admin/proposalcount")
		public @ResponseBody long performCount()
		{
			return proposalTypeService.rowCount();
		}
		
		@GetMapping("/admin/studycategorycount")
		public @ResponseBody long performStudyCategoryCount()
		{
			return studyCategoryService.rowCount();
		}
		@GetMapping("/admin/industysectorcount")
		public @ResponseBody long performIndustrySectorCount()
		{
			return industrySectorService.rowCount();
		}
		
		@GetMapping("/admin/addQueryCatalog")
		public @ResponseBody long addQueryCatalog(@RequestParam("id") int id,@RequestParam("detail") String detail,@RequestParam("count") long count,@RequestParam("detail2") String detail2,@RequestParam("detail3") int detail3)
		{
			QueryCatalog queryCatalog2 =  queryCatalogService.findQuery(detail);
			QueryCatalog queryCatalog = new QueryCatalog( id,detail, count, detail2, detail3);
			
			long oldValue =  queryCatalog2.getRow_count();
			queryCatalogService.addQuery(queryCatalog);
			return oldValue;
			
		}
		@GetMapping("/admin/getProposalTypes")
		public List<ProposalType> getProposals()
		{
			return proposalTypeService.listAllProposalTypes();
		}
		
		@GetMapping("/admin/getquerycatalogs")
		public @ResponseBody List<QueryCatalog> getQueryCatalog()
		{
			return queryCatalogService.ListQueryCatalog();
		}
		
			
		
		
}
