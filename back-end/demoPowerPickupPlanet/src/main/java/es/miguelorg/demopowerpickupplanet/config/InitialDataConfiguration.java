package es.miguelorg.demopowerpickupplanet.config;

import es.miguelorg.demopowerpickupplanet.entidad.*;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.InstalacionService;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.MantenimientoService;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.ProductoService;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableJpaAuditing
public class InitialDataConfiguration {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MantenimientoService mantenimientoService;

    @Autowired
    private InstalacionService instalacionService;


    @Bean
    public Marca initMarca() {
        Map<String, String> marca = Map.of(
                "SP", "SunPower",
                "LgS", "LG Solar",
                "CS", "Canadian Solar",
                "JS", "Jinko Solar",
                "TS", "Trina Solar"
        );
        return new Marca(marca);
    }

    @Bean
    public Modelo initModelo() {
        Map<String, String[]> modelo = new LinkedHashMap<>();
        modelo.put("SP", new String[]{"Maxeon 3", "Maxeon 2", "Performance", "E-Series"});
        modelo.put("LgS", new String[]{"Neon 2", "Neon R", "Mono X Plus", "Mono X NeON"});
        modelo.put("CS", new String[]{"KuPower", "HiDM", "SuperPower", "All-Black"});
        modelo.put("JS", new String[]{"Eagle", "Cheetah", "Swan", "Tiger"});
        modelo.put("TS", new String[]{"Honey", "TallMax", "DuoMax", "Vertex"});
        return new Modelo(modelo);
    }


    @PostConstruct
    public void initUsuarios() {
        usuarioService.deleteAll();

        log.info("ALTA A LOS USUARIOS");
        Usuario u1 = Usuario.builder()
                .dni("12345678K")
                .nombre_usuario("Carlos")
                .apellido("Carlos")
                .email("carlos@gmail.com")
                .password("user")
                .tarjeta("1234 1234 1234 1234")
                .roles("ROLE_ADMIN")
                .build();
        usuarioService.save(u1);

        Usuario u2 = Usuario.builder()
                .dni("82745193U")
                .nombre_usuario("Ana")
                .apellido("Ana")
                .email("ana@gmail.com")
                .password("user")
                .tarjeta("9382 8472 1234 4321")
                .roles("ROLE_ADMIN")
                .build();
        usuarioService.save(u2);

        Usuario u3 = Usuario.builder()
                .dni("87654321P")
                .nombre_usuario("Juanjo")
                .apellido("Juanjo")
                .email("juanjo@gmail.com")
                .password("user")
                .tarjeta("4873 8172 8392 1293")
                .roles("ROLE_ADMIN")
                .build();
        usuarioService.save(u3);

        Usuario u4 = Usuario.builder()
                .dni("60011992A")
                .nombre_usuario("Miguel")
                .apellido("Miguel")
                .email("miguel@gmail.com")
                .password("user")
                .tarjeta("5203 2746 0001 0129")
                .roles("ROLE_ADMIN")
                .build();
        usuarioService.save(u4);
    }



    @PostConstruct
    public void initProductos() {

        productoService.deleteAll();
        mantenimientoService.deleteAll();
        instalacionService.deleteAll();

        log.info("ALTA A LA INSTALACIÓN");
        Instalacion i1 = Instalacion.builder()
                .nombre_instalacion("Inicial")
                .fecha_instalacion(LocalDate.of(2024, 11, 3))
                .orientacion(90.0)
                .inclinacion(90.0)
                .sistema_montaje(true)
                .build();
        instalacionService.save(i1);
        Instalacion i2 = Instalacion.builder()
                .nombre_instalacion("Premium")
                .fecha_instalacion(LocalDate.of(2025, 7, 14))
                .orientacion(45.0)
                .inclinacion(45.0)
                .sistema_montaje(true)
                .build();
        instalacionService.save(i2);
        Instalacion i3 = Instalacion.builder()
                .nombre_instalacion("Dinámica")
                .fecha_instalacion(LocalDate.of(2026, 6, 25))
                .orientacion(25.0)
                .inclinacion(90.0)
                .sistema_montaje(true)
                .build();
        instalacionService.save(i3);

        log.info("ALTA AL MANTENIMIENTO");
        Mantenimiento m1 = Mantenimiento.builder()
                .fecha_mantenimiento(LocalDate.of(2025, 2, 2))
                .tipo_mantenimiento("Premium")
                .build();
        mantenimientoService.save(m1);

        Mantenimiento m2 = Mantenimiento.builder()
                .fecha_mantenimiento(LocalDate.of(2030, 1, 5))
                .tipo_mantenimiento("Estándar")
                .build();
        mantenimientoService.save(m2);

        Mantenimiento m3 = Mantenimiento.builder()
                .fecha_mantenimiento(LocalDate.of(2050, 12, 12))
                .tipo_mantenimiento("Interestelar")
                .build();
        mantenimientoService.save(m3);

        log.info("ALTA A LOS PRODUCTOS");
        Producto p1 = Producto.builder()
                .marca("SunPower")
                .modelo("Maxeon 3")
                .dimension(170.0)
                .cantidad(4)
                .potencia(55.5)
                .eficiencia(0.2)
                .instalacion(i3)
                .mantenimiento(m2)
                .build();
        productoService.save(p1);
        Producto p2 = Producto.builder()
                .marca("SunPower")
                .modelo("Maxeon 2")
                .dimension(80.0)
                .cantidad(1)
                .potencia(45.5)
                .eficiencia(0.1)
                .instalacion(i1)
                .mantenimiento(m3)
                .build();
        productoService.save(p2);
        Producto p3 = Producto.builder()
                .marca("SunPower")
                .modelo("Performance")
                .dimension(100.0)
                .cantidad(100)
                .potencia(75)
                .eficiencia(0.45)
                .instalacion(i2)
                .mantenimiento(m1)
                .build();
        productoService.save(p3);
        Producto p4 = Producto.builder()
                .marca("SunPower")
                .modelo("E-Series")
                .dimension(210.0)
                .cantidad(6)
                .potencia(75.0)
                .eficiencia(0.226)
                .instalacion(i1)
                .mantenimiento(m1)
                .build();
        productoService.save(p4);
        Producto p5 = Producto.builder()
                .marca("LG Solar")
                .modelo("Neon 2")
                .dimension(170.0)
                .cantidad(10)
                .potencia(45.0)
                .eficiencia(0.193)
                .instalacion(i2)
                .mantenimiento(m3)
                .build();
        productoService.save(p5);
        Producto p6 = Producto.builder()
                .marca("LG Solar")
                .modelo("Neon R")
                .dimension(180.0)
                .cantidad(12)
                .potencia(55.0)
                .eficiencia(0.198)
                .mantenimiento(m2)
                .build();
        productoService.save(p6);
        Producto p7 = Producto.builder()
                .marca("LG Solar")
                .modelo("Mono X Plus")
                .dimension(200.0)
                .cantidad(8)
                .potencia(60.0)
                .eficiencia(0.204)
                .mantenimiento(m1)
                .build();
        productoService.save(p7);
        Producto p8 = Producto.builder()
                .marca("LG Solar")
                .modelo("Mono X NeON")
                .dimension(190.0)
                .cantidad(10)
                .potencia(50.0)
                .eficiencia(0.18)
                .instalacion(i3)
                .mantenimiento(m3)
                .build();
        productoService.save(p8);
        Producto p9 = Producto.builder()
                .marca("Canadian Solar")
                .modelo("KuPower")
                .dimension(170.0)
                .cantidad(4)
                .potencia(60.0)
                .eficiencia(0.22)
                .instalacion(i1)
                .mantenimiento(m1)
                .build();
        productoService.save(p9);
        Producto p10 = Producto.builder()
                .marca("Canadian Solar")
                .modelo("HiDM")
                .dimension(180.0)
                .cantidad(8)
                .potencia(72.0)
                .eficiencia(0.19)
                .instalacion(i2)
                .mantenimiento(m3)
                .build();
        productoService.save(p10);
        Producto p11 = Producto.builder()
                .marca("Canadian Solar")
                .modelo("SuperPower")
                .dimension(165.0)
                .cantidad(12)
                .potencia(45.0)
                .eficiencia(0.185)
                .instalacion(i3)
                .mantenimiento(m2)
                .build();
        productoService.save(p11);
        Producto p12 = Producto.builder()
                .marca("Canadian Solar")
                .modelo("All-Black")
                .dimension(180.0)
                .cantidad(8)
                .potencia(72.5)
                .eficiencia(0.197)
                .build();
        productoService.save(p12);
        Producto p13 = Producto.builder()
                .marca("Jinko Solar")
                .modelo("Eagle")
                .dimension(160.0)
                .cantidad(6)
                .potencia(50.0)
                .eficiencia(0.195)
                .build();
        productoService.save(p13);
        Producto p14 = Producto.builder()
                .marca("Jinko Solar")
                .modelo("Cheetah")
                .dimension(185.0)
                .cantidad(12)
                .potencia(72.5)
                .eficiencia(0.191)
                .mantenimiento(m2)
                .build();
        productoService.save(p14);
        Producto p15 = Producto.builder()
                .marca("Jinko Solar")
                .modelo("Swan")
                .dimension(190.0)
                .cantidad(20)
                .potencia(72.0)
                .eficiencia(0.183)
                .mantenimiento(m1)
                .build();
        productoService.save(p15);
        Producto p16 = Producto.builder()
                .marca("Jinko Solar")
                .modelo("Tiger")
                .dimension(165.0)
                .cantidad(15)
                .potencia(65.0)
                .eficiencia(0.206)
                .mantenimiento(m3)
                .build();
        productoService.save(p16);
        Producto p17 = Producto.builder()
                .marca("Trina Solar")
                .modelo("Honey")
                .dimension(165.0)
                .cantidad(15)
                .potencia(65.0)
                .eficiencia(0.726)
                .mantenimiento(m3)
                .build();
        productoService.save(p17);
        Producto p18 = Producto.builder()
                .marca("Trina Solar")
                .modelo("TallMax")
                .dimension(165.0)
                .cantidad(15)
                .potencia(65.0)
                .eficiencia(0.546)
                .mantenimiento(m3)
                .build();
        productoService.save(p18);
        Producto p19 = Producto.builder()
                .marca("Trina Solar")
                .modelo("DuoMax")
                .dimension(165.0)
                .cantidad(15)
                .potencia(65.0)
                .eficiencia(0.226)
                .mantenimiento(m3)
                .build();
        productoService.save(p19);
        Producto p20 = Producto.builder()
                .marca("Trina Solar")
                .modelo("Vertex")
                .dimension(165.0)
                .cantidad(15)
                .potencia(65.0)
                .eficiencia(0.226)
                .mantenimiento(m3)
                .build();
        productoService.save(p20);
    }
}
