/**
 * 
 */
package com.hbt.semillero.ejb;

import java.util.List;

import com.hbt.semillero.dto.PersonaDTO;

/**
 * @author user
 *
 */
public interface IGestionarPersonaLocal {
	/**
	 * 
	 * Metodo encargado de crear los personajes de comic
	 * 
	 * @author ehernandez
	 * 
	 * @param Persona comicNuevo
	 */
	public void crearPersona(PersonaDTO personaDTO);

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
	 * @param Long idPersona
	 */
	//public void eliminarPersona(Long idPersona);

	/**
	 * @return 
	 * 
	 * Metodo encargado de retornar la informacion de un Persona
	 * 
	 * 
	 * @return comic Resultado de la consulta
	 * @throws 
	 */
	public List<PersonaDTO> consultarPersona();

	/**
	 * 
	 * Metodo encargado de retornar una lista de Personajes comics
	 * 
	 * 
	 * @return
	 */
	public List<PersonaDTO> consultarPersona(Long idPersona);
}
