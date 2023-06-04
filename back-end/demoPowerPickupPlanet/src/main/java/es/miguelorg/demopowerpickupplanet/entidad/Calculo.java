package es.miguelorg.demopowerpickupplanet.entidad;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "calculo")
public class Calculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_calculo;

    @NotNull
    @Max(1000)
    @Min(1)
    @Column(name = "potencia")
    private double potencia; // vatios (W)

    @NotNull
    @Max(1)
    @DecimalMin(value = "0.1", inclusive = true)
    @Column(name = "eficiencia")
    private double eficiencia; // porcentaje %

    @NotNull
    @Max(24)
    @Min(1)
    @Column(name = "horas_sol_dia")
    private int horasSolDia; // horas

    @NotNull
    @Max(1)
    @DecimalMin(value = "0.1", inclusive = true)
    @Column(name = "costo_electricidad")
    private double costoElectricidad; // Kwh

    @NotNull
    @Min(1)
    @Column(name = "dimension")
    private double dimension; // metros cuadrados m^2

    @NotNull
    @Max(100)
    @Min(1)
    @Column(name = "cantidad")
    private int cantidad; // unidad

    @Column(name = "ahorro_energia")
    private double ahorroEnergia; // Kwh

    @Column(name = "ahorro_dinero")
    private double ahorroDinero; // Euros/Dolares ya que es aproximado
}
