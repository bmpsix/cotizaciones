package com.unimer.cotizaciones.services.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unimer.cotizaciones.entities.Consecutive;
import com.unimer.cotizaciones.entities.LogRol;
import com.unimer.cotizaciones.entities.Rol;
import com.unimer.cotizaciones.repositories.ConsecutivesJpaRepository;
import com.unimer.cotizaciones.repositories.LogRolJpaRepository;
import com.unimer.cotizaciones.repositories.RolJpaRepository;
import com.unimer.cotizaciones.services.RolService;

@Service("rolServiceImpl")
public class RolServiceImpl implements RolService {

	@Autowired
	@Qualifier("rolJpaRepository")
	private RolJpaRepository rolJpaRepository;

	@Autowired
	@Qualifier("consecutivesJpaRepository")
	private ConsecutivesJpaRepository consecutivesJpaRepository;

	@Autowired
	@Qualifier("logRolJpaRepository")
	private LogRolJpaRepository logRolJpaRepository;

	private static final Log LOG = LogFactory.getLog(RolServiceImpl.class);

	@Override
	public void addRol(Rol rol) {

		Consecutive consecutive = consecutivesJpaRepository.findByType("Role");

		if (consecutive != null) {
			rol.setIdRol(consecutive.getPrefix() + "-" + consecutive.getSubfix());

			if (!rol.getIdRol().equals(rolJpaRepository.findOne(rol.getIdRol()))
					&& rolJpaRepository.findByDetail(rol.getDetail()) == null) {

				rolJpaRepository.save(rol);
				consecutive.setSubfix(consecutive.getSubfix() + 1);
				consecutivesJpaRepository.save(consecutive);
			} else if (rolJpaRepository.findByIdRol(rol.getIdRol()) != null) {
				Rol roleToUpdate = rolJpaRepository.findByIdRol(rol.getIdRol());
				java.util.Date date = new Date();
				LogRol lrol = new LogRol(date, "Role modified", "test", roleToUpdate.getDetail(),
						roleToUpdate.getIdRol(), roleToUpdate.getStatus());
				roleToUpdate.setStatus(rol.getStatus());
				rolJpaRepository.save(roleToUpdate);
				logRolJpaRepository.save(lrol);

			}
		} else {

			Consecutive ConsecutiveRolDefault = new Consecutive();

			ConsecutiveRolDefault.setType("Role");
			ConsecutiveRolDefault.setPrefix("ROL");
			ConsecutiveRolDefault.setSubfix(1);
			ConsecutiveRolDefault.setDetail("Consecutivo por defecto para el manejo de los roles");
			consecutivesJpaRepository.save(ConsecutiveRolDefault);

			rol.setIdRol(ConsecutiveRolDefault.getPrefix() + "-" + ConsecutiveRolDefault.getSubfix());

			if (!rol.getIdRol().equals(rolJpaRepository.findOne(rol.getIdRol()))
					&& rolJpaRepository.findByDetail(rol.getDetail()) == null) {
				rolJpaRepository.save(rol);
				ConsecutiveRolDefault.setSubfix(ConsecutiveRolDefault.getSubfix() + 1);
				consecutivesJpaRepository.save(ConsecutiveRolDefault);
			} else {
				LOG.info("METHOD: addRol in RolServiceImpl -- The role id already exists ");
				return;
			}
		}
	}

	@Override
	public List<Rol> listAllRol() {
		// List<Rol> roles = rolJpaRepository.findRolesByStatus((byte)1);
		List<Rol> roles = rolJpaRepository.findAll();
		return roles;
	}

	@Override
	public void changeStatusById(String idRol, byte status) {

		Rol rol = rolJpaRepository.findOne(idRol);

		if (rol != null) {
			rol.setStatus(status);
			rolJpaRepository.save(rol);
		}

	}

	@Override
	public Rol findById(String idRol) {

		return rolJpaRepository.findByIdRol(idRol);
	}

}
