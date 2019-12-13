/**
 * 
 */
package com.hbt.semillero.interfaces;

import java.math.BigDecimal;

import com.hbt.semillero.entidad.TematicaEnum;

/**
 * @author ehernandez, erikdhv2@gmail.com
 *
 */
public interface IUtilities {

	/**
	 * 
	 * Metodo encargado de crear un comic y persistirlo
	 * 
	 * @author ehernandez
	 * 
	 * @param tematica tematica del comic
	 */
	public float calcularIva(TematicaEnum tematica);

	/**
	 * 
	 * Metodo encargado de calcular el precio total del comic luego de calcular el iva y sumarlo al precio
	 * 
	 * @author ehernandez
	 * 
	 * @param iva porcentaje de iva a calcular para sumarlo al valor del comic, price precio base del comic sin iva
	 */
	public BigDecimal calcularPrecioTotal(float iva, BigDecimal price);
}
