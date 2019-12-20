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

import com.hbt.semillero.dto.VentaDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.Persona;
import com.hbt.semillero.entidad.Venta;

/**
 * @author ehernandez
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarVentaBean implements IGestionarVentaLocal{

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager entityManger;
	
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearVenta(VentaDTO ventaDTO, Comic comic) {
		logger.debug("Inicia metodo crearPersonaje");
		try {
			Venta venta = convertirDTOEntidad(ventaDTO);
			entityManger.persist(venta);
			// Aqui persistimos los elementos de la tabla intermedia entre ventas y comic
			venta.addComic( comic );
		} catch (Exception e) {
			logger.error("Se presento un error al guardar los venta " + e);
		}
		
		logger.debug("Se ejecuta el comando");
		
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<VentaDTO> consultarVenta() {
		logger.debug("Inicia metodo consultarPersona");
		
		String query = "SELECT persona "
				+"FROM Persona persona ";
		
		// Creamos el Objeto de tipo Lista para el DTO de los Personajes
		List<VentaDTO> listaVentaDTO = new ArrayList<VentaDTO>();
		
		try {
			@SuppressWarnings("unchecked")
			// Asignamos el resultado de la consulta al Entity manager
			List<Venta> listaVenta = entityManger.createQuery(query).getResultList();

			// Recorremos la lista de personajes Retornados y creamos el la Lista DTO
			for(Venta venta : listaVenta) {
				// Convertimos los datos del tipo Entidad a DTO para luego retornarlos
				listaVentaDTO.add(convertirEntidadDTO(venta));
			}
			
		} catch (Exception e) {
			logger.error("Se ha presentado un error al Consultar los persona "+ e);
		}
		logger.debug("Finaliza metodo consultarPersona");
		
		return listaVentaDTO;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<VentaDTO> consultarVenta(Long idVenta) {
		String query = "SELECT venta "
				+ "FROM Venta venta "
				+ "WHERE venta.id = :idVenta";
		
		List<VentaDTO> listaVentaDTO = new ArrayList<VentaDTO>();
		
		try {
			@SuppressWarnings("unchecked")
			List<Venta> listaVenta = entityManger.createQuery(query).setParameter("idVenta", idVenta).getResultList();
			
			for(Venta venta : listaVenta) {
				listaVentaDTO.add(convertirEntidadDTO(venta));
			}			
		} catch (Exception e) {
			logger.error("Error al consultar persona por id " + e);
		}
		
		logger.debug("Finaliza metodo consultarPersona");
		
		return listaVentaDTO;
	}
	
	/*
	 * Convierte un Dto a la entidad para el venta
	 */
	private Venta convertirDTOEntidad(VentaDTO ventaDTO){
		
		Venta venta = new Venta();	
		
		if(ventaDTO.getId()!=null) {
			venta.setId(ventaDTO.getId());
		}
		
		venta.setIdpersona(new Persona());
		venta.getPersona().setId(ventaDTO.getIdpersona().getId());
		
		venta.setFecventa(ventaDTO.getFecventa());
		
		return venta;
	}
	
	/*
	 * Convierte la entidad en un dto para el venta del comic
	 */
	private VentaDTO convertirEntidadDTO(Venta venta){
		
		VentaDTO ventaDTO = new VentaDTO();
		
		if (venta.getId() != null) {
			ventaDTO.setId(venta.getId());
		}
		
		ventaDTO.setIdpersona(venta.getIdpersona());
		ventaDTO.setFecventa(venta.getFecventa());
		
		return ventaDTO;
	}

}
