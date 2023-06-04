import { Component } from '@angular/core';
import { Instalacion } from 'src/app/shared/interface/instalacion';
import { InstalacionService } from 'src/app/shared/service/instalacion/instalacion.service';

@Component({
  selector: 'app-instalacion',
  templateUrl: './instalacion.component.html',
  styleUrls: ['./instalacion.component.css']
})
export class InstalacionComponent {
  // VARIABLES
  instalaciones!: Instalacion[];

  instalacion!: Instalacion;

  // Recursos usados
  constructor(private instalacionService: InstalacionService) { }

  // INICIALIZA TODAS LAS INSTALACIONES
  ngOnInit() {
    this.getAllInstalaciones();
  }

  getAllInstalaciones() {
    this.instalacionService.getAllInstalaciones().subscribe(
      data => {
        this.instalaciones = data;
        console.log(this.instalaciones);
      },
      error => {
        console.error('Ocurrió un error al obtener las instalaciones:', error);
      }
    );
  }

  eliminarInstalacion(id: number) {
    // Mostrar aviso de confirmación
    const confirmacion = window.confirm('¿Estás seguro de que deseas eliminar la instalación?');
    if (confirmacion) {
      // Lógica para eliminar la instalacion utilizando el servicio InstalacionService
      this.instalacionService.deleteInstalacion(id).subscribe(
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
    this.instalacionService.getInstalacionById(id).subscribe(
      (data: Instalacion) => {
        this.instalacion = data;
      },
      (error) => {
        console.error('Ocurrió un error al obtener la instalación:', error);
      }
    );
  }

}
