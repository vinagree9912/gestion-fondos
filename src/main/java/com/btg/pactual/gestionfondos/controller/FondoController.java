package com.btg.pactual.gestionfondos.controller;

import com.btg.pactual.gestionfondos.dto.FondoDTO;
import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import com.btg.pactual.gestionfondos.model.Fondo;
import com.btg.pactual.gestionfondos.service.FondoService;
import com.btg.pactual.gestionfondos.service.SuscripcionService;
import com.btg.pactual.gestionfondos.utils.Constantes;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH_FONDO)
@AllArgsConstructor
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.OPTIONS})
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class FondoController {

    private final FondoService fondoService;



    @PostMapping(Constantes.PATH_CREAR_FONDOS)
    public ResponseEntity<String> crearFondos(@Valid @RequestBody List<@Valid FondoDTO> fondos) {
        try {
            fondoService.crearFondos(fondos);
            return ResponseEntity.ok("Fondos creados exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear los fondos: " + e.getMessage());
        }
    }

}
