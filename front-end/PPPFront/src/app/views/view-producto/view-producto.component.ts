import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Producto } from 'src/app/shared/interface/producto/completeProducto';
import { ProductoService } from 'src/app/shared/service/producto/producto.service';

// SELECCION DE IMAGENES
interface Imagenes {
  [key: string]: string;
}

@Component({
  selector: 'app-view-producto',
  templateUrl: './view-producto.component.html',
  styleUrls: ['./view-producto.component.css']
})
export class ViewProductoComponent implements OnInit {

  // VARIABLES
  producto!: Producto;
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

  // Recursos usados
  constructor(
    private route: ActivatedRoute,
    private productoService: ProductoService
  ) { }

  // INICIALIZA EL PRODUCTO SELECCIONADO
  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id !== null) {
      const productId = parseInt(id);
      this.productoService.obtenerProductoPorId(productId).subscribe(
        producto => this.producto = producto
      );
    }
  }

  getImageUrl(modelo: string): string {
    if (modelo in this.imageNames) {
      return this.imageNames[modelo];
    } else {
      return 'assets/img/paneles/default.jpg'; // Ruta de imagen por defecto si no se encuentra el modelo en el mapeo
    }
  }
}
