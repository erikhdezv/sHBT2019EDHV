
import { ComicDTO } from '../../dto/comic.dto';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EjemploService } from '../../services/ejemplo.service';

/**
 * @description Componenete gestionar comic, el cual contiene la logica CRUD
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
    selector: 'gestionar-comic',
    templateUrl: './gestionar-comic.html',
    styleUrls: ['./gestionar-comic.css']
})
export class GestionarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarComicForm : FormGroup;

    /**
     * Atributo que contendra la informacion del comic
     */
    public comic: ComicDTO;

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaComics : Array<ComicDTO>;

    public idComic : number = 0;

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
     * @description Este es el constructor del componente GestionarComicComponent
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    constructor(private fb : FormBuilder,
        private router : Router,
        private gestionarComicService : EjemploService) {
        this.gestionarComicForm = this.fb.group({
            nombre : [null, Validators.required],
            editorial : [null],
            tematica : ['AVENTURAS'],
            coleccion : [null],
            numeroPaginas : [null],
            precio : [null],
            autores : [null],
            color : [null]
        });
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    ngOnInit(): void {
        console.log("Ingreso al al evento oninit");
        this.comic = new ComicDTO();
        this.listaComics = new Array<ComicDTO>();
        this.consultarComics();
    }

    /**
     * @description Metodo que permite validar el formulario y crear o actulizar un comic
     */
    public crearActualizarComic() : void {
        this.submitted = true;
        if(this.gestionarComicForm.invalid) {
            return;
        }
        //this.idComic++;
        this.comic = new ComicDTO();
        //this.comic.id = this.idComic + "";
        this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
        this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
        this.comic.tematica = this.gestionarComicForm.controls.tematica.value;
        this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
        this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
        this.comic.precio = this.gestionarComicForm.controls.precio.value;
        this.comic.autores = this.gestionarComicForm.controls.autores.value;
        this.comic.color = this.gestionarComicForm.controls.color.value;
        
        this.gestionarComicService.crearComic(this.comic).subscribe(resultado => {
            this.mensajeExito = resultado.mensajeEjecucion;
            this.consultarComics();
            this.limpiarFormulario();
        }, error => {
            console.log("Se a presentado un error al consumir el servicio de Crear comic");
        });

        //this.listaComics.push(this.comic);
        
    }


    /**
     * Metodo que permite consultar un comic de la tabla y sus detalles e inhabilitar el formulario
     * @param posicion en la lista del comic seleccionado
     */
    private consultarComics() : void {
        this.gestionarComicService.consultarComics().subscribe(listaComics => {
            this.listaComics = listaComics;
        }, error => {
            console.log("Se a presentado un error al cargar los datos");
        });
    }

    /**
     * Metodo que permite consultar un comic de la tabla y sus detalles e inhabilitar el formulario
     * @param posicion en la lista del comic seleccionado
     */
    public consultarComic(posicion : number) : void {
        let comic = this.listaComics[posicion];
        console.log(JSON.stringify(comic));
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        if (this.edicion == false){
            this.gestionarComicForm.controls.nombre.disable();
            this.gestionarComicForm.controls.editorial.disable();
            this.gestionarComicForm.controls.tematica.disable();
            this.gestionarComicForm.controls.coleccion.disable();
            this.gestionarComicForm.controls.numeroPaginas.disable();
            this.gestionarComicForm.controls.precio.disable();
            this.gestionarComicForm.controls.autores.disable();
            this.gestionarComicForm.controls.color.disable();
        }
//        this.gestionarComicForm.controls.color.enable(); para habilitar el campo

    }

    /**
     * @description Metodo encargado de cargar los datos al formulario para actualizar
     * @author Erik Darío Hernández Vásquez, erikdhv@gmail.com
     * @param comic : any
     */
    public updateComic(comic: any) : void {
        this.edicion = true;
        // Cargamos al formulario los Datos del item seleccionado
        this.consultarComic(comic.id-1);

    }

    /**
     * @description Metodo que elimina un elemento del DTO
     * @author Erik Darío Hernández Vásquez, erikdhv@gmail.com
     * @param comic : any
     */
    public deleteComic(comic : any) : void {
        // buscamos el elemento a eliminar en el objeto de datos
        var i = this.listaComics.indexOf( comic );
        // Si encontramos el elemento, se procede a eliminarlo del DTO
        if ( i !== -1 ) {
            // Se elimina el elemento del DTO
            this.listaComics.splice( i, 1 );
        }
        // Se hace el llamadoa la funcion que limpia el formulario
        this.limpiarFormulario();
    }

    private limpiarFormulario() : void {
        this.submitted = false;
        this.edicion = false;
        this.gestionarComicForm.controls.nombre.setValue(null);
        this.gestionarComicForm.controls.editorial.setValue(null);
        this.gestionarComicForm.controls.tematica.setValue(null);
        this.gestionarComicForm.controls.coleccion.setValue(null);
        this.gestionarComicForm.controls.numeroPaginas.setValue(null);
        this.gestionarComicForm.controls.precio.setValue(null);
        this.gestionarComicForm.controls.autores.setValue(null);
        this.gestionarComicForm.controls.color.setValue(null);
    }

    /**
     * @description Metodo que obtiene los controles y sus propiedades
     * @author Diego Fernando Alvarez Silva
     */
    get f() { 
        return this.gestionarComicForm.controls;
    }
}