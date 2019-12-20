package com.hbt.semillero.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b>Descripción:<b> Clase que determina la entidad que permite representar la
 * tabla "DB_SEMILLERO"."PERSONA"
 * 
 * @author ehernandez
 * @version
 */
@Entity
@Table(name = "PERSONA")
public class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONA_ID_GENERATOR", sequenceName = "SEC_PERSONA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_ID_GENERATOR")
	@Column(name = "PER_ID")
	private Long id;
	
	/*
	 * Almacena el nombre para ela persona
	 * Asiganamos restriccion de unico y no nulo para la columna PER_NOMBRE
	 */
	@Column(name = "PER_NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "PER_TIPID", nullable = false)
	private String tipid;
	
	@Column(name = "PER_NUMID", unique=true, nullable = false)
	private Long numid;
	
	@Column(name = "PER_FECNAC", nullable = false)
	private LocalDateTime fecnac;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tipid
	 */
	public String getTipid() {
		return tipid;
	}

	/**
	 * @param tipid the tipid to set
	 */
	public void setTipid(String tipid) {
		this.tipid = tipid;
	}

	/**
	 * @return the numid
	 */
	public Long getNumid() {
		return numid;
	}

	/**
	 * @param numid the numid to set
	 */
	public void setNumid(Long numid) {
		this.numid = numid;
	}

	/**
	 * @return the fecnac
	 */
	public LocalDateTime getFecnac() {
		return fecnac;
	}

	/**
	 * @param fecnac the fecnac to set
	 */
	public void setFecnac(LocalDateTime fecnac) {
		this.fecnac = fecnac;
	}
	
}
