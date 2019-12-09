package com.hbt.semillero.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <b>Descripción:<b> Clase que determina la entidad que permite representar la
 * tabla "DB_SEMILLERO"."PERSONAJE"
 * 
 * @author ccastano
 * @version
 */
@Entity
@Table(name = "PERSONAJE")
public class Personaje implements Serializable{

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize = 1, name = "PERSONAJE_ID_GENERATOR", sequenceName = "SEC_PERSONAJE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAJE_ID_GENERATOR")
	@Column(name = "PERS_ID")
	private Long id;
	
	/*
	 * Almacena el nombre para el personaje del comic
	 * Asiganamos restriccion de unico y no nulo para la columna PERS_NOMBRE
	 */
	@Column(name = "PERS_NOMBRE", unique=true, nullable = false)
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERS_ID_COMIC", nullable = false)
	private Comic comic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERS_IDROL", nullable = false)
	private Rol rol;
	
	@Column(name = "PERS_ESTADO")
	private String estado;
	
	@Column(name = "PERS_SUPERPODER")
	private String superPoder;
	
	/**
	 * Metodo que retorna en el atributo Id para el personaje del comic
	 * @param id, Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo encargado de retornar el valor del atributo id
	 * @param id, Long
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo que almacena en el atributo Id el identificador para el personaje del comic
	 * @param id de tipo Long
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método asigna el nombre del personaje al atributo nombre de la clase rol 
	 * @param nombre, String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo retorna el id del comic asociado al personaje
	 * @return estado de tipo string
	 */
	public Comic getComic() {
		return comic;
	}
	
	/**
	 * Metodo asigna al atributo el id del comic a asociar en la clase
	 * @param estado
	 */
	public void setComic(Comic comic) {
		this.comic = comic;
	}

	/**
	 * Metodo retorna el estado para el personje del comic
	 * @return estado de tipo string
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Metodo asigna el estado del personje del comic
	 * @param estado, String
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Metodo retorna el Super Poder para el personje del comic
	 * @return superpoder de tipo string
	 */
	public String getSuperPoder() {
		return superPoder;
	}

	/**
	 * Metodo asigna el Super Poder para el personje del comic
	 * @param superpoder de tipo string
	 */
	public void setSuperPoder(String superPoder) {
		this.superPoder = superPoder;
	}
	
	/**
	 * @description Metodo que retornar el valor del atributo rol
	 * 
	 * @return El rol asociado a la clase
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @description Metodo que retornar el valor del atributo rol
	 * 
	 * @return El rol asociado a la clase
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
