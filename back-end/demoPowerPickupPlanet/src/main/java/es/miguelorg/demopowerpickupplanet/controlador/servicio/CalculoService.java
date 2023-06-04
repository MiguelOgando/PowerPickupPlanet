package es.miguelorg.demopowerpickupplanet.controlador.servicio;

import es.miguelorg.demopowerpickupplanet.entidad.Calculo;
import es.miguelorg.demopowerpickupplanet.interfaz.CalculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculoService {

    @Autowired
    private CalculoRepository calculoRepository;

    /*
    * Se usan ideas generales y variables existentes en el formulario para poder realizar un calculo real y optimo
    * Calculo:
    * energiaGenerada = potencia * eficiencia * horasDeSol * cantidad
    * energiaConsumida = energiaGenerada * dimension
    * ahorroEnergia = energiaConsumida * costoElectricidad
    * ahorroDinero = ahorroEnergia * 12
    *
    * Se usan las variables restantes para mostrar el calculo
    * */
    public Calculo calcularAhorroEnergiaYDinero(Calculo calculo) {
        double energiaGenerada = calculo.getPotencia() * calculo.getEficiencia() * calculo.getHorasSolDia() * calculo.getCantidad();
        double energiaConsumida = energiaGenerada * calculo.getDimension();
        double ahorroEnergia = energiaConsumida * calculo.getCostoElectricidad();
        double ahorroDinero = ahorroEnergia * 12; // suponiendo 1 año

        calculo.setAhorroEnergia(ahorroEnergia);
        calculo.setAhorroDinero(ahorroDinero);

        return calculo;
    }
}
