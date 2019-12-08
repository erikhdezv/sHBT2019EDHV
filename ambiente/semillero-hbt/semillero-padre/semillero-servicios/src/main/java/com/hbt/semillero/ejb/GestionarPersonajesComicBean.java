/**
 * GestionarPersonajesComicBean.java
 */
package com.hbt.semillero.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Personaje;

/**
 * @Descripción: Clase que determina el bean para realizar las gestion de
 * los Personajes comics
 * 
 * @author ehernandez
 * @version 1.0
 */

/**
 * Se utiliza la anotación @Stateless,
 * 
 * Se utiliza la anotación @TransactionManagement, 
 * 
 * Se utiliza la anotación @SuppressWarnings temporalmente para evitar 
 * errores de paquetes importados no usados
 * 
 * @author ehernandez, erikdhv@gmail.com
 *
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajesComicBean implements IGestionarPersonajesComicLocal{

	@PersistenceContext
	private EntityManager entityManger;
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonajes(PersonajeDTO personajeDTO) {
		logger.debug("Inicia metodo crearPersonaje");
		
		Personaje personaje = convertirDTOEntidad(personajeDTO);
		entityManger.persist(personaje);
		
		logger.debug("Se ejecuta el comando");		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void actualizarPersonaje() {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic() {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void eliminarComic() {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes() {
		logger.debug("Inicia metodo consultarPersonaje");
		
		String query = "SELECT personaje"
				+"FROM Personaje personaje ";
		
		List<Personaje> listaPersonajes = entityManger.createQuery(query).getResultList();
		
		List<PersonajeDTO> listaPersonajeDTO = new ArrayList<PersonajeDTO>();
		
		for(Personaje personaje : listaPersonajes) {
			listaPersonajeDTO.add(convertirEntidadDTO(personaje));
		}
		logger.debug("Finaliza metodo consultarPersonaje");
		
		return listaPersonajeDTO;	
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes(Long idComic) {
		logger.debug("Inicia metodo consultarPersonaje");
		
		String query = "SELECT personaje"
				+ "FROM Personaje personaje "
				+ "WHERE personaje.comic.id = :idComic";
		
		List<Personaje> listaPersonajes = entityManger.createQuery(query).setParameter("idComic", idComic).getResultList();
		
		List<PersonajeDTO> listaPersonajeDTO = new ArrayList<PersonajeDTO>();
		
		for(Personaje personaje : listaPersonajes) {
			listaPersonajeDTO.add(convertirEntidadDTO(personaje));
		}
		logger.debug("Finaliza metodo consultarPersonaje");
		
		return listaPersonajeDTO;
	}
	
	private Personaje convertirDTOEntidad(PersonajeDTO personajeDTO){
		
		Personaje personaje = new Personaje();
		
		if (personajeDTO.getId() != null) {
			personaje.setId(personajeDTO.getId());
		}
		
		personaje.setId(personajeDTO.getId());//borrar
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEstado(personajeDTO.getEstado());
		personaje.getComic().setId(personajeDTO.getIdcomic());
		personaje.setSuperPoder(personajeDTO.getSuperPoder());
		return personaje;
		
	}
	
	private PersonajeDTO convertirEntidadDTO(Personaje personaje){
		
		PersonajeDTO personajeDTO = new PersonajeDTO();
		
		if (personaje.getId() != null) {
			personajeDTO.setId(personaje.getId());
		}
		
		personajeDTO.setId(personaje.getId());//borrar
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEstado(personaje.getEstado());
		personajeDTO.setIdComic(personaje.getComic().getId());
		personajeDTO.setSuperPoder(personaje.getSuperPoder());
		return personajeDTO;
		
	}

}
