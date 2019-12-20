import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PersonaDTO } from '../../dto/persona.dto';
import { Router } from '@angular/router';
import { PersonaService } from '../../services/persona.service';

/**
 * @description Componenete gestionar personas, el cual contiene la logica CRUD
 * 
 * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
 */
@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {
    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarPersonaForm : FormGroup;

    /**
     * Atributo que contendra la informacion de la Persona
     */
    public persona: PersonaDTO;

    /**
     * Atributo que contendra la lista de personas creados
     */
    public listaPersona : Array<PersonaDTO>;

    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted : boolean;

    /**
     * Atributo que indica si se van actualizar los datos
     */
    public edicion : boolean;

    public mensajeExito : String;

  /**
   * @description Este es el constructor del componente PersonaComponent
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  constructor(private fb : FormBuilder,
    private router : Router,
    private gestionarPersonaService : PersonaService) { 
      this.gestionarPersonaForm = this.fb.group({
        nombre : [null, Validators.required],
        tipid : ['CC'],
        numid : [null],
        fecnac : [null]
      });
    }

  /**
   * @description Evento angular que se ejecuta al invocar el componente
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  ngOnInit() {
    console.log("Ingreso al al evento oninit en componente PersonaComponent");
    this.persona = new PersonaDTO();
    this.listaPersona = new Array<PersonaDTO>();
    this.consultarPersonas();
  }

  /**
   * Metodo que permite consultar las Personas de la tabla y sus detalles e inhabilitar el formulario
   * @param posicion en la lista de la persona seleccionada
   */
  private consultarPersonas() : void {
    this.gestionarPersonaService.consultarPersonas().subscribe(listaPersonas => {
        this.listaPersona = listaPersonas;
    }, error => {
        console.log("Se a presentado un error al cargar los datos en el componente PersonaComponent");
    });
  }

  /**
   * @description Metodo que permite validar el formulario y crear una persona
   * 
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  public crearActualizarPersona() : void {
    this.submitted = true;
    if(this.gestionarPersonaForm.invalid) {
        return;
    }
    console.log("Dentro de la funcion Crear Personas");

    this.persona = new PersonaDTO();
    this.persona.nombre = this.gestionarPersonaForm.controls.nombre.value;
    this.persona.tipid = this.gestionarPersonaForm.controls.tipid.value;
    this.persona.numid = this.gestionarPersonaForm.controls.numid.value;
    this.persona.fecnac = this.gestionarPersonaForm.controls.fecnac.value;
    
    this.gestionarPersonaService.crearPersona(this.persona).subscribe(resultado => {
        this.mensajeExito = resultado.mensajeEjecucion;
        this.consultarPersonas();
        this.limpiarFormulario();
    }, error => {
        console.log("Se a presentado un error al consumir el servicio de Crear Personas");
    });
  }

  /**
   * @description Metodo que obtiene los controles y sus propiedades
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  get f() { 
    return this.gestionarPersonaForm.controls;
  }

  /**
   * @description Metodo que limpia los controles del formulario
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  private limpiarFormulario() : void {
    this.submitted = false;
    this.edicion = false;
    this.gestionarPersonaForm.controls.nombre.setValue(null);
    this.gestionarPersonaForm.controls.tipid.setValue('CC');
    this.gestionarPersonaForm.controls.numid.setValue(null);
    this.gestionarPersonaForm.controls.fecnac.setValue(null);
  }

}
