import { Component, OnInit } from '@angular/core';
import { InfoPersonalDTO } from '../../dto/infoPersonal.dto';

/**
 * @description Componente Informaciòn Personal, el cual permite visualizar la informaciòn perosnal en la interfaz de bienvenida
 * 
 * @author Erik Dario Hernande Vasquez erikdhv@gmail.com
 */
@Component({
    selector: 'info-personal',
    templateUrl: './info-personal-component.html',
    styleUrls: ['./info-personal-component.css'],
  })

  /**
   * @description Clase que maneja los datos de la información personal de cada uno de nosotros
   * @author Erik Hernandez V, erikdhv@gmail.com
   * @date Diciembre 04 de 2019
   */
  export class InfoPersonalComponent implements OnInit {
    
    /**
     * Atributo de tipo InfoPersonalDTO, para crear los objetos de este tipo
     */
    private listaInfoPersonalDTO : InfoPersonalDTO;

    /**
     * Almacena la lista del DTO InfoPersonalDTO 
     */
    public listaInfoPersonal : Array<InfoPersonalDTO>;

    /**
     * Almacena copia del array DTo con el fin de poder recorrer y mostrar siempre la lista de los comic 
     */
    public listaInfoPersonalCopy : Array<InfoPersonalDTO>;

    /**
     * Almacena la lista de la información como una cadena de caracteres para ser mostrada
     */
    public _listaInfoPersonalDTOView : String;

    /**
     * Almacenamos el elemento eliminado
     */
    public elementRemovedJson : String;

    /**
     * Almacena el elemento de la lista de la información que se eliminará
     */
    public elementRemoved : any;
    

    /**
     * @description Variable usada para almacenar los nombre y apellidos del creador del componente
     */
    private _nombre: string;

    /**
     * @description Variable usada para almacenar la ciudad del creador del componente
     */
    private _ciudad: string;

    /**
     * @description Usada para evaluar si se elimino el tercer elemento del DTO
     */
    public vshow : boolean;

    /**
     * @description Constructor para la clase InfoPersonalComponent que nos permite inicializar e inyectas las dependencias
     */
    constructor() {
      console.log("entro al constructor del componente InfoPersonal");
    }
    
    /**
     * @description Funcion que muestra los datos Nombres y Ciudad del creador del componente, creada para garantizar el encapsulamiento de las variables
     */
    public info(): string {
        return this._nombre+" - "+this._ciudad;
    }

    /**
     * @description Evento angular que se ejecuta para inicializar nuestras variables
     */
    ngOnInit(): void {
      this._nombre = "Erik Darío Hernández Vásquez";
      this._ciudad = "Montería - Cordoba";
      this.vshow = false;
      
      // Creamos una instancia para el atributo de la clase listaInfoPersonal
      this.listaInfoPersonal = new Array<InfoPersonalDTO>();

      // Llamamos al metodo que realiza las asignaciones de información a los Objetos usados
      this.iniciarAsignaciones();
      
    }

    /**
     * @description Metodo usado para inicializar las asignaciones de datos a los objetos 
     * @author Erik Darío Hernández Vásquez
     * @version 1.0
     */
    private iniciarAsignaciones(): void {
      //Usamos la siguiente función para asignar los valores al DTO e ir creando la lista con la información.
      //Esto puede llegar desde un formulario
      this.asignaInformacion(1000, 'Primer Libro', 'Censura editores', 'Conocimiento y Ciencia', 356, 107845.75, 'Erik Hernandez', true, new Date(), 'Activo');
      this.asignaInformacion(2000, 'Historia de Colombia', 'Norma', 'Historia', 98765, 65000.54, 'Dario Vasquez', false, new Date(), 'Inactivo');
      this.asignaInformacion(3000, 'Youtuber Infantiles', 'Ra-ma', 'Infantil y Juvenil', 578, 34789.44, 'Don ramon, El chavo', true, new Date(), 'Activo');
      this.asignaInformacion(4000, 'Cuentos Infantiles de Ayer', 'Anaya Multimedia', 'Literatura', 600, 89789.20, 'Juan Pablo Montoya', true, new Date(), 'Activo');
      this.asignaInformacion(4856, 'Economía en Latinoamerica', 'Planeta', 'Comic y Fantasía', 2346, 99876.50, 'Desconocido', false, new Date(), 'Inactivo');

      // Le sacamos copia al objeto listaInfoPersonal para recorrer y mostrart siempre los comic
      this.listaInfoPersonalCopy = this.listaInfoPersonal.slice();
      //Asignamos a la variable _listaInfoPersonalDTOView la información del array para ser mostrada
      this._listaInfoPersonalDTOView = JSON.stringify(this.listaInfoPersonal, undefined, 4);
    }

    /**
     * @description Metodo usado para asignar los valores que recibe como parametros al DTO y luego vamos asignando 
     * los valores a la lista de Objetos
     * @author Erik Darío Hernández Vásquez
     * @version 1.0
     * 
     * @param id Identificador del comic
     * @param name Nombre del comic
     * @param editorial Editorial de comic
     * @param tematic Tematica del comic
     * @param pageNumber Numero de paginas
     * @param price Valor del comic
     * @param author Autor del comic
     * @param operator Operador de desicion
     * @param dateSale Fecha de venta de comic
     * @param state Estado del comic
     */
    private asignaInformacion(id: number, name: string, editorial: string, tematic: string, pageNumber: number, price: number, author: string, operator: boolean, dateSale: Date, state: string): void {
      // Creamos la instancia a la clase InfoPersonalDTO
      this.listaInfoPersonalDTO = new InfoPersonalDTO();

      // Asignamos la información al DTO
      this.listaInfoPersonalDTO.id = id;
      this.listaInfoPersonalDTO.nombre = name;
      this.listaInfoPersonalDTO.editorial = editorial;
      this.listaInfoPersonalDTO.tematica = tematic;
      this.listaInfoPersonalDTO.numeroPaginas = pageNumber;
      this.listaInfoPersonalDTO.precio = price;
      this.listaInfoPersonalDTO.autores = author;
      this.listaInfoPersonalDTO.aColor = operator;
      this.listaInfoPersonalDTO.fechaVenta = dateSale;
      this.listaInfoPersonalDTO.estado = state;

      // Asignamos el DTO a la lista de DTO's
      this.listaInfoPersonal.push(this.listaInfoPersonalDTO);
    }

    /**
     * @description Metodo usado para eliminar el tercer elemento del DTO y asignar el elemento eliminado a una 
     * variable a fin de mostrarla en la interfaz
     * @author Erik Darío Hernández Vásquez
     * @version 1.0
     */
    public eliminarTercerElemento(): void {
      this.elementRemoved = this.listaInfoPersonal[3];
      // Almacenamos en el atributo elementRemoved los datos que se van a eliminar para mostrarlos en la interfaz
      this.elementRemovedJson = JSON.stringify(this.elementRemoved, undefined, 4);

      // Se elimina el tercer elemento del arreglo
      this.listaInfoPersonal.splice(1,3);
      // Confirmamos al div que muestre los datos eliminados
      this.vshow = true;
    }

  }