package com.cjavaperu.mvc.bean;

import java.util.List;

import com.cjavaperu.mvc.entity.Persona;

public class Respuesta {

	private boolean exito;

	private String campo;
	
	private String mensaje;
	
	private String categoria;
	
	private Persona persona;
	
	private List<Persona> personas;
	
	private List<Respuesta> respuestas;
	
	public Respuesta() {
	}

	public Respuesta(boolean exito, List<Persona> personas) {
		this.exito = exito;
		this.personas = personas;
	}

	public Respuesta(boolean exito, Persona persona) {
		this.exito = exito;
		this.persona = persona;
	}

	public Respuesta(String campo, String mensaje, String categoria) {
		this.campo = campo;
		this.mensaje = mensaje;
		this.categoria = categoria;
	}

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}
	
	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

}
