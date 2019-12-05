/**
 * GestionarPersonajesComicBean.java
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

//import com.hbt.semillero.dto.ComicDTO;
//import com.hbt.semillero.entidad.Comic;

/**
 * @Descripción: Clase que determina el bean para realizar las gestion de
 * los Personajes comics
 * 
 * @author ehernandez
 * @version 1.0
 */

/**
 * Se utiliza la anotación @Stateless,
 * 
 * Se utiliza la anotación @TransactionManagement, 
 * 
 * Se utiliza la anotación @SuppressWarnings temporalmente para evitar 
 * errores de paquetes importados no usados
 * 
 * @author ehernandez, erikdhv@gmail.com
 *
 */

@SuppressWarnings("unused")
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarPersonajesComicBean implements IGestionarPersonajesComicLocal{

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);
	/**
	 * @Descriptión: Constructor de la clase
	 * @author ehernandez
	 */
	public GestionarPersonajesComicBean() {
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void crearPersonajes() {
		// TODO Auto-generated method stub
		logger.debug("Se ejecuta el comando");		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void modificarComic() {
		// TODO Auto-generated method stub
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void eliminarComic() {
		// TODO Auto-generated method stub
		
	}

}
