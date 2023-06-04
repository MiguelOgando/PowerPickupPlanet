import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './views/welcome/welcome.component';
import { CatalogoComponent } from './views/catalogo/catalogo.component';
import { MantenimientoComponent } from './views/mantenimiento/mantenimiento.component';
import { InstalacionComponent } from './views/instalacion/instalacion.component';
import { CalculadoraComponent } from './views/calculadora/calculadora.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NewProductoComponent } from './views/new-producto/new-producto.component';
import { ViewProductoComponent } from './views/view-producto/view-producto.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { EditProductoComponent } from './views/edit-producto/edit-producto.component';
import { CookieService } from 'ngx-cookie-service';
import { NewInstalacionComponent } from './views/new-instalacion/new-instalacion.component';
import { NewMantenimientoComponent } from './views/new-mantenimiento/new-mantenimiento.component';
import { EditInstalacionComponent } from './views/edit-instalacion/edit-instalacion.component';
import { EditMantenimientoComponent } from './views/edit-mantenimiento/edit-mantenimiento.component';
import { ErrorComponent } from './views/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    CatalogoComponent,
    MantenimientoComponent,
    InstalacionComponent,
    CalculadoraComponent,
    NewProductoComponent,
    ViewProductoComponent,
    LoginComponent,
    RegisterComponent,
    EditProductoComponent,
    NewInstalacionComponent,
    NewMantenimientoComponent,
    EditInstalacionComponent,
    EditMantenimientoComponent,
    ErrorComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
