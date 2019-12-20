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
import javax.ws.rs.core.Response;

import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.ejb.IGestionarPersonaLocal;

/**
 * @author user
 *
 */
@Path("/GestionarPersona")
public class GestionarPersonaRest {

	//final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);

	/**
	* Atriburo que permite gestionar un Persona
	*/
	@EJB
	private IGestionarPersonaLocal gestionarPersonaEJB;

	/**
	* @description Crea las personas en el sistema. http://localhost:8085/semillero-servicios/rest/GestionarPersona/crear
	*
	* @param personaDTO
	*/
	@POST
	@Path("/crear")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response crearPersona(PersonaDTO personaDTO) {
		try {
			gestionarPersonaEJB.crearPersona(personaDTO);
			return Response.status(Response.Status.CREATED)
			      .entity(personaDTO)
			      .build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
			      .entity("Se presento fallo en la invocación del servicio al crear la persona " + e)
			      .build();
		}
	}

	/**
	*
	* @description Metodo encargado de traer la informacion de una persona determinada
	*              http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarId?id=1
	* @param id
	* @return
	*/
	@GET
	@Path("/consultarId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonaDTO> consultarPersona(@QueryParam("id") Long id) {
		List<PersonaDTO> personaDTO = null;
		try {
			if (id != null) {
				personaDTO = gestionarPersonaEJB.consultarPersona(id);
			}
		} catch (Exception e) {
			//logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"+ e.getMensaje());
		}
		
		return personaDTO;
	}

	/**
	*
	* @description Metodo encargado de traer la informacion de las pesonas
	*              http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultar
	*
	* @return
	*/
	@GET
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonaDTO> consultarPersona() {
		List<PersonaDTO> personaDTO = null;
		try {
			personaDTO = gestionarPersonaEJB.consultarPersona();
		} catch (Exception e) {
			//logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"+ e.getMensaje());
		}
		return personaDTO;
	}
}
