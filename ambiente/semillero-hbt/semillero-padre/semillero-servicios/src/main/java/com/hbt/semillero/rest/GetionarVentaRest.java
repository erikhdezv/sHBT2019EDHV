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
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.VentaDTO;
import com.hbt.semillero.ejb.GestionarPersonaBean;
import com.hbt.semillero.ejb.IGestionarVentaLocal;
import com.hbt.semillero.entidad.Comic;

/**
 * @author ehernandez
 *
 */
@Path("/GestionarVenta")
public class GetionarVentaRest {

	final static Logger logger = Logger.getLogger(GestionarPersonaBean.class);

	/**
	* Atriburo que permite gestionar un Venta
	*/
	@EJB
	private IGestionarVentaLocal gestionarVentaEJB;

	/**
	* @description Crea las ventas en el sistema.
	*              http://localhost:8085/semillero-servicios/rest/GestionarVenta/crear
	*
	* @param ventaDTO
	*
	* @return
	*/
	@POST
	@Path("/crear")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response crearVenta(VentaDTO ventaDTO, Comic comic) {
		try {
			gestionarVentaEJB.crearVenta(ventaDTO, comic);
			return Response.status(Response.Status.CREATED)
			      .entity(ventaDTO)
			      .build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
			      .entity("Se presento fallo en la invocación del servicio al crear la venta " + e)
			      .build();
		}
	}


	/**
	*
	* @description Metodo encargado de traer la informacion de una venta determinada
	*              http://localhost:8085/semillero-servicios/rest/GestionarVenta/consultarId?id=1
	*
	* @param id
	* @return
	*/
	@GET
	@Path("/consultarId")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<VentaDTO> consultarVenta(@QueryParam("id") Long id) {
		List<VentaDTO> ventaDTO = null;
		try {
			if (id != null) {
				ventaDTO = gestionarVentaEJB.consultarVenta(id);
			}
		} catch (Exception e) {
			//logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"+ e.getMensaje());
		}
		return ventaDTO;

	}

	/**
	*
	* @description Metodo encargado de traer la informacion de las pesonas
	*              http://localhost:8085/semillero-servicios/rest/GestionarVenta/consultar
	*
	* @return
	*/
	@GET
	@Path("/consultar")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public List<VentaDTO> consultarVenta() {
		List<VentaDTO> ventaDTO = null;
		try {
			ventaDTO = gestionarVentaEJB.consultarVenta();
		} catch (Exception e) {
			//logger.error("Se capturo la excepcion y la informacion es:  codigo " + e.getCodigo() + " mensaje:"+ e.getMensaje());
		}
		return ventaDTO;
	}

}
