import { Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Calculo } from 'src/app/shared/interface/calculadora';
import { CalculadoraService } from 'src/app/shared/service/calculadora/calculadora.service';

declare var google: any;

@Component({
  selector: 'app-calculadora',
  templateUrl: './calculadora.component.html',
  styleUrls: ['./calculadora.component.css']
})
export class CalculadoraComponent implements OnInit {
  @ViewChild('map', { static: true }) mapElement!: ElementRef; // VARIABLE PARA EL USO DEL MAPS

  // VARIABLES
  form!: FormGroup; // CREACION DEL FORMULARIO
  resultado!: Calculo; // INTERFAZ DE CALCULO
  map: any; // VARIABLE PARA EL USO DEL MAPA

  // Recursos usados
  constructor(private fb: FormBuilder, private calculoService: CalculadoraService) { }

  // FORMULARIO CON VALIDACIONES POR PARTE DEL CLIENTE
  ngOnInit() {
    this.form = this.fb.group({
      potencia: [1, [Validators.required, Validators.max(1000), Validators.min(1)]],
      eficiencia: [0.1, [Validators.required, Validators.max(1), Validators.min(0.1)]],
      horasSolDia: [1, [Validators.required, Validators.max(24), Validators.min(1)]],
      costoElectricidad: [0.1, [Validators.required, Validators.max(1), Validators.min(0.1)]],
      dimension: [1, [Validators.required, Validators.min(1)]],
      cantidad: [1, [Validators.required, Validators.max(100), Validators.min(1)]],
    });

    this.initializeMap();
  }

  // INICIALIZA EL MAPA
  initializeMap() {
    // UBICACION INICIAL
    const mapOptions = {
      center: { lat: 44.5452, lng: -78.5389 },
      zoom: 9,
    };

    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

    // PUNTOS DEL CUADRADO
    const bounds = {
      north: 44.599,
      south: 44.49,
      east: -78.443,
      west: -78.649,
    };

    const rectangle = new google.maps.Rectangle({
      bounds: bounds,
      editable: true,
      draggable: true,
    });

    rectangle.setMap(this.map);

    rectangle.addListener("bounds_changed", () => {
      this.showNewRect(rectangle.getBounds());
    });
  }

  // REALIZACION DEL CALCULO PARA REDIMENSIONAR A DIMENSION DEL FORMULARIO
  showNewRect(bounds: any) {
    const projection = this.map.getProjection();
    const ne = bounds.getNorthEast();
    const sw = bounds.getSouthWest();

    const neWorldCoord = projection.fromLatLngToPoint(ne);
    const swWorldCoord = projection.fromLatLngToPoint(sw);

    // Renderiza la superficie de la tierra a escala plana
    const widthMeters = Math.abs(neWorldCoord.x - swWorldCoord.x) * 40075000 / 360;
    const heightMeters = Math.abs(neWorldCoord.y - swWorldCoord.y) * 40075000 / 360;

    const dimension = widthMeters * heightMeters;
    this.form.get('dimension')?.setValue(dimension);
  }

  // ENVÍA LAS VARIABLES Y MUESTRA EL RESULTADO
  onSubmit() {
    const calculo: Calculo = {
      potencia: this.form.get('potencia')?.value,
      eficiencia: this.form.get('eficiencia')?.value,
      horasSolDia: this.form.get('horasSolDia')?.value,
      costoElectricidad: this.form.get('costoElectricidad')?.value,
      dimension: this.form.get('dimension')?.value,
      cantidad: this.form.get('cantidad')?.value,
      ahorroEnergia: 0,
      ahorroDinero: 0
    };

    this.calculoService.calcularAhorroEnergiaYDinero(calculo).subscribe(res => {
      this.resultado = res;
    });
  }
}

