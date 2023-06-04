import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Mantenimiento } from 'src/app/shared/interface/mantenimiento';
import { MantenimientoService } from 'src/app/shared/service/mantenimiento/mantenimiento.service';

@Component({
  selector: 'app-new-mantenimiento',
  templateUrl: './new-mantenimiento.component.html',
  styleUrls: ['./new-mantenimiento.component.css']
})
export class NewMantenimientoComponent implements OnInit {

  // VARIBALES
  mantenimientoForm!: FormGroup;

  // Recursos usados
  constructor(
    private mantenimientoService: MantenimientoService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  // INICIALIZA EL FORMULARIO Y LA VALIDACION
  ngOnInit(): void {
    this.mantenimientoForm = this.fb.group({
      tipo_mantenimiento: ['', Validators.required],
      fecha_mantenimiento: ['', [Validators.required, this.validateDate]]
    });
  }

  // VALIDACION PERSONALIZADA PARA LA FECHA
  validateDate(control: AbstractControl): { [key: string]: boolean } | null {
    const inputDate = new Date(control.value);
    const currentDate = new Date();
    currentDate.setMonth(currentDate.getMonth() + 1); // Agregar un mes a la fecha actual

    if (inputDate < currentDate) {
      return { 'invalidDate': true };
    }

    return null;
  }

  guardarMantenimiento() {
    if (this.mantenimientoForm.valid) {
      const mantenimiento: Mantenimiento = this.mantenimientoForm.value;
      this.mantenimientoService.addMantenimiento(mantenimiento)
        .subscribe(
          nuevoMantenimiento => {
            console.log('Mantenimiento creado:', nuevoMantenimiento);
            // Realizar cualquier otra acción necesaria después de crear el mantenimiento
            this.router.navigate(['/mantenimiento']);
          },
          error => {
            console.error('Error al crear el mantenimiento:', error);
            // Realizar cualquier acción de manejo de errores necesaria
          }
        );
    } else {
      console.log('El formulario no es válido');
    }
  }
}
