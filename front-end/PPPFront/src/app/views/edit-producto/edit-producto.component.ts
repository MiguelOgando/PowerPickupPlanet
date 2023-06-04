import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Instalacion } from 'src/app/shared/interface/instalacion';
import { Mantenimiento } from 'src/app/shared/interface/mantenimiento';
import { Producto } from 'src/app/shared/interface/producto/completeProducto';
import { Marca } from 'src/app/shared/interface/producto/marca';
import { Modelo } from 'src/app/shared/interface/producto/modelo';
import { InstalacionService } from 'src/app/shared/service/instalacion/instalacion.service';
import { MantenimientoService } from 'src/app/shared/service/mantenimiento/mantenimiento.service';
import { ProductoService } from 'src/app/shared/service/producto/producto.service';

@Component({
  selector: 'app-edit-producto',
  templateUrl: './edit-producto.component.html',
  styleUrls: ['./edit-producto.component.css']
})
export class EditProductoComponent implements OnInit {

  // SELECT DEL FORMULARIO
  marcas: Marca[] = [
    { value: 'SP', label: 'SunPower' },
    { value: 'LgS', label: 'LG Solar' },
    { value: 'CS', label: 'Canadian Solar' },
    { value: 'JS', label: 'Jinko Solar' },
    { value: 'TS', label: 'Trina Solar' }
  ];

  // SELECT DEL FORMULARIO
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
  producto!: Producto;

  instalaciones!: Instalacion[];
  mantenimientos!: Mantenimiento[];

  // Recoge el id de la anterior pagina
  id = this.route.snapshot.paramMap.get('id');

  // Recursos usados
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private productoService: ProductoService,
    private instalacionService: InstalacionService,
    private mantenimientoService: MantenimientoService
  ) { }

  // INICIALIZA EL FORMULARIO Y LA VALIDACION
  ngOnInit(): void {
    this.productoForm = this.formBuilder.group({
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      dimension: ['', [Validators.required, Validators.min(1)]],
      cantidad: ['', [Validators.required, Validators.max(100), Validators.min(1)]],
      potencia: ['', [Validators.required, Validators.max(1000), Validators.min(1)]],
      eficiencia: ['', [Validators.required, Validators.max(1), Validators.min(0.1)]],
      mantenimiento: this.formBuilder.group({
        id_mantenimiento: [''],
        fecha_mantenimiento: [''],
        tipo_mantenimiento: ['']
      }),
      instalacion: this.formBuilder.group({
        id_instalacion: [''],
        nombre_instalacion: [''],
        fecha_instalacion: [''],
        orientacion: [''],
        inclinacion: [''],
        sistema_montaje: ['']
      })
    });

    // Busca todo el mantenimiento existente
    this.mantenimientoService.getAllMantenimientos().subscribe(
      (mantenimientos: Mantenimiento[]) => {
        this.mantenimientos = mantenimientos;
      },
      error => {
        console.error('Error al obtener los mantenimientos:', error);
      }
    );

    // Busca toda la instalacion existente
    this.instalacionService.getAllInstalaciones().subscribe(
      (instalaciones: Instalacion[]) => {
        this.instalaciones = instalaciones;
      },
      error => {
        this.productoForm.get('instalacion')?.setValue(null);
      }
    );

    // Busca el id
    if (this.id !== null) {
      const productId = parseInt(this.id);
      this.productoService.obtenerProductoPorId(productId).subscribe(
        producto => {
          this.producto = producto;
          this.productoForm.patchValue(producto);
        }
      );
    }
  }

  guardarCambios() {
    if (this.productoForm.valid) {
      const productoActualizado = this.productoForm.value as Producto;

      // Verificar si se seleccionó una instalación
      if (!productoActualizado.instalacion?.id_instalacion) {
        productoActualizado.instalacion = null;
      }

      // Verificar si se seleccionó un mantenimiento
      if (!productoActualizado.mantenimiento?.id_mantenimiento) {
        productoActualizado.mantenimiento = null;
      }

      // Llama al servicio ProductoService para actualizar el producto en el backend
      this.productoService.actualizarProducto(parseInt(this.id!), productoActualizado).subscribe(
        // Maneja la respuesta o error en caso de actualización exitosa o fallida
        data => {
          this.router.navigate(['/catalogo/viewProducto/' + this.id])
        }
      );
    }
  }
}
