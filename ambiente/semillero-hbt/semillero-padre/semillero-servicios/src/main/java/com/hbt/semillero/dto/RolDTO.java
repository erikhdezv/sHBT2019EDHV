/**
 * 
 */
package com.hbt.semillero.dto;

import java.io.Serializable;

/**
 * <b>Descripción:<b> Clase que determina el dto a usar para modificar,
 * consultar y posteriormente eliminar un rol
 * @author ehernandez
 * @version 1.0
 *
 */
public class RolDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * Identificador de Rol del Personaje
	 */
	private Long id;
	
	/*
	 * Nombre del Rol del Personaje del comic
	 */
	private String nombre;
	
	/*
	 * Estado para el Rol del personaje comic, Activo o inactivo
	 */
	private String estado;
	
	/**
	 * Metodo get que retorna el identificador del personaje del comic
	 * @return the nombre
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Metodo que asigna al atributo de la tabla el id al rol del personaje del comic
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Metodo que retorna el nombre del rol del personaje del comic
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo que asigna el nombre del rol al atributo de la clase
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo que retorna el estado del rol del personaje del comic
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Metodo que asigna el estado al rol del personaje del comic
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo RolDTO.
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static RolDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, RolDTO.class);
	}

	/**
	 * Método encargado de convertir los datos recibidos en RolDTO al JSON
	 * esperado
	 * 
	 * @param dto DTO
	 * 
	 * @return Json
	 */
	@Override
	public String toString() {
		return JsonUtils.toStringJson(this);
	}
	
}
