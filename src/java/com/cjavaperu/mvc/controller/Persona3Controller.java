package com.cjavaperu.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/rest3")
public class Persona3Controller {
	
	@Autowired
	PersonaService personaService;
	
	private HttpHeaders	headers;
	
	public Persona3Controller() {
		headers = new HttpHeaders();
		headers.add( "Content-Type", "application/json; charset=utf-8" );
	}

	@RequestMapping(value="personas", method = RequestMethod.GET)
	public ResponseEntity<Object> listar() {
		Respuesta respuesta = new Respuesta(true, personaService.listar());
		return new ResponseEntity<Object>( respuesta, headers, HttpStatus.OK );
	}
	
	@RequestMapping(value="persona", method = RequestMethod.POST)
	public ResponseEntity<Object> registrar(@Valid Persona persona, BindingResult result) {
		
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<>();
		
		if(result.hasErrors()){
			respuesta.setCategoria("validacion");
			for(FieldError fieldError : result.getFieldErrors()){
				respuestas.add( new Respuesta( fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage(), "validacion" ) );
			}
			respuesta.setRespuestas(respuestas);
			return new ResponseEntity<Object>( respuesta, headers, HttpStatus.BAD_REQUEST );
		}
		
		personaService.registrar(persona);
		if(persona.getId()!=null){
			respuesta.setExito(true);
			respuesta.setMensaje("Se registro correctamente");
		}
		
		return new ResponseEntity<Object>( respuesta, headers, HttpStatus.OK );
	}
	
	@RequestMapping(value="persona", method = RequestMethod.PUT)
	public ResponseEntity<Object> actualizar(@Valid Persona persona, BindingResult result) {
		
		Respuesta respuesta = new Respuesta();
		List<Respuesta> respuestas = new ArrayList<>();
		
		if(result.hasErrors()){
			respuesta.setCategoria("validacion");
			for(FieldError fieldError : result.getFieldErrors()){
				respuestas.add( new Respuesta( fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage(), "Validacion" ) );
			}
			respuesta.setRespuestas(respuestas);
			return new ResponseEntity<Object>( respuesta, headers, HttpStatus.BAD_REQUEST );
		}
		
		if(persona.getId() == null){
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("Falta indicar identificador de Persona");
			return new ResponseEntity<Object>( respuesta, headers, HttpStatus.BAD_REQUEST );
		}

		personaService.actualizar(persona);
		
		respuesta.setExito(true);
		respuesta.setMensaje("Se modifico correctamente");
		
		return new ResponseEntity<Object>( respuesta, headers, HttpStatus.OK );
	}
	
	@RequestMapping(value="persona/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> cargar(@PathVariable Long id) {
		
		Respuesta respuesta = new Respuesta();
		HttpStatus status = HttpStatus.OK;
		
		Persona persona = personaService.obtener(id);
		if(persona!=null){
			respuesta.setExito(true);
			respuesta.setPersona(persona);
		}else{
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("No existe persona");
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Object>( respuesta, headers, status );
	}

	@RequestMapping(value="persona/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> eliminar(@PathVariable Long id) {
		
		Respuesta respuesta = new Respuesta();
		HttpStatus status = HttpStatus.OK;
		
		Persona persona = personaService.obtener(id);
		if(persona!=null){
			respuesta.setExito(true);
			personaService.eliminar(persona);
			respuesta.setMensaje("Se elimino correctamente");
		}else{
			respuesta.setCategoria("integridad");
			respuesta.setMensaje("No existe persona");
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<Object>( respuesta, headers, status );
	}
 
}