/**
 * 
 */
package com.hbt.semillero.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

/**
 * @Descripción: Clase que gestiona la informaciòn de los roles para los personajes de los comic.
 * Clase que determina la entidad que permite representar la tabla "DB_SEMILLERO"."ROL"
 * @author ehernandez, erikdhv@gmail.com
 * @version 1.0 
 *
 */
@Entity
@Table(name = "ROL")
@Check(constraints = "ROL_ESTADO IN ('ACTIVO', 'INACTIVO')")
public class Rol implements Serializable{

	/**
	 * Serializar es pasar un Objeto a un array de bytes y viceversa. Atributo que
	 * determina serialVersionUID es el id único que identifica una clase cuando lo
	 * serializamos. ;ediante este id podemos identificar el objeto convertido en un
	 * array de bytes.
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize = 1, name = "ROL_ID_GENERATOR", sequenceName = "SEC_ROL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_ID_GENERATOR")
	@Column(name = "ROL_ID")
	private Long id;
	
	/*
	 * Almacena el nombre para el rol del personaje
	 * Asiganamos restriccion de unico y no nulo para la columna ROL_NOMBRE
	 */
	@Column(name = "ROL_NOMBRE", unique=true, nullable = false)
	private String nombre;
	
	/**
	 * Almacena el estado para el rol del personaje
	 * Se asigna restricciòn de no nulo debido a que los valores posibles son ACTIVO, INACTIVO
	 */
	@Column(name = "ROL_ESTADO",  nullable = false)
	private String estado;

	/**
	 * Metodo queretorna el id para el rol del personaje
	 * @return Long, id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Metodo que almacena en el atributo Id el identificador para el rol
	 * @param id de tipo Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metodo que retorna el nombre para el rol del personaje
	 * @return nombre de tipo string
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método asigna el nombre del rol al atributo nombre de la clase rol 
	 * @param nombre, String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo retorna el estado del rol
	 * @return estado de tipo string
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Metodo asigna al atributo estado para la clase rol
	 * @param estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
