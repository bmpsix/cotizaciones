package com.unimer.cotizaciones.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unimer.cotizaciones.entities.Technique;

@Repository("TechniqueJpaRepository")
public interface TechniqueJpaRepository extends JpaRepository<Technique, Serializable>{

	
	
	@Query("select t from Technique t order by t.detail")
	public abstract List<Technique> orderListAll();
	
	public abstract Technique findByDetail(String detail);
	
	
	public abstract Technique findByIdTechnique(int idTechnique);
	
}
