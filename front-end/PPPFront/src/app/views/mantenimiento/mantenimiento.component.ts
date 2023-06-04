import { Component } from '@angular/core';
import { Mantenimiento } from 'src/app/shared/interface/mantenimiento';
import { MantenimientoService } from 'src/app/shared/service/mantenimiento/mantenimiento.service';

@Component({
  selector: 'app-mantenimiento',
  templateUrl: './mantenimiento.component.html',
  styleUrls: ['./mantenimiento.component.css']
})
export class MantenimientoComponent {

  // VARIABLES
  mantenimientos!: Mantenimiento[];
  mantenimiento!: Mantenimiento;

  // Recursos usados
  constructor(private mantenimientoService: MantenimientoService) { }

  // INICIALIZA LOS MANTENIENTOS
  ngOnInit() {
    this.getAllMantenimientos();
  }

  getAllMantenimientos() {
    this.mantenimientoService.getAllMantenimientos().subscribe(
      data => {
        this.mantenimientos = data;
        console.log(this.mantenimientos);
      },
      error => {
        console.error('Ocurrió un error al obtener las instalaciones:', error);
      }
    );
  }

  eliminarMantenimiento(id: number) {
    // Mostrar aviso de confirmación
    const confirmacion = window.confirm('¿Estás seguro de que deseas eliminar el mantenimiento?');
    if (confirmacion) {
      // Lógica para eliminar el mantenimiento utilizando el servicio MantenimientoService
      this.mantenimientoService.deleteMantenimiento(id).subscribe(
        () => {
          // Eliminación exitosa, refrescar la página
          window.location.reload();
        },
        error => {
          // Manejo de error en caso de falla en la eliminación
        }
      );
    }
  }

  getInstalacionById(id:number) {
    this.mantenimientoService.getMantenimientoById(id).subscribe(
      (data: Mantenimiento) => {
        this.mantenimiento = data;
      },
      (error) => {
        console.error('Ocurrió un error al obtener la instalación:', error);
      }
    );
  }
}
