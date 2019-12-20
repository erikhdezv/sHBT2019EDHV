import { Injectable } from '@angular/core';
import { Injector } from "@angular/core";
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from './template.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CompraDTO } from '../dto/compra.dto';

/**
 * Servicio encargado de llamar a los servicios REST de
 * ejemplo
 */
@Injectable({
  providedIn: 'root'
})
export class CompraService extends AbstractService {

  /**
   * Constructor
   */
  constructor(injector: Injector, private httpClient : HttpClient) {
    super(injector);
  }

  
  public consultarCompras(): Observable<any> {

    // Se debe asignar el servicio correcto
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultar');
  }

  public crearCompra(compraDTO : CompraDTO): Observable<any> {

    // Se debe Asignar el servicio correcto
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarPersona/crear',compraDTO);
  }
}