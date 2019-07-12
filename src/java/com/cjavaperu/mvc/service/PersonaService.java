package com.cjavaperu.mvc.service;

import java.util.List;

import com.cjavaperu.mvc.entity.Persona;

public interface PersonaService {
	
	void registrar(Persona persona);

	void actualizar(Persona persona);
	
	Persona obtener(Long id);
	
	void eliminar(Persona persona);

	List<Persona> listar();

}
