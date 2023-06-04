import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Instalacion } from '../../interface/instalacion';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstalacionService {

  // Realiza la conecxion con el servidor
  private baseUrl = 'http://localhost:9000/instalaciones';

  // Recursos usados
  constructor(private http: HttpClient) { }

  // LLamada al controlador del servicio. Obtiene todas las instalaciones
  getAllInstalaciones() {
    return this.http.get<Instalacion[]>(`${this.baseUrl}/getAll`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Obtiene una instalacion por el id
  getInstalacionById(id: number) {
    return this.http.get<Instalacion>(`${this.baseUrl}/get/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Crea una instalacion
  addInstalacion(instalacion: Instalacion) {
    return this.http.post<Instalacion>(`${this.baseUrl}/add`, instalacion)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Actualiza una instalacion
  updateInstalacion(id: number, instalacion: Instalacion) {
    return this.http.put<Instalacion>(`${this.baseUrl}/edit/${id}`, instalacion)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Elimina una instalacion por su id
  deleteInstalacion(id: number) {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Manejo de los errores generales
  private handleError(error: any) {
    console.error('Ocurrió un error:', error);
    return throwError(error);
  }
}
