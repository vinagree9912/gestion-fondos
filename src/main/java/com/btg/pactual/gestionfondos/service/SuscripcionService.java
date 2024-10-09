package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import jakarta.mail.MessagingException;

public interface SuscripcionService {
    public void cancelarSuscripcion(String suscripcionId, String tipoNotificacion) throws MessagingException;
    public void suscribirFondo(SuscripcionRequestDTO suscripcionRequestDTO) throws MessagingException;
    void validateFondoDTO(SuscripcionRequestDTO suscripcionRequestDTO);

}
