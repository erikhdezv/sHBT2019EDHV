package com.hbt.semillero.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "PERSONAJE")
public class Personaje implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAJE_ID_GENERATOR", sequenceName = "SEC_PERSONAJE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAJE_ID_GENERATOR")
	@Column(name = "PERS_ID")
	private Long id;
	
	@Column(name = "PERS_NOMBRE")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERS_ID_COMIC", nullable = false)
	private Comic comic;
	
	@Column(name = "PERS_ESTADO")
	private String estado;
	
	@Column(name = "PERS_SUPERPODER")
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

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
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
}
