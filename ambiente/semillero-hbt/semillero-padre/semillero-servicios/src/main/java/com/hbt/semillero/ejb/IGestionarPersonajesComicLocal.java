/**
 * IGestionarPersonajesComicLocal.java
 */
package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.dto.PersonajeDTO;

/**
 * Ésta interfaz contine los metodos del EJB GestionarPersonajesComic 
 * que se deben implementar en su respectiva clase.
 * 
 * Se utiliza la anotación @Local, si el cliente EJB está en el mismo 
 * entorno donde se implementará el bean de sesión EJB.
 * 
 * @author ehernandez, erikdhv@gmail.com
 *
 */

@Local
public interface IGestionarPersonajesComicLocal {
	/**
	 * 
	 * Metodo encargado de crear los personajes de comic
	 * 
	 * @author ehernandez
	 * 
	 * @param ComicDTO comicNuevo
	 */
	public void crearPersonajes(PersonajeDTO personajeDTO);

	/**
	 * 
	 * Metodo encargado de consultar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long id, String nombre, PersonajeDTO personajeNuevo
	 */
	public void modificarComic(Long id, String nombre, PersonajeDTO personajeNuevo);
	
	/**
	 * 
	 * Metodo encargado de eliminar un Personaje comic modificarlo y guardarlo
	 * 
	 * @author ehernandez
	 * 
	 * @param Long idComic
	 */
	public void eliminarComic();

	/**
	 * @return 
	 * 
	 * Metodo encargado de retornar la informacion de un Personaje comic
	 * 
	 * 
	 * @return comic Resultado de la consulta
	 * @throws 
	 */
	public List<PersonajeDTO> consultarPersonajes();

	/**
	 * 
	 * Metodo encargado de retornar una lista de Personajes comics
	 * 
	 * 
	 * @return
	 */
	//public List<PersonajeDTO> consultarPersonajes(String idComic);
	public PersonajeDTO consultarPersonajes(String idComic);
	
	public void actualizarPersonaje();
}
