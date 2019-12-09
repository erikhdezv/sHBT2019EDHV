/**
 * 
 */
package com.hbt.semillero.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.RolDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarRolPersonajeLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un Rol para los personajes de los comic
 * 
 * @author ehernandez
 * @version 1.0
 */
@Path("/GestionarRolPersonaje")
public class GestionarRolPersonajeRest {

	/**
	 * Atriburo que permite gestionar un rol para los personajes de los comic
	 */
	@EJB
	private IGestionarRolPersonajeLocal gestionarRolPersonajeLocal;
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	/**
	 *  GestionarRolPersonaje/saludo
	 * @return
	 */
	@GET
	@Path("/saludo")
	@Produces(MediaType.APPLICATION_JSON)
	public String primerRest() {
		logger.debug("Inicia metodo actualizarPersonaje");
		
		logger.debug("Finaliza metodo actualizarPersonaje");
		return "Prueba inicial servicios rest en el semillero java hbt";
	}
	
	/**
	 * 
	 * Metodo encargado de crear los personajes de comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/crear
	 * 
	 * @author ehernandez
	 * 
	 * @param ComicDTO comicNuevo
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void crearRolPersonajes(RolDTO rolDTO) {
		logger.debug("Inicia metodo crearPersonajes");
		
		gestionarRolPersonajeLocal.crearRolPersonajes(rolDTO);
	}
	
	/**
	 * @return 
	 * 
	 * Metodo encargado de retornar la informacion de un Personaje comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajes?idComic=1
	 * 
	 * 
	 * @return comic Resultado de la consulta
	 * @throws 
	 */
	@GET
	@Path("/consultarPersonajesPorId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO> consultarRolPersonajes(@QueryParam("idRol") String idRol){
		return gestionarRolPersonajeLocal.consultarRolPersonajes(idRol.toString());
	}
	
	/**
	 * 
	 * Metodo encargado de retornar una lista de Personajes comics
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/consultarPersonajes
	 * 
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarPersonajes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RolDTO> consultarRolPersonajes(){
		return gestionarRolPersonajeLocal.consultarRolPersonajes();
	}
}
