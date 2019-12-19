package com.hbt.semillero.rest;

import java.sql.SQLException;
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

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.ejb.GestionarComicBean;
import com.hbt.semillero.ejb.IGestionarPersonajesComicLocal;

/**
 * <b>Descripción:<b> Clase que determina el servicio rest que permite gestionar
 * un personajes de los comic
 * 
 * @author ehernandez
 * @version 1.0
 */
@Path("/GestionarPersonaje")
public class GestionarPersonajesRest {
	
	/**
	 * Atriburo que permite gestionar un peronaje del comic
	 */
	@EJB
	private IGestionarPersonajesComicLocal gestionarPersonajeComicBean;
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
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
	public void crearPersonajes(PersonajeDTO personajeDTO) {
		logger.debug("Inicia metodo crearPersonajes");
		gestionarPersonajeComicBean.crearPersonajes(personajeDTO);
		logger.debug("Finaliza el metodo crearPersonajes");
	}
	
	/**
	 * 
	 * Metodo encargado de modificar el nombre de un comic
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/modificarPersonajes?idComic=1&nombre=nuevonombre
	 * @param idComic identificador del comic a buscar
	 * @param nombre nombre nuevo del comic
	 */
	@POST
	@Path("/modificarPersonajes")
	@Produces(MediaType.APPLICATION_JSON)
	public void modificarPersonaje(@QueryParam("idPersonaje") Long idPersonaje, @QueryParam("nombre") String nombre, @QueryParam("PersonajeDTO") PersonajeDTO personajeDTO) {
		gestionarPersonajeComicBean.modificarPersonaje(idPersonaje, nombre, personajeDTO);
	}

	/**
	 * 
	 * Metodo encargado de eliminar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long idComic
	 * http://localhost:8085/semillero-servicios/rest/GestionarPersonaje/eliminarPersonaje?idPersonaje=1
	 */
	@POST
	@Path("/eliminarPersonaje")
	@Produces(MediaType.APPLICATION_JSON)
	public void eliminarPersonaje(@QueryParam("id") Long id) {
		gestionarPersonajeComicBean.eliminarPersonaje(id);
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
	public List<PersonajeDTO> consultarPersonajes(@QueryParam("idComic") String idComic){
		if (idComic != null) {
			List<PersonajeDTO> personajeDTO = gestionarPersonajeComicBean.consultarPersonajes(idComic.toString());
			return personajeDTO;
		}
		return null;
	}
	/*@GET
	@Path("/consultarPersonajesPorId")
	@Produces(MediaType.APPLICATION_JSON)
	public PersonajeDTO consultarPersonajes(@QueryParam("idComic") String idComic){
		if (idComic != null) {
			PersonajeDTO personajeDTO = gestionarPersonajeComicBean.consultarPersonajes(idComic.toString());
			return personajeDTO;
		}
		return null;
	}*/
	
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
	public List<PersonajeDTO> consultarPersonajes(){
		return gestionarPersonajeComicBean.consultarPersonajes();
	}
	
}
