import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Calculo } from '../../interface/calculadora';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculadoraService {

  // Realiza la conecxion con el servidor
  private baseUrl = 'http://localhost:9000/calculo';

  // Recursos usados
  constructor(private http: HttpClient) {}

  // LLamada al controlador del servicio. Realiza el calculo
  calcularAhorroEnergiaYDinero(calculo: Calculo): Observable<Calculo> {
    return this.http.post<Calculo>(this.baseUrl, calculo);
  }
}
