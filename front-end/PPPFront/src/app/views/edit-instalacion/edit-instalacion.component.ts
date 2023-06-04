import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Instalacion } from 'src/app/shared/interface/instalacion';
import { InstalacionService } from 'src/app/shared/service/instalacion/instalacion.service';

@Component({
  selector: 'app-edit-instalacion',
  templateUrl: './edit-instalacion.component.html',
  styleUrls: ['./edit-instalacion.component.css']
})
export class EditInstalacionComponent {

  // VARIABLES
  instalacionForm!: FormGroup;
  instalacion!: Instalacion;

  // Recoge el id de la anterior pagina
  id = this.route.snapshot.paramMap.get('id');

  // Recursos usados
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private instalacionService: InstalacionService
  ) { }

  // INICIALIZA EL FORMULARIO Y LA VALIDACION POR PARTE DEL CLIENTE
  ngOnInit(): void {
    this.instalacionForm = this.formBuilder.group({
      nombre_instalacion: ['', Validators.required],
      fecha_instalacion: ['', [Validators.required, this.fechaActualValidator()]],
      orientacion: ['', [Validators.required, Validators.min(0), Validators.max(360)]],
      inclinacion: ['', [Validators.required, Validators.min(0), Validators.max(90)]],
      sistema_montaje: [true, Validators.required]
    });

    // BUSCA EL ID DEL OBJETO
    if (this.id !== null) {
      const productId = parseInt(this.id);
      this.instalacionService.getInstalacionById(productId).subscribe(
        instalacion => {
          this.instalacion = instalacion;
          this.instalacionForm.patchValue(instalacion);
        }
      );
    }
  }

  // VALIDACION PERSONALIZADA PARA LA FECHA
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

  guardarCambios() {
    if (this.instalacionForm.valid) {
      const instalacionActualizado = this.instalacionForm.value as Instalacion;
      // Llama al servicio instalacionService para actualizar el producto en el backend
      this.instalacionService.updateInstalacion(parseInt(this.id!), instalacionActualizado).subscribe(
        // Maneja la respuesta o error en caso de actualización exitosa o fallida
        data => {
          this.router.navigate(['/instalacion'])
        }
      );
    }
  }
}
