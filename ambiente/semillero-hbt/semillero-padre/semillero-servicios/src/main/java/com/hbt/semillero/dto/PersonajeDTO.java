package com.hbt.semillero.dto;

import java.io.Serializable;

/**
 * <b>Descripción:<b> Clase que determina el dto a usar para modificar,
 * consultar y posteriormente eliminar un Personaje de un comic
 * 
 * @author ehernandez
 * @version 1.0
 */
public class PersonajeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * Identificador de Personaje
	 */
	private Long id;
	
	/*
	 * Nombre del Personaje del comic
	 */
	private String nombre;
	
	/*
	 * Identificador del comic, clave foranea a la tabla comic
	 */
	private Long idcomic;
	
	/*
	 * Identificador del rol, clave foranea a la tabla rol
	 */
	private Long idrol;
	
	/*
	 * Estado para el personaje comic, Activo o inactivo
	 */
	private String estado;
	
	/*
	 * Superpoder asignado al personaje del comic
	 */
	private String superPoder;
	
	/**
	 * Metodo get que retorna el identificador del personaje del comic
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/*
	 * Metodo que asigna al atributo de la tabla el id al personaje del comic
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Metodo que retorna el nombre del personaje de comic
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/*
	 * Metodo que asigna el nombre al atributo de la clase para el personaje del comic
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo que retorna el id del comic que tiene asignado el registro del personaje del comic
	 * @return the id comic
	 */
	public Long getIdcomic() {
		return idcomic;
	}
	
	/*
	 * Metodo que asigna al atributo de la clase el id del comic al cual pertenece el personaje
	 */
	public void setIdComic(Long idcomic) {
		this.idcomic = idcomic;
	}
	
	/**
	 * Metodo que retorna el estado del objeto personaje del comic, Activo o Inactivo
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/*
	 * Metodo que asigna al atributo de la clase el estado del personaje del comic
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Metodo que retorna el super poder del personaje del comic
	 * @return the superpoder
	 */
	public String getSuperPoder() {
		return superPoder;
	}

	/**
	 * Metodo que retorna el id del rol que tiene asignado el registro del personaje del comic
	 * @return the idrol
	 */
	public Long getIdrol() {
		return idrol;
	}

	/**
	 * Metodo que asigna al atributo de la clase el id del rol al cual pertenece el personaje
	 * @param idrol the idrol to set
	 */
	public void setIdrol(Long idrol) {
		this.idrol = idrol;
	}
	
	/*
	 * Metodo que asigna al atributo de la clase el super poder del personaje del comic 
	 */
	public void setSuperPoder(String superPoder) {
		this.superPoder = superPoder;
	}
	
	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo PersonajeDTO.
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static PersonajeDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, PersonajeDTO.class);
	}

	/**
	 * Método encargado de convertir los datos recibidos en PersonajeDTO al JSON
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
