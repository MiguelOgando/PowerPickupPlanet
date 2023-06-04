package es.miguelorg.demopowerpickupplanet.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id_usuario;

    @Column(name = "DNI")
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "El DNI debe tener un formato válido")
    private String dni;

    @Column(name = "nombre")
    private String nombre_usuario;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "tarjeta")
    @Pattern(regexp = "(\\d{4}\\s){3}\\d{4}", message = "Debe ser un número de tarjeta de crédito/débito válido")
    private String tarjeta;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @ManyToMany(mappedBy = "usuarios")
    @JsonIgnore
    private Set<Producto> productos = new LinkedHashSet<>();

}
