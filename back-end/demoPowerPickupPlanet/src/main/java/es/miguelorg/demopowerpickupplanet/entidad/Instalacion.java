package es.miguelorg.demopowerpickupplanet.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "instalacion")
public class Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_instalacion;

    // Permite que se lea en el formulario del cliente
    public Instalacion(String id_instalacion) {
        this.id_instalacion = Long.valueOf(id_instalacion);
    }

    @NotNull
    @Column(name = "nombre_instalacion")
    private String nombre_instalacion;

    @NotNull
    @FutureOrPresent
    @Column(name = "fecha_instalacion")
    private LocalDate fecha_instalacion;

    @NotNull
    @Min(0)
    @Max(360)
    @Column(name = "orientacion")
    private double orientacion; // En grados

    @NotNull
    @Min(0)
    @Max(90)
    @Column(name = "inclinacion")
    private double inclinacion; // En grados

    @NotNull
    @Column(name = "sistema_montaje")
    private boolean sistema_montaje;

    @OneToMany(mappedBy = "instalacion", orphanRemoval = false)
    @JsonIgnore
    private List<Producto> productos = new ArrayList<>();

}
