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
		try {
			Personaje personaje = convertirDTOEntidad(personajeDTO);
			entityManger.persist(personaje);			
		} catch (Exception e) {
			logger.error("Se presento un error al guardar los personajes " + e);
		}
		
		logger.debug("Se ejecuta el comando");		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#modificarComic()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarPersonaje(Long id, String nombre, PersonajeDTO personajeNuevo) {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		// Se crea instancia a la entidad a modificar
		Personaje personajeModificar;
		//logger.debug("PERSONAJE DTO ES SIGUAL A  "+personajeDTO);
		try {
			// Verificamos que el DTO enviado como parametro sea nulo
			if (personajeNuevo == null) {
				// Buscamos en la entidad a modificar el registro indicado por Id
				personajeModificar = entityManger.find(Personaje.class, id);
				logger.debug("Personaje encontrado modificarPersonaje "+personajeModificar);
			}else {
				// Si se envia el DTO se carga en la variable que contiene los datos a modificar
				personajeModificar = convertirDTOEntidad(personajeNuevo);
			}
			
			// Se asigna el nuevo nombre para el personaje a nodificar. 
			personajeModificar.setNombre(nombre);
			// Se aplican los cambios de la modificacion
			entityManger.merge(personajeModificar);
			
		} catch (Exception e) {
			logger.error("Error al actualizar el Pesonaje del Comic" + e);
		}
		
		logger.debug("Finaliza metodo actualizarPersonaje");
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#eliminarComic()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void eliminarPersonaje(Long id) {
		logger.debug("Inicia metodo eliminarPersonaje "+id);
		//Personaje personajeEliminar;
		try {
			Personaje personajeEliminar = entityManger.find(Personaje.class, id);
			
			logger.debug("Personaje encontrado "+personajeEliminar);
			if (personajeEliminar != null) {
				entityManger.remove(personajeEliminar);
				entityManger.flush();
				logger.debug("Personaje no encontrado");
			}
			
		} catch (Exception e) {
			logger.error("Error al eliminar el registro en eliminarPersonaje "+e);
		}
		
		logger.debug("Finaliza metodo eliminarPersonaje");
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
		
		// Creamos el Objeto de tipo Lista para el DTO de los Personajes
		List<PersonajeDTO> listaPersonajeDTO = new ArrayList<PersonajeDTO>();
		
		try {
			@SuppressWarnings("unchecked")
			// Asignamos el resultado de la consulta al Entity manager
			List<Personaje> listaPersonajes = entityManger.createQuery(query).getResultList();

			// Recorremos la lista de personajes Retornados y creamos el la Lista DTO
			for(Personaje personajes : listaPersonajes) {
				// Convertimos los datos del tipo Entidad a DTO para luego retornarlos
				listaPersonajeDTO.add(convertirEntidadDTO(personajes));
			}
			
		} catch (Exception e) {
			logger.error("Se ha presentado un error al Consultar los personajes "+ e);
		}
		logger.debug("Finaliza metodo consultarPersonaje");
		
		return listaPersonajeDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarPersonajesComicLocal#consultarPersonajes(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonajeDTO> consultarPersonajes(String idComic) {
		String query = "SELECT personaje "
				+ "FROM Personaje personaje "
				+ "WHERE personaje.comic.id = :idComic";
		
		List<PersonajeDTO> listaPersonajeDTO = new ArrayList<PersonajeDTO>();
		
		try {
			@SuppressWarnings("unchecked")
			List<Personaje> listaPersonajes = entityManger.createQuery(query).setParameter("idComic", Long.parseLong(idComic)).getResultList();
			
			for(Personaje personaje : listaPersonajes) {
				listaPersonajeDTO.add(convertirEntidadDTO(personaje));
			}			
		} catch (Exception e) {
			logger.error("Error al consultar personaje por id de comic " + e);
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
