import { Component, OnInit } from '@angular/core';

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
     * @description Variable usada para almacenar los nombre y apellidos del creador del componente
     */
    private _nombre: string;

    /**
     * @description Variable usada para almacenar la ciudad del creador del componente
     */
    private _ciudad: string;
    
    /**
     * @description Constructor para la clase InfoPersonalComponent
     */
    constructor() {
      console.log("entro al constructor del componente InfoPersonal");
      this._nombre = "Erik Darío Hernández Vásquez";
      this._ciudad = "Montería - Cordoba"
    }
    
    /**
     * @description Funcion que muestra los datos Nombres y Ciudad del creador del componente, creada para garantizar el encapsulamiento de las variables
     */
    public info(): string {
        return this._nombre+" - "+this._ciudad;
    }

    ngOnInit(): void {
    }
  }