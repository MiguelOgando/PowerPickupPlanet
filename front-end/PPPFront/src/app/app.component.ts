import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from './shared/service/seguridad/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'PPP_front';
  constructor(private authService: AuthService, private cookieService: CookieService) {}

  // Nos permite enseñar lo que necesitemos de la pagina si estas o no logueado
  isLoggedIn(): boolean {
    return this.cookieService.check('token');
  }

  // Nos permite cerrar sesion
  logout() {
    this.authService.logoutAuth();
  }
}
