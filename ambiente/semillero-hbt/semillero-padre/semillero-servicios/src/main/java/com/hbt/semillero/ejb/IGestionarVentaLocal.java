/**
 * 
 */
package com.hbt.semillero.ejb;

import java.util.List;

import com.hbt.semillero.dto.VentaDTO;
import com.hbt.semillero.entidad.Comic;

/**
 * @author user
 *
 */
public interface IGestionarVentaLocal {
	/**
	 * 
	 * Metodo encargado de crear los personajes de comic
	 * 
	 * @author ehernandez
	 * 
	 * @param Persona comicNuevo
	 */
	public void crearVenta(VentaDTO ventaDTO, Comic comic);

	/**
	 * 
	 * Metodo encargado de consultar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long id, String nombre, PersonaDTO personajeNuevo
	 */
	//public void modificarPersona(Long id, String nombre, PersonaDTO personaNuevo);
	
	/**
	 * 
	 * Metodo encargado de eliminar un Persona modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long idVenta
	 */
	//public void eliminarVenta(Long idVenta);

	/**
	 * @return 
	 * 
	 * Metodo encargado de retornar la informacion de un Persona
	 * 
	 * 
	 * @return comic Resultado de la consulta
	 * @throws 
	 */
	public List<VentaDTO> consultarVenta();

	/**
	 * 
	 * Metodo encargado de retornar una lista de Personajes comics
	 * 
	 * 
	 * @return
	 */
	public List<VentaDTO> consultarVenta(Long idVenta);
}
