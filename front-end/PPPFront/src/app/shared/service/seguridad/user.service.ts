import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // Realiza la conecxion con el servidor
  apiUrl = 'http://localhost:9000/usuarios/login';

  // Recursos usados
  constructor(private http: HttpClient) { }

  // LLamada al controlador del servicio.
  // Busca a un usuario con las especificaciones de email y password
  login(email: string, password: string): Observable<any> {
    const body = { email: email, password: password };
    return this.http.post<any>(this.apiUrl, body);
  }
}
