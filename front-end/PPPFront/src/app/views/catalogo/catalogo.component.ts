import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/shared/interface/producto/completeProducto';
import { ListProducto } from 'src/app/shared/interface/producto/listProducto';
import { ProductoService } from 'src/app/shared/service/producto/producto.service';

// INTERFAZ PARA LAS IMAGENES
interface Imagenes {
  [key: string]: string;
}

@Component({
  selector: 'app-catalogo',
  templateUrl: './catalogo.component.html',
  styleUrls: ['./catalogo.component.css']
})
export class CatalogoComponent implements OnInit {

  // VARIABLES
  productos!: ListProducto[];
  selectedProducto: Producto | null = null;

  // Recursos usados
  constructor(
    private productoService: ProductoService
  ) { }

  // INICIALIZA EL CATALOGO
  ngOnInit() {
    this.productoService.obtenerTodosLosProductos().subscribe(
      (data: any) => {
        this.productos = data;
        console.log(this.productos);
      },
      (error: HttpErrorResponse) => {
        console.error('Error occurred:', error);
      }
    );
  }

  // ELIMINA LOS PRODUCTOS
  eliminarProducto(id: number) {
    // Mostrar aviso de confirmación
    const confirmacion = window.confirm('¿Estás seguro de que deseas eliminar el producto?');
    if (confirmacion) {
      // Lógica para eliminar el producto utilizando el servicio ProductoService
      this.productoService.eliminarProductoPorId(id).subscribe(
        () => {
          // Eliminación exitosa, refrescar la página
          window.location.reload();
        },
        error => {

        }
      );
    }
  }

  // IMAGENES
  imageNames: Imagenes  = {
    "All-Black": "assets/img/paneles/All-Black.jpg",
    "Cheetah": "assets/img/paneles/Cheetah.jpg",
    "DuoMax": "assets/img/paneles/DuoMax.jpg",
    "Eagle": "assets/img/paneles/Eagle.jpg",
    "E-Series": "assets/img/paneles/E-Series.jpg",
    "HiDM": "assets/img/paneles/HiDM.jpg",
    "Honey": "assets/img/paneles/Honey.jpg",
    "KuPower": "assets/img/paneles/KuPower.jpg",
    "Maxeon 2": "assets/img/paneles/Maxeon 2.jpg",
    "Maxeon 3": "assets/img/paneles/Maxeon 3.jpg",
    "Mono X NeON": "assets/img/paneles/Mono X NeON.jpg",
    "Mono X Plus": "assets/img/paneles/Mono X Plus.jpg",
    "Neon 2": "assets/img/paneles/Neon 2.jpg",
    "Neon R": "assets/img/paneles/Neon R.jpg",
    "Performance": "assets/img/paneles/Performance.jpg",
    "SuperPower": "assets/img/paneles/SuperPower.jpg",
    "Swan": "assets/img/paneles/Swan.jpg",
    "TallMax": "assets/img/paneles/TallMax.jpg",
    "Tiger": "assets/img/paneles/Tiger.jpg",
    "Vertex": "assets/img/paneles/Vertex.jpg"
  };

  selectedImage!: string;

  selectImage(imageName: string) {
    this.selectedImage = imageName;
  }

  getImageUrl(modelo: string): string {
    if (modelo in this.imageNames) {
      return this.imageNames[modelo];
    } else {
      return 'assets/img/paneles/default.png'; // Ruta de imagen por defecto si no se encuentra el modelo en el mapeo
    }
  }
}
