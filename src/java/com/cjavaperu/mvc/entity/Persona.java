package com.cjavaperu.mvc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "persona")
@XmlRootElement
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String apellidos;

	@NotNull(message = "no puede estar vacio")
	@Min(1)
	@Max(100)
	private Integer edad;

	@NotEmpty
	private String nombres;

	public Persona() {
	}

	public Persona(String nombres, String apellidos, Integer edad) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
	}
	
	public Persona(Long id, String nombres, String apellidos, Integer edad) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Override
	public String toString() {
		return "Persona [nombres=" + nombres + ", apellidos=" + apellidos
				+ ", edad=" + edad + ", id=" + id + "]";
	}

}