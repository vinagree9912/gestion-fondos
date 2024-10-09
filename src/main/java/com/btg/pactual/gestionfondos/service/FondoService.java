package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.dto.FondoDTO;
import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import com.btg.pactual.gestionfondos.model.Fondo;

import java.util.List;

public interface FondoService {
    void crearFondos(List<FondoDTO> fondos);
}
