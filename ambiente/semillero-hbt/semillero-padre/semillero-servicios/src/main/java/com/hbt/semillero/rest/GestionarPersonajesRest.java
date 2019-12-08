package com.hbt.semillero.rest;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.ejb.IGestionarPersonajesComicLocal;

@Path("/*GestionarPersonaje*/")
public class GestionarPersonajesRest {
	
	@EJB
	private IGestionarPersonajesComicLocal gestionarPersonajeComicBean;
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
	/*
	 * @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON) 
	 * 
	 * @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON) 
	 */
	public void crearPersonajes(PersonajeDTO personajeDTO) {
		gestionarPersonajeComicBean.crearPersonajes(personajeDTO);
	}
	
	/**
	 * 
	 * Metodo encargado de consultar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long id, String nombre, ComicDTO comicNuevo
	 */
	//public void modificarComic();

	/**
	 * 
	 * Metodo encargado de eliminar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long idComic
	 */
	//public void eliminarComic();

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
	//@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<PersonajeDTO> consultarPersonajes(@QueryParam("idComic") Long idComic){
		return gestionarPersonajeComicBean.consultarPersonajes(idComic);
	}

	/**
	 * 
	 * Metodo encargado de retornar una lista de Personajes comics
	 * 
	 * 
	 * @return
	 */
	/*public void consultarPersonaje() {
		
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
	//@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<PersonajeDTO> consultarPersonajes(){
		return gestionarPersonajeComicBean.consultarPersonajes();
	}
	
	//public void actualizarPersonaje();
}
