import { Instalacion } from "../instalacion";
import { Mantenimiento } from "../mantenimiento";

export interface NewProducto {
  id_producto: number;
  marca: string;
  modelo: string;
  dimension: number;
  cantidad: number;
  potencia: number;
  eficiencia: number;
  instalacion: Instalacion | null | '';
  mantenimiento: Mantenimiento | null | '';
}
