import { WelcomeComponent } from './views/welcome/welcome.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MantenimientoComponent } from './views/mantenimiento/mantenimiento.component';
import { InstalacionComponent } from './views/instalacion/instalacion.component';
import { CalculadoraComponent } from './views/calculadora/calculadora.component';
import { CatalogoComponent } from './views/catalogo/catalogo.component';
import { NewProductoComponent } from './views/new-producto/new-producto.component';
import { ViewProductoComponent } from './views/view-producto/view-producto.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { EditProductoComponent } from './views/edit-producto/edit-producto.component';
import { AuthGuard } from './shared/service/seguridad/auth.guard';
import { EditInstalacionComponent } from './views/edit-instalacion/edit-instalacion.component';
import { EditMantenimientoComponent } from './views/edit-mantenimiento/edit-mantenimiento.component';
import { NewMantenimientoComponent } from './views/new-mantenimiento/new-mantenimiento.component';
import { NewInstalacionComponent } from './views/new-instalacion/new-instalacion.component';
import { ErrorComponent } from './views/error/error.component';

// Rutas de navegacion de la aplicacion
const routes: Routes = [
  {path: 'login', component:LoginComponent},
  {path: '', component:WelcomeComponent},
  {path: 'register', component:RegisterComponent, canActivate: [AuthGuard]},
  {path: 'mantenimiento', component:MantenimientoComponent, canActivate: [AuthGuard]},
  {path: 'mantenimiento/editMantenimiento/:id', component:EditMantenimientoComponent, canActivate: [AuthGuard]},
  {path: 'mantenimiento/addMantenimiento', component:NewMantenimientoComponent, canActivate: [AuthGuard]},
  {path: 'instalacion', component:InstalacionComponent, canActivate: [AuthGuard]},
  {path: 'instalacion/editInstalacion/:id', component:EditInstalacionComponent, canActivate: [AuthGuard]},
  {path: 'instalacion/addInstalacion', component:NewInstalacionComponent, canActivate: [AuthGuard]},
  {path: 'calculadora', component:CalculadoraComponent, canActivate: [AuthGuard]},
  {path: 'catalogo', component:CatalogoComponent, canActivate: [AuthGuard]},
  {path: 'catalogo/addProducto', component:NewProductoComponent, canActivate: [AuthGuard]},
  {path: 'catalogo/viewProducto/:id', component: ViewProductoComponent, canActivate: [AuthGuard]},
  {path: 'catalogo/editProducto/:id', component: EditProductoComponent, canActivate: [AuthGuard]},
  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
