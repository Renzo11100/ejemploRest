package com.cjavaperu.mvc.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name="usuario" )
@XmlAccessorType( XmlAccessType.NONE )
public class Usuario {
	
	@XmlElement( name="id" )
	private Long id;
	
	@XmlElement( name="usuario" )
	private String usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
