import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewProducto } from '../../interface/producto/newProducto';
import { Observable, catchError, throwError } from 'rxjs';
import { Producto } from '../../interface/producto/completeProducto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  // Realiza la conecxion con el servidor
  private apiUrl = 'http://localhost:9000/productos';

  // Recursos usados
  constructor(
    private http: HttpClient
  ) { }

  // LLamada al controlador del servicio. Obtiene todos los productos
  obtenerTodosLosProductos() {
    return this.http.get(this.apiUrl + '/getAll');
  }

  // LLamada al controlador del servicio. Crea un producto
  crearProducto(producto: NewProducto): Observable<NewProducto> {
    return this.http.post<NewProducto>(`${this.apiUrl}/add`, producto)
      .pipe(
        catchError(this.handleError)
      );
  }

  // LLamada al controlador del servicio. Obtiene un producto por su id
  obtenerProductoPorId(id: number): Observable<Producto> {
    const url = `${this.apiUrl}/get/${id}`;
    return this.http.get<Producto>(url);
  }

  // LLamada al controlador del servicio. Actualiza un producto por su id
  actualizarProducto(id: number, producto: Producto): Observable<Producto> {
    const url = `${this.apiUrl}/edit/${id}`;
    return this.http.put<Producto>(url, producto);
  }

  // LLamada al controlador del servicio. Elimina un productos por su id
  eliminarProductoPorId(id: number): Observable<any> {
    const url = `${this.apiUrl}/delete/${id}`;
    return this.http.delete(url);
  }

  // Manejo de los errores generales
  private handleError(error: any) {
    console.error('Ocurrió un error:', error);
    return throwError(error);
  }
}
