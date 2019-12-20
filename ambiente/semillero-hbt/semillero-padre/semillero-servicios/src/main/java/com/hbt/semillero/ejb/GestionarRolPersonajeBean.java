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

import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.entidad.Rol;

/**
 * @Descripción: Clase que determina el bean para realizar las gestion de
 * los Roles de los Personajes comics
 * 
 * @version 1.0
 * 
 * Se utiliza la anotación @Stateless,
 * Se utiliza la anotación @TransactionManagement, 
 * Se utiliza la anotación @SuppressWarnings temporalmente para evitar 
 * errores de paquetes importados no usados
 * 
 * @author ehernandez, erikdhv@gmail.com
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarRolPersonajeBean implements IGestionarRolPersonajeLocal{

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManger;
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#crearRolPersonajes(com.hbt.semillero.dto.RolDTO)
	 */
	public void crearRolPersonajes(RolDTO rolDTO) {
		logger.debug("Inicia metodo crearRolPersonajes");
		
		Rol rol = convertirDTOEntidad(rolDTO);
		entityManger.persist(rol);
		
		logger.debug("Se ejecuta el comando crearRolPersonajes");	
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#modificarRolPersonaje()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void modificarRolPersonaje() {
		logger.debug("Inicia metodo modificarRolPersonaje");
		
		logger.debug("Finaliza metodo modificarRolPersonaje");
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#eliminarRolPersonaje()
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void eliminarRolPersonaje() {
		logger.debug("Inicia metodo eliminarRolPersonaje");
		
		logger.debug("Finaliza metodo eliminarRolPersonaje");
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#consultarRolPersonajes()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public List<RolDTO> consultarRolPersonajes() {
		logger.debug("Inicia metodo consultarRolPersonajes");
		
		String query = "SELECT rol "
				+"FROM Rol rol ";
		
		List<RolDTO> listaRolDTO = new ArrayList<RolDTO>();
		@SuppressWarnings("unchecked")
		List<Rol> listaRoles = entityManger.createQuery(query).getResultList();
		for(Rol roles : listaRoles) {
			listaRolDTO.add(convertirEntidadDTO(roles));
		}
		logger.debug("Finaliza metodo consultarRolPersonajes");
		
		return listaRolDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#consultarRolPersonajes(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<RolDTO> consultarRolPersonajes(String idRol) {
		logger.debug("Inicia metodo consultarRolPersonajes");
		
		String query = "SELECT rol "
				+ "FROM Rol rol "
				+ "WHERE personaje.id = :idRol";
		
		@SuppressWarnings("unchecked")
		List<Rol> listaRoles = entityManger.createQuery(query).setParameter("idRol", Long.parseLong(idRol)).getResultList();
		
		List<RolDTO> listaRolDTO = new ArrayList<RolDTO>();
		
		for(Rol roles : listaRoles) {
			listaRolDTO.add(convertirEntidadDTO(roles));
		}
		logger.debug("Finaliza metodo consultarRolPersonajes");
		
		return listaRolDTO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hbt.semillero.ejb.IGestionarRolPersonajeLocal#actualizarRolPersonaje()
	 */
	@Override
	public void actualizarRolPersonaje() {
		logger.debug("Inicia metodo actualizarRolPersonaje");
		
		logger.debug("Finaliza metodo actualizarRolPersonaje");
		
	}
	
	/*
	 * Convierte un Dto a la entidad para el Rol del personaje del comic
	 */
	private Rol convertirDTOEntidad(RolDTO rolDTO){
		
		Rol rol = new Rol();	
		
		if(rolDTO.getId()!=null) {
			rol.setId(rolDTO.getId());
		}

		rol.setNombre(rolDTO.getNombre());
		rol.setEstado(rolDTO.getEstado());
		
		return rol;
		
	}
	
	/*
	 * Convierte la entidad en un dto para el Rol del personaje del comic
	 */
	private RolDTO convertirEntidadDTO(Rol rol){
		
		RolDTO rolDTO = new RolDTO();
		
		if (rol.getId() != null) {
			rolDTO.setId(rol.getId());
		}
		
		rolDTO.setNombre(rol.getNombre());
		rolDTO.setEstado(rol.getEstado());
		return rolDTO;
		
	}

}
