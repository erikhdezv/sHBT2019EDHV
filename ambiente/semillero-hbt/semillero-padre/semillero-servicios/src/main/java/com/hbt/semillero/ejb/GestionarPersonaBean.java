/**
 * 
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

import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.entidad.Persona;

/**
 * @author ehernandez
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonaBean implements IGestionarPersonaLocal {

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManger;
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersona(PersonaDTO personaDTO) {
		logger.debug("Inicia metodo crearPersonaje");
		try {
			Persona persona = convertirDTOEntidad(personaDTO);
			entityManger.persist(persona);			
		} catch (Exception e) {
			logger.error("Se presento un error al guardar los personajes " + e);
		}
		
		logger.debug("Se ejecuta el comando");	
		
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersona() {
		logger.debug("Inicia metodo consultarPersona");
		
		String query = "SELECT persona "
				+"FROM Persona persona ";
		
		// Creamos el Objeto de tipo Lista para el DTO de los Personajes
		List<PersonaDTO> listaPersonaDTO = new ArrayList<PersonaDTO>();
		
		try {
			@SuppressWarnings("unchecked")
			// Asignamos el resultado de la consulta al Entity manager
			List<Persona> listaPersona = entityManger.createQuery(query).getResultList();

			// Recorremos la lista de personajes Retornados y creamos el la Lista DTO
			for(Persona persona : listaPersona) {
				// Convertimos los datos del tipo Entidad a DTO para luego retornarlos
				listaPersonaDTO.add(convertirEntidadDTO(persona));
			}
			
		} catch (Exception e) {
			logger.error("Se ha presentado un error al Consultar los persona "+ e);
		}
		logger.debug("Finaliza metodo consultarPersona");
		
		return listaPersonaDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersona(Long idPersona) {
		String query = "SELECT persona "
				+ "FROM Persona persona "
				+ "WHERE personaje.id = :idPersona";
		
		List<PersonaDTO> listaPersonaDTO = new ArrayList<PersonaDTO>();
		
		try {
			@SuppressWarnings("unchecked")
			List<Persona> listaPersona = entityManger.createQuery(query).setParameter("idPersona", idPersona).getResultList();
			
			for(Persona persona : listaPersona) {
				listaPersonaDTO.add(convertirEntidadDTO(persona));
			}			
		} catch (Exception e) {
			logger.error("Error al consultar persona por id " + e);
		}
		
		logger.debug("Finaliza metodo consultarPersona");
		
		return listaPersonaDTO;
	}

	/*
	 * Convierte un Dto a la entidad para el personaje del comic
	 */
	private Persona convertirDTOEntidad(PersonaDTO personaDTO){
		
		Persona persona = new Persona();	
		
		if(personaDTO.getId()!=null) {
			persona.setId(personaDTO.getId());
		}

		persona.setNombre(personaDTO.getNombre());
		persona.setNumid(personaDTO.getNumid());		
		persona.setTipid(personaDTO.getTipid());
		persona.setFecnac(personaDTO.getFecnac());
		
		return persona;
	}
	
	/*
	 * Convierte la entidad en un dto para el personaje del comic
	 */
	private PersonaDTO convertirEntidadDTO(Persona persona){
		
		PersonaDTO personajeDTO = new PersonaDTO();
		
		if (persona.getId() != null) {
			personajeDTO.setId(persona.getId());
		}
		
		personajeDTO.setNombre(persona.getNombre());
		personajeDTO.setNumid(persona.getNumid());
		
		personajeDTO.setTipid(persona.getTipid());
		personajeDTO.setFecnac(persona.getFecnac());
		
		return personajeDTO;
	}
}
