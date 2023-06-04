import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Instalacion } from 'src/app/shared/interface/instalacion';
import { InstalacionService } from 'src/app/shared/service/instalacion/instalacion.service';

@Component({
  selector: 'app-new-instalacion',
  templateUrl: './new-instalacion.component.html',
  styleUrls: ['./new-instalacion.component.css']
})
export class NewInstalacionComponent {

  // VARIABLES
  instalacionForm!: FormGroup;
  instalacion!: Instalacion;

  // Recursos usados
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private instalacionService: InstalacionService
  ) { }

  // INICIALIZA EL FORMULARIO Y LA VALIDACION
  ngOnInit(): void {
    this.instalacionForm = this.formBuilder.group({
      nombre_instalacion: ['', Validators.required],
      fecha_instalacion: ['', [Validators.required, this.fechaActualValidator()]],
      orientacion: [0, [Validators.required, Validators.min(0), Validators.max(360)]],
      inclinacion: [0, [Validators.required, Validators.min(0), Validators.max(90)]],
      sistema_montaje: [true, Validators.required]
    });
  }

  // Valiacion personalizada para el campo de fecha
  fechaActualValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const fechaSeleccionada = new Date(control.value);
      const fechaActual = new Date();

      if (fechaSeleccionada < fechaActual) {
        return { fechaMenor: true }; // Retorna un objeto con una propiedad personalizada para indicar el error
      }

      return null; // Retorna null si no hay error
    };
  }

  guardarInstalacion() {
    if (this.instalacionForm.valid) {
      const instalacion: Instalacion = this.instalacionForm.value;
      this.instalacionService.addInstalacion(instalacion)
        .subscribe(
          nuevoinstalacion => {
            console.log('Instalacion creado:', nuevoinstalacion);
            // Realizar cualquier otra acción necesaria después de crear la instalacion
            this.router.navigate(['/instalacion']);
          },
          error => {
            console.error('Error al crear la instalacion:', error);
            // Realizar cualquier acción de manejo de errores necesaria
          }
        );
    } else {
      console.log('El formulario no es válido');
    }
  }
}
