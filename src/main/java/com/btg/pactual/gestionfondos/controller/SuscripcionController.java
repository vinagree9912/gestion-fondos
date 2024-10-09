package com.btg.pactual.gestionfondos.controller;

import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import com.btg.pactual.gestionfondos.service.SuscripcionService;
import com.btg.pactual.gestionfondos.utils.Constantes;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RestController
@RequestMapping(Constantes.PATH_SUSCRIBIR)
@AllArgsConstructor
@CrossOrigin(
        origins = "*",
        methods = {RequestMethod.OPTIONS})
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class SuscripcionController {

    private final SuscripcionService suscripcionService;
    @PostMapping()
    public ResponseEntity<String> suscribirFondo(@Valid @RequestBody SuscripcionRequestDTO suscripcionDTO) throws MessagingException {
        suscripcionService.suscribirFondo(suscripcionDTO);
        return ResponseEntity.ok("Suscripción exitosa");
    }

    @DeleteMapping(Constantes.PATH_CANCELAR)
    public ResponseEntity<String> cancelarSuscripcion(@PathVariable  String suscripcionId, @PathVariable  String tipoNotificacion) throws MessagingException {
        suscripcionService.cancelarSuscripcion(suscripcionId, tipoNotificacion);
        return ResponseEntity.ok("Cancelación exitosa");
    }

}
