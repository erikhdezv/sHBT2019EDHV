/**
 * @description Clase InfoPersonalDTO que contiene la informacion de un Libro, Ejercicio Sessión 2 Angular
 * 
 * @author Erik Darío Hernández Vásquez <erikdhv@gmail.com>
 */
export class InfoPersonalDTO {

    /**
    * Indicador del Libro.
    */
    public id: number;

    /**
    * Nombre del Libro.
    */
    public nombre: string;

    /**
    * Editorial del Libro.
    */
    public editorial: string;

    /**
    * Almacen la tematica del contenido del Libro.
    */
    public tematica: string;

    /**
    * Número de páginas que contiene el Libro.
    */
    public numeroPaginas: number;

    /**
    * Almacena el valor del Libro
    */
    public precio: number;

    /**
    * Almacena el nombre de los autores del libro
    */
    public autores: string;

    /**
    * Operador de desición
    */
    public aColor: boolean;

    /**
    * Fecha de edicion de la información
    */
    public fechaVenta: Date;

    /**
    * Estado del registro
    */
    public estado: string;
  
}