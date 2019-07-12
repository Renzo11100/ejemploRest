package com.cjavaperu.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjavaperu.mvc.dao.PersonaDao;
import com.cjavaperu.mvc.entity.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private PersonaDao personaDao;

	@Override
	public void registrar(Persona persona) {
		personaDao.registrar(persona);
	}

	@Override
	public void actualizar(Persona persona) {
		personaDao.actualizar(persona);
	}

	@Override
	public Persona obtener(Long id) {
		return personaDao.obtener(id);
	}

	@Override
	public void eliminar(Persona persona) {
		personaDao.eliminar(persona);
	}

	@Override
	public List<Persona> listar() {
		return personaDao.listar();
	}

}
