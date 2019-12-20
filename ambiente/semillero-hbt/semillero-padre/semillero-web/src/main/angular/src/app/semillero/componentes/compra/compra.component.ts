import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CompraDTO } from '../../dto/compra.dto';
import { Router } from '@angular/router';
import { CompraService } from '../../services/compra.service';
import { ComicDTO } from 'src/app/app.module';
import { PersonaDTO } from '../../dto/persona.dto';
import { EjemploService } from '../../services/ejemplo.service';
import { PersonaService } from '../../services/persona.service';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {
  /**
   * Atributo que contiene los controles del formulario
   */
  public gestionarCompraForm : FormGroup;

  /**
   * Atributo que contendra la informacion de la Compra
   */
  public compra: CompraDTO;

  /**
   * Atributo que contendra la informacion de la Persona
   */
  public personas: PersonaDTO;

  /**
   * Atributo que contendra la informacion de los Comic
   */
  public comic: ComicDTO;

  /**
   * Atributo que contendra la lista de Compras creados
   */
  public listaCompra : Array<CompraDTO>;

  /**
   * Atributo que contendra la lista de personas creados
   */
  public listaPersona : Array<PersonaDTO>;

  /**
   * Atributo que contendra la lista de Comic creados
   */
  public listaComic : Array<ComicDTO>;

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
   * @description Este es el constructor del componente CompraComponent
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  constructor(private fb : FormBuilder,
    private router : Router,
    private gestionarCompraService : CompraService, 
    private gestionarComicService : EjemploService,
    private gestionarPersonaService : PersonaService) { 
      this.gestionarCompraForm = this.fb.group({
        idpersona : [null, Validators.required],
        idComic : [null, Validators.required],
        fecventa : [null]
      });
    }
  /**
   * @description Evento angular que se ejecuta al invocar el componente
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  ngOnInit() {
    console.log("Ingreso al al evento oninit en componente CompraComponent");
    this.compra = new CompraDTO();
    this.listaCompra = new Array<CompraDTO>();
    this.consultarCompras();

    // Consultamos las comic
    this.comic = new ComicDTO();
    this.listaComic = new Array<ComicDTO>();
    this.consultarComics();

    // Consultamos las personas
    this.personas = new PersonaDTO();
    this.listaPersona = new Array<PersonaDTO>();
    this.consultarPersona();
  }

  /**
   * Metodo que permite consultar las Ventas de la tabla y sus detalles
   * @param 
   */
  private consultarCompras() : void {
    this.gestionarCompraService.consultarCompras().subscribe(listaCompras => {
        this.listaCompra = listaCompras;
    }, error => {
        console.log("Se a presentado un error al cargar los datos en el componente CompraComponent");
    });
  }

  /**
   * Metodo que permite consultar los comic de la tabla y sus detalles
   * @param 
   */
  private consultarComics() : void {
    this.gestionarComicService.consultarComics().subscribe(listaComics => {
        this.listaComic = listaComics;
    }, error => {
        console.log("Se a presentado un error al cargar los datos en el componente CompraComponent metodo consultarComics");
    });
  }
  
  /**
   * Metodo que permite consultar los comic de la tabla y sus detalles
   * @param 
   */
  private consultarPersona() : void {
    this.gestionarPersonaService.consultarPersonas().subscribe(listaPersonas => {
        this.listaPersona = listaPersonas;
    }, error => {
        console.log("Se a presentado un error al cargar los datos en el componente CompraComponent metodo consultarPersona");
    });
  }
  
  /**
   * @description Metodo que obtiene los controles y sus propiedades
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  get f() { 
    return this.gestionarCompraForm.controls;
  }

  /**
   * @description Metodo que limpia los controles del formulario
   * @author Erik Dario Hernandez Vasquez <erikdhv@gmail.com>
   */
  private limpiarFormulario() : void {
    this.submitted = false;
    this.edicion = false;
    this.gestionarCompraForm.controls.idpersona.setValue(null);
    this.gestionarCompraForm.controls.idComic.setValue(null);
    this.gestionarCompraForm.controls.fecventa.setValue(null);
  }

}
