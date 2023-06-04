package es.miguelorg.demopowerpickupplanet.entidad;

import lombok.Data;

import java.util.Map;

@Data
public class TipoMantenimiento {
    private final Map<String, String[]> map;
}
