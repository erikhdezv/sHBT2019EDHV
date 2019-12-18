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

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Personaje;
import com.hbt.semillero.entidad.Rol;

/**
 * @Descripción: Clase que determina el bean para realizar las gestion de
 * los Personajes comics
 * 
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

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManger;
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#crearPersonajes(com.hbt.semillero.dto.PersonajeDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersonajes(PersonajeDTO personajeDTO) {
		logger.debug("Inicia metodo crearPersonaje");
		
		Personaje personaje = convertirDTOEntidad(personajeDTO);
		entityManger.persist(personaje);
		
		logger.debug("Se ejecuta el comando");		
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#actualizarPersonaje()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void actualizarPersonaje() {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#modificarComic()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, PersonajeDTO personajeNuevo) {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		Personaje personajeModificar;
		if (personajeNuevo == null) {
			// Entidad a modificar
			personajeModificar = entityManger.find(Personaje.class, id);
		}else {
			personajeModificar = convertirDTOEntidad(personajeNuevo);
		}
		
		personajeModificar.setNombre(nombre);
		entityManger.merge(personajeModificar);
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#eliminarComic()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void eliminarComic() {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#consultarPersonajes()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes() {
		logger.debug("Inicia metodo consultarPersonaje");
		
		String query = "SELECT personaje "
				+"FROM Personaje personaje ";
		
		List<PersonajeDTO> listaPersonajeDTO = new ArrayList<PersonajeDTO>();
		@SuppressWarnings("unchecked")
		List<Personaje> listaPersonajes = entityManger.createQuery(query).getResultList();
		for(Personaje personajes : listaPersonajes) {
			listaPersonajeDTO.add(convertirEntidadDTO(personajes));
		}
		logger.debug("Finaliza metodo consultarPersonaje");
		
		return listaPersonajeDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#consultarPersonajes(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PersonajeDTO consultarPersonajes(String idComic) {
		String query = "SELECT personaje "
				+ "FROM Personaje personaje "
				+ "WHERE personaje.comic.id = :idComic";
		
		@SuppressWarnings("unchecked")
		List<Personaje> listaPersonajes = entityManger.createQuery(query).setParameter("idComic", Long.parseLong(idComic)).getResultList();
		
		//List<PersonajeDTO> listaPersonajeDTO = new ArrayList<PersonajeDTO>();
		PersonajeDTO listaPersonajeDTO = null;
		
		for(Personaje personaje : listaPersonajes) {
			listaPersonajeDTO = convertirEntidadDTO(personaje);
		}
		logger.debug("Finaliza metodo consultarPersonaje");
		
		return listaPersonajeDTO;
	}
	
	/*
	 * Convierte un Dto a la entidad para el personaje del comic
	 */
	private Personaje convertirDTOEntidad(PersonajeDTO personajeDTO){
		
		Personaje personaje = new Personaje();	
		
		if(personajeDTO.getId()!=null) {
			personaje.setId(personajeDTO.getId());
		}

		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEstado(personajeDTO.getEstado());
		
		personaje.setComic(new Comic());
		personaje.getComic().setId(personajeDTO.getIdcomic());
		
		personaje.setRol(new Rol());
		personaje.getRol().setId(personajeDTO.getIdrol());
		
		personaje.setSuperPoder(personajeDTO.getSuperPoder());
		return personaje;
		
	}
	
	/*
	 * Convierte la entidad en un dto para el personaje del comic
	 */
	private PersonajeDTO convertirEntidadDTO(Personaje personaje){
		
		PersonajeDTO personajeDTO = new PersonajeDTO();
		
		if (personaje.getId() != null) {
			personajeDTO.setId(personaje.getId());
		}
		
		personajeDTO.setNombre(personaje.getNombre());
		personajeDTO.setEstado(personaje.getEstado());
		
		personajeDTO.setIdComic(personaje.getComic().getId());
		personajeDTO.setIdrol(personaje.getRol().getId());
		
		personajeDTO.setSuperPoder(personaje.getSuperPoder());
		return personajeDTO;
		
	}

}
