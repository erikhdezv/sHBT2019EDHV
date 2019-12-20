/**
 * 
 */
package com.hbt.semillero.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.hbt.semillero.entidad.Persona;

/**
 * @author ehernandez
 *
 */
public class VentaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Persona idpersona;
	private LocalDateTime fecventa;
	
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
	 * @return the idpersona
	 */
	public Persona getIdpersona() {
		return idpersona;
	}
	/**
	 * @param idpersona the idpersona to set
	 */
	public void setIdpersona(Persona idpersona) {
		this.idpersona = idpersona;
	}
	/**
	 * @return the fecventa
	 */
	public LocalDateTime getFecventa() {
		return fecventa;
	}
	/**
	 * @param fecventa the fecventa to set
	 */
	public void setFecventa(LocalDateTime fecventa) {
		this.fecventa = fecventa;
	}
	
	
}
