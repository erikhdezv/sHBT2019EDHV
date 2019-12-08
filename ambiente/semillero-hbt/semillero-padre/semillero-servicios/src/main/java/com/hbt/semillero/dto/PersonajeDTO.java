package com.hbt.semillero.dto;

import java.io.Serializable;

import com.hbt.semillero.entidad.EstadoEnum;

public class PersonajeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private Long idcomic;
	private String estado;
	private String superPoder;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdcomic() {
		return idcomic;
	}
	public void setIdComic(Long idcomic) {
		this.idcomic = idcomic;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getSuperPoder() {
		return superPoder;
	}
	public void setSuperPoder(String superPoder) {
		this.superPoder = superPoder;
	}
	
	/**
	 * Método encargado de convertir los datos recibidos en JSON al tipo ComicDTO.
	 * <b>Caso de Uso:</b>
	 * 
	 * @param arg Cadena que representa el objeto complejo JSON.
	 * @return Instancia con los datos recibidos.
	 */
	public static PersonajeDTO valueOf(String arg) {
		return JsonUtils.valueOf(arg, PersonajeDTO.class);
	}

	/**
	 * Método encargado de convertir los datos recibidos en ComicDTO al JSON
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
