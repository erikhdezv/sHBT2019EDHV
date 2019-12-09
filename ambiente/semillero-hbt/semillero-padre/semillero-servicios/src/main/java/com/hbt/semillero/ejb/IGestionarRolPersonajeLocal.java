/**
 * 
 */
package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.PersonajeDTO;
import com.hbt.semillero.dto.RolDTO;

/**
 * Ésta interfaz contine los metodos del EJB GestionarRolPersonajeBean 
 * que se deben implementar en su respectiva clase.
 * 
 * Se utiliza la anotación @Local, si el cliente EJB está en el mismo 
 * entorno donde se implementará el bean de sesión EJB.
 * 
 * Se utiliza la anotación @SuppressWarnings temporalmente para evitar 
 * errores de paquetes importados no usados
 * 
 * @author ehernandez, erikdhv@gmail.com
 *
 */
@Local
public interface IGestionarRolPersonajeLocal {

	/**
	 * 
	 * Metodo encargado de crear los roles de los personajes de comic
	 * 
	 * @author ehernandez
	 * 
	 * @param RolDTO rolDTO
	 */
	public void crearRolPersonajes(RolDTO rolDTO);

	/**
	 * 
	 * Metodo encargado de consultar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long id, String nombre, RolDTO rolNuevo
	 */
	public void modificarRolPersonaje();

	/**
	 * 
	 * Metodo encargado de eliminar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long idComic
	 */
	public void eliminarRolPersonaje();

	/**
	 * @return 
	 * 
	 * Metodo encargado de retornar la informacion de un Rol Personaje
	 * 
	 * 
	 * @return comic Resultado de la consulta
	 * @throws 
	 */
	public List<RolDTO> consultarRolPersonajes();

	/**
	 * 
	 * Metodo encargado de retornar una lista de roles de Personajes
	 * 
	 * 
	 * @return
	 */
	public List<RolDTO> consultarRolPersonajes(String idRol);
	
	public void actualizarRolPersonaje();
}
