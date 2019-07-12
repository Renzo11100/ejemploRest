package com.cjavaperu.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjavaperu.mvc.bean.Respuesta;
import com.cjavaperu.mvc.entity.Persona;
import com.cjavaperu.mvc.service.PersonaService;

@RestController
@RequestMapping("rest2")
public class Persona2Controller {
	
	@Autowired
	PersonaService personaService;

	@RequestMapping(value="personas", method = RequestMethod.GET)
	public Respuesta listar() {
		Respuesta respuesta = new Respuesta(true, personaService.listar());
		return respuesta;
	}
	
	@RequestMapping(value="persona", method = RequestMethod.POST)
	public Respuesta registrar(@Valid Persona persona, BindingResult result) {
		
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<>();
		if(result.hasErrors()){
			respuesta.setCategoria("validacion");
			for(FieldError fieldError : result.getFieldErrors()){
				respuestas.add( new Respuesta( fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage(), "validacion" ) );
			}
			respuesta.setRespuestas(respuestas);
			return respuesta;
		}
		
		personaService.registrar(persona);
		if(persona.getId()!=null){
			respuesta.setExito(true);
			respuesta.setMensaje("Se registro correctamente");
		}
		
		return respuesta;
	}
	
	@RequestMapping(value="persona", method = RequestMethod.PUT)
	public Respuesta actualizar(@Valid Persona persona, BindingResult result) {
		
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<>();
		if(result.hasErrors()){
			respuesta.setCategoria("validacion");
			for(FieldError fieldError : result.getFieldErrors()){
				respuestas.add( new Respuesta( fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage(), "validacion" ) );
			}
			respuesta.setRespuestas(respuestas);
			return respuesta;
		}
		
		if(persona.getId() == null){
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("Falta indicar identificador de Persona");
			return respuesta;
		}

		personaService.actualizar(persona);
		
		respuesta.setExito(true);
		respuesta.setMensaje("Se modifico correctamente");
		
		return respuesta;
	}
	
	@RequestMapping(value="persona/{id}", method = RequestMethod.GET)
	public Respuesta cargar(@PathVariable Long id) {
		
		Respuesta respuesta = new Respuesta();
		
		Persona persona = personaService.obtener(id);
		if(persona!=null){
			respuesta.setExito(true);
			respuesta.setPersona(persona);
		}else{
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("No existe persona");
		}
		
		return respuesta;
	}

	@RequestMapping(value="persona/{id}", method = RequestMethod.DELETE)
	public Respuesta eliminar(@PathVariable Long id) {
		
		Respuesta respuesta = new Respuesta();
		
		Persona persona = personaService.obtener(id);
		if(persona!=null){
			respuesta.setExito(true);
			personaService.eliminar(persona);
			respuesta.setMensaje("Se elimino correctamente");
		}else{
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("No existe persona");
		}

		return respuesta;
	}
 
}