import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { Router } from '@angular/router';
import {CookieService} from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Manejo de errores generales
  setLoggedIn(args0: boolean) {
    throw new Error('Method not implemented.')
  }

  // Recursos usados
  constructor(
    private userService: UserService,
    private router: Router,
    private cookieService: CookieService
    ) { }

  // Se asegura que tanto el email como su password respectivo esten existentes en la aplicacion
  loginAuth(_email:string, _password:string) {
    const userAuth = this.userService.login(_email, _password)
    .subscribe(user => {
      // En caso de acierto, se crean las cookies para acceder a la aplicacion
      if (_email === user.email && _password === user.password) {
        this.cookieService.set('token', 'login', 2, '', 'localhost', true);
        this.router.navigate(['/']);
        return true;
      } else {
        return false;
      }
    });

    // Si es true te deja seguir, si es false no te deja acceder a su vez
    if (userAuth) {
      return true;
    } else {
      return false;
    }
  }

  // Elimina la cookie y reinicia la pagina
  logoutAuth() {
    this.cookieService.delete('token');
    window.location.reload();
  }

  // Asegura que tenemos la sesion inciada
  isLoggedIn(): boolean {
    if (this.cookieService.check('token') == true) {
      return true;
    } else {
      return false;
    }
  }
}
