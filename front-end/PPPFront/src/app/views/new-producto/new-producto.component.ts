import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Instalacion } from 'src/app/shared/interface/instalacion';
import { Mantenimiento } from 'src/app/shared/interface/mantenimiento';
import { Marca } from 'src/app/shared/interface/producto/marca';
import { Modelo } from 'src/app/shared/interface/producto/modelo';
import { NewProducto } from 'src/app/shared/interface/producto/newProducto';
import { InstalacionService } from 'src/app/shared/service/instalacion/instalacion.service';
import { MantenimientoService } from 'src/app/shared/service/mantenimiento/mantenimiento.service';
import { ProductoService } from 'src/app/shared/service/producto/producto.service';

@Component({
  selector: 'app-new-producto',
  templateUrl: './new-producto.component.html',
  styleUrls: ['./new-producto.component.css']
})
export class NewProductoComponent implements OnInit {

  // SELECT DE FORMULARIO
  marcas: Marca[] = [
    { value: 'SP', label: 'SunPower' },
    { value: 'LgS', label: 'LG Solar' },
    { value: 'CS', label: 'Canadian Solar' },
    { value: 'JS', label: 'Jinko Solar' },
    { value: 'TS', label: 'Trina Solar' }
  ];

  // SELECT DE FORMULARIO
  modelos: Modelo[] = [
    {
      marca: 'SP',
      modelos: [
        'Maxeon 3',
        'Maxeon 2',
        'Performance',
        'E-Series'
      ]
    },
    {
      marca: 'LgS',
      modelos: [
        'Neon 2',
        'Neon R',
        'Mono X Plus',
        'Mono X NeON'
      ]
    },
    {
      marca: 'CS',
      modelos: [
        'KuPower',
        'HiDM',
        'SuperPower',
        'All-Black'
      ]
    },
    {
      marca: 'JS',
      modelos: [
        'Eagle',
        'Cheetah',
        'Swan',
        'Tiger'
      ]
    },
    {
      marca: 'TS',
      modelos: [
        'Honey',
        'TallMax',
        'DuoMax',
        'Vertex'
      ]
    }
  ];

  // VARIABLES
  productoForm!: FormGroup;
  newProducto!: NewProducto;

  instalaciones!: Instalacion[];
  mantenimientos!: Mantenimiento[];

  // Recursos usados
  constructor(
    private productoService: ProductoService,
    private fb: FormBuilder,
    private instalacionService: InstalacionService,
    private mantenimientoService: MantenimientoService,
    private router: Router
  ) { }

  // INICIALIZA EL FORMULARIO Y LA VALIDACION
  ngOnInit(): void {
    this.productoForm = this.fb.group({
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      dimension: [1, [Validators.required, Validators.min(1)]],
      cantidad: [1, [Validators.required, Validators.max(100), Validators.min(1)]],
      potencia: [1, [Validators.required, Validators.max(1000), Validators.min(1)]],
      eficiencia: [0.1, [Validators.required, Validators.max(1), Validators.min(0.1)]],
      mantenimiento: [''],
      instalacion: ['']
    });

    // Para poder realizar el select en el formulario
    this.mantenimientoService.getAllMantenimientos().subscribe(
      (mantenimientos: Mantenimiento[]) => {
        this.mantenimientos = mantenimientos;
      },
      error => {
        console.error('Error al obtener los mantenimientos:', error);
      }
    );

    // Para poder realizar el select en el formulario
    this.instalacionService.getAllInstalaciones().subscribe(
      (instalaciones: Instalacion[]) => {
        this.instalaciones = instalaciones;
      },
      error => {
        console.error('Error al obtener las instalaciones:', error);
      }
    );
  }

  guardarProducto() {
    if (this.productoForm.valid) {
      const producto = this.productoForm.value as NewProducto;

      // Si no se elige ninguna opcion en el formulario se da como nulo
      if (producto.mantenimiento === "") {
        producto.mantenimiento = null;
      }
      // Si no se elige ninguna opcion en el formulario se da como nulo
      if (producto.instalacion === "") {
        producto.instalacion = null;
      }

      this.productoService.crearProducto(producto)
        .subscribe(
          nuevoProducto => {
            // Realizar cualquier otra acción necesaria después de crear el producto
            this.router.navigate(['/catalogo']);
          },
          error => {
            console.error('Error al crear el producto:', error);
            // Realizar cualquier acción de manejo de errores necesaria
          }
        );
    } else {
      console.log('El formulario no es válido');
    }
  }
}
