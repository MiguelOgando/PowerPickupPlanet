import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/shared/service/seguridad/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // Variables
  public loginForm!: FormGroup;

  mostrarError = false;

  // Recursos usados
  constructor (
    private authService: AuthService,
    private fb: FormBuilder
  ) {}

  // Inicializa el formulario
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [''],
      password: ['']
    })
  }

  email!: string;
  password!: string;

  login() {
    const succes = this.authService.loginAuth(this.email, this.password);
    if (!succes) {
      this.mostrarError = true;
    } else {
      this.mostrarError = true;
    }
  }

  logout() {
    this.authService.logoutAuth();
    window.location.reload();
  }
}
