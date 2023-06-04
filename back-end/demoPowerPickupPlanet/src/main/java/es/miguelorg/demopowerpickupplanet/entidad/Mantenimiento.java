package es.miguelorg.demopowerpickupplanet.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "mantenimiento")
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mantenimiento;

    public Mantenimiento(String id_mantenimiento) {
        this.id_mantenimiento = Long.valueOf(id_mantenimiento);
    }

    @Future
    @Column(name = "fecha_mantenimiento")
    private LocalDate fecha_mantenimiento;

    // Validación personalizada para la fecha mínima de mantenimiento
    public boolean isFechaMinimaValid() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaMinima = fechaActual.plusMonths(1); // Agregar un mes a la fecha actual
        return fecha_mantenimiento.isAfter(fechaMinima) || fecha_mantenimiento.isEqual(fechaMinima);
    }

    @NotNull
    @Column(name = "tipo_mantenimiento")
    private String tipo_mantenimiento;

    @OneToMany(mappedBy = "mantenimiento", orphanRemoval = false)
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();
}
