package es.miguelorg.demopowerpickupplanet.controlador.rest;

import es.miguelorg.demopowerpickupplanet.entidad.Calculo;
import es.miguelorg.demopowerpickupplanet.controlador.servicio.CalculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculo")
public class CalculoController {

    @Autowired
    private CalculoService calculoService;

    /**
     * Realiza el calculo del servicio
     * */
    @PostMapping("")
    public Calculo calcularAhorroEnergiaYDinero(@RequestBody Calculo calculo) {
        return calculoService.calcularAhorroEnergiaYDinero(calculo);
    }
}
