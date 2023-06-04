package es.miguelorg.demopowerpickupplanet.entidad;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private String modelo;

    @NotNull
    @Min(1)
    @Column(name = "dimension")
    private double dimension; // metro cuadrados

    @NotNull
    @Max(100)
    @Min(1)
    @Column(name = "cantidad")
    private int cantidad; // unidad

    @NotNull
    @Max(1000)
    @Min(1)
    @Column(name = "potencia")
    private double potencia; // W

    @NotNull
    @Max(1)
    @DecimalMin(value = "0.1", inclusive = true)
    @Column(name = "eficiencia")
    private double eficiencia; // Tanto por ciento

    @ManyToOne
    @JoinColumn(name = "id_mantenimiento", nullable = true)
    private Mantenimiento mantenimiento;

    @ManyToMany
    @JoinTable(name = "producto_usuarios",
            joinColumns = @JoinColumn(name = "producto_id_producto"),
            inverseJoinColumns = @JoinColumn(name = "usuarios_id_usuario"))
    private Set<Usuario> usuarios = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_instalacion", nullable = true)
    private Instalacion instalacion;
}
