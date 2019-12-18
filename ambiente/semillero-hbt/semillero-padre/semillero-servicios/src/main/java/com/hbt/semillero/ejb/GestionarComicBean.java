/**
 * GestionarComicBean.java
 */
package com.hbt.semillero.ejb;

import java.math.BigDecimal;
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

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.entidad.Comic;
import com.hbt.semillero.entidad.TematicaEnum;
import com.hbt.semillero.interfaces.IUtilities;

/**
 * <b>Descripción:<b> Clase que determina el bean para realizar las gestion de
 * los comics
 * 
 * @author ccastano
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarComicBean implements IGestionarComicLocal, IUtilities {

	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	/**
	 * Atributo em que se usa para interacturar con el contexto de persistencia.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#crearComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearComic(ComicDTO comicNuevo) {
		// Entidad nueva
		Comic comic = convertirComicDTOToComic(comicNuevo);
		// Se almacena la informacion y se maneja la enidad comic
		em.persist(comic);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#modificarComic(com.hbt.semillero.dto.ComicDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void modificarComic(Long id, String nombre, ComicDTO comicNuevo) {
		Comic comicModificar;
		if (comicNuevo == null) {
			// Entidad a modificar
			comicModificar = em.find(Comic.class, id);
		} else {
			comicModificar = convertirComicDTOToComic(comicNuevo);
		}
		comicModificar.setNombre(nombre);
		em.merge(comicModificar);
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#eliminarComic(java.lang.Long)
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarComic(Long idComic) {
		Comic comicEliminar = em.find(Comic.class, idComic);
		if (comicEliminar != null) {
			em.remove(comicEliminar);
		}
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComic(java.lang.String)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ComicDTO consultarComic(String idComic) {
		Comic comic = null;
		comic = new Comic();
		comic = em.find(Comic.class, Long.parseLong(idComic));
		ComicDTO comicDTO = convertirComicToComicDTO(comic);
		return comicDTO;
	}

	/**
	 * 
	 * @see com.hbt.semillero.ejb.IGestionarComicLocal#consultarComics()
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ComicDTO> consultarComics() {
		logger.debug("Se ejecuta el comando");

		List<ComicDTO> resultadosComicDTO = new ArrayList<ComicDTO>();
		@SuppressWarnings("unchecked")
		List<Comic> resultados = em.createQuery("select c from Comic c").getResultList();
		for (Comic comic : resultados) {
			resultadosComicDTO.add(convertirComicToComicDTO(comic));
		}
		return resultadosComicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comic a un comicDTO
	 * 
	 * @param comic
	 * @return
	 */
	private ComicDTO convertirComicToComicDTO(Comic comic) {
		ComicDTO comicDTO = new ComicDTO();
		if (comic.getId() != null) {
			comicDTO.setId(comic.getId().toString());
		}
		comicDTO.setNombre(comic.getNombre());
		comicDTO.setEditorial(comic.getEditorial());
		comicDTO.setTematicaEnum(comic.getTematicaEnum());
		comicDTO.setColeccion(comic.getColeccion());
		comicDTO.setNumeroPaginas(comic.getNumeroPaginas());
		comicDTO.setPrecio(comic.getPrecio());
		comicDTO.setAutores(comic.getAutores());
		comicDTO.setColor(comic.getColor());
		comicDTO.setFechaVenta(comic.getFechaVenta());
		comicDTO.setEstadoEnum(comic.getEstadoEnum());
		comicDTO.setCantidad(comic.getCantidad());
		logger.debug("????Que tematica enviamos????? "+comic.getTematicaEnum());
		// Calculoar el iva modificar Comentarios
		comicDTO.setIva(this.calcularIva(comic.getTematicaEnum()));
		// Calculamos el precio total
		comicDTO.setPrecioTotal(this.calcularPrecioTotal(comicDTO.getIva(), comic.getPrecio()));

		return comicDTO;
	}

	/**
	 * 
	 * Metodo encargado de transformar un comicDTO a un comic
	 * 
	 * @param comic
	 * @return
	 */
	private Comic convertirComicDTOToComic(ComicDTO comicDTO) {
		Comic comic = new Comic();
		if (comicDTO.getId() != null) {
			comic.setId(Long.parseLong(comicDTO.getId()));
		}
		comic.setNombre(comicDTO.getNombre());
		comic.setEditorial(comicDTO.getEditorial());
		comic.setTematicaEnum(comicDTO.getTematicaEnum());
		comic.setColeccion(comicDTO.getColeccion());
		comic.setNumeroPaginas(comicDTO.getNumeroPaginas());
		comic.setPrecio(comicDTO.getPrecio());
		comic.setAutores(comicDTO.getAutores());
		comic.setColor(comicDTO.getColor());
		comic.setFechaVenta(comicDTO.getFechaVenta());
		comic.setEstadoEnum(comicDTO.getEstadoEnum());
		comic.setCantidad(comicDTO.getCantidad());
		return comic;
	}

	/**
	 * 
	 * Metodo encargado de retornar el calor del iva para el comic segun la tematica
	 * @author Erik Dario Hernandez Vasquez, erikdhv@gmail.com
	 * 
	 * @param tematicaEnum : TematicaEnum
	 * @return ivaTematica : float
	 */
	@Override
	public float calcularIva(TematicaEnum tematicaEnum) {
		float ivaTematica = 0;
		logger.debug("****LA TEMAtICA ES "+tematicaEnum);
		
		if (tematicaEnum != null) {
			switch (tematicaEnum.toString()) {
			case "AVENTURAS":
				ivaTematica = (float) 0.05;
				break;
			case "BELICO":
				ivaTematica = (float) 0.16;
				break;
			case "DEPORTIVO":
				ivaTematica = (float) 0.10;
				break;
			case "FANTASTICO":
				ivaTematica = (float) 0.05;
				break;
			case "CIENCIA_FICCION":
				ivaTematica = (float) 0.16;
				break;
			case "HISTORICO":
				ivaTematica = (float) 0.05;
				break;
			case "HORROR":
				ivaTematica = (float) 0.16;
				break;
			default:
				ivaTematica = (float) 0.5;
				break;
			}			
		}
		
		logger.debug("****LA ivaTematica ES "+ivaTematica);
		return ivaTematica;
	}

	/**
	 * 
	 * Metodo encargado de retornar el valor del comic mas el valor del iva del comic, 
	 * lo que sería el valor total a pagar por el comic
	 * @author Erik Dario Hernandez Vasquez, erikdhv@gmail.com
	 * 
	 * @param iva : float, price : BigDecimal
	 * @return precioTotal : BigDecimal
	 */
	@Override
	public BigDecimal calcularPrecioTotal(float iva, BigDecimal price) {
		// TODO Auto-generated method stub
		BigDecimal precioTotal = price.add(price.multiply(new BigDecimal(iva)));
		return precioTotal;
	}
}
