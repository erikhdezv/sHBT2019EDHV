/**
 * 
 */
package com.hbt.semillero.dto;

import java.time.LocalDateTime;

/**
 * @author ehernandez
 *
 */
public class PersonaDTO {

	private Long id;
	private String nombre;
	private String tipid;
	private Long numid;
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
