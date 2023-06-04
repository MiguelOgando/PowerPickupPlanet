import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { Mantenimiento } from '../../interface/mantenimiento';

@Injectable({
  providedIn: 'root'
})
export class MantenimientoService {

  // Realiza la conecxion con el servidor
  private baseUrl = 'http://localhost:9000/mantenimientos';

  // Recursos usados
  constructor(private http: HttpClient) { }

  // LLamada al controlador del servicio. Obtiene todos los mantenimientos
  getAllMantenimientos() {
    return this.http.get<Mantenimiento[]>(`${this.baseUrl}/getAll`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Obtiene un mantenimiento por su id
  getMantenimientoById(id: number) {
    return this.http.get<Mantenimiento>(`${this.baseUrl}/get/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Crea un mantenimiento
  addMantenimiento(mantenimiento: Mantenimiento) {
    return this.http.post<Mantenimiento>(`${this.baseUrl}/add`, mantenimiento)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Actualiza un mantenimiento por su id
  updateMantenimiento(id: number, mantenimiento: Mantenimiento) {
    return this.http.put<Mantenimiento>(`${this.baseUrl}/edit/${id}`, mantenimiento)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Elimina un mantenimiento por su id
  deleteMantenimiento(id: number) {
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
