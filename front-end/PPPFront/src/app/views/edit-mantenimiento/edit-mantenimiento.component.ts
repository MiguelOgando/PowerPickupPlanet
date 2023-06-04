import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Mantenimiento } from 'src/app/shared/interface/mantenimiento';
import { MantenimientoService } from 'src/app/shared/service/mantenimiento/mantenimiento.service';

@Component({
  selector: 'app-edit-mantenimiento',
  templateUrl: './edit-mantenimiento.component.html',
  styleUrls: ['./edit-mantenimiento.component.css']
})
export class EditMantenimientoComponent {

  // VARIABLES
  mantenimientoForm!: FormGroup;
  mantenimiento!: Mantenimiento;

  // Recoge el id de la anterior pagina
  id = this.route.snapshot.paramMap.get('id');

  // Recursos usados
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private mantenimientoService: MantenimientoService
  ) { }

  // INICIALIZA EL FORMULARIO Y LA VALIACION
  ngOnInit(): void {
    this.mantenimientoForm = this.formBuilder.group({
      tipo_mantenimiento: ['', Validators.required],
      fecha_mantenimiento: ['', [Validators.required, this.validateDate]]
    });

    // BUSCA EL ID ACTUAL DEL OBJETO
    if (this.id !== null) {
      const productId = parseInt(this.id);
      this.mantenimientoService.getMantenimientoById(productId).subscribe(
        mantenimiento => {
          this.mantenimiento = mantenimiento;
          this.mantenimientoForm.patchValue(mantenimiento);
        }
      );
    }
  }

  // PERSONALIZACION DE LA FECHA
  validateDate(control: AbstractControl): { [key: string]: boolean } | null {
    const inputDate = new Date(control.value);
    const currentDate = new Date();
    currentDate.setMonth(currentDate.getMonth() + 1); // Agregar un mes a la fecha actual

    if (inputDate < currentDate) {
      return { 'invalidDate': true };
    }

    return null;
  }

  guardarCambios() {
    if (this.mantenimientoForm.valid) {
      const instalacionActualizado = this.mantenimientoForm.value as Mantenimiento;
      // Llama al servicio mantenimientoService para actualizar el producto en el backend
      this.mantenimientoService.updateMantenimiento(parseInt(this.id!), instalacionActualizado).subscribe(
        // Maneja la respuesta o error en caso de actualización exitosa o fallida
        data => {
          this.router.navigate(['/mantenimiento'])
        }
      );
    }
  }
}
