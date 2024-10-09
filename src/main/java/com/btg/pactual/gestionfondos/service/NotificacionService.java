package com.btg.pactual.gestionfondos.service;

import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import com.btg.pactual.gestionfondos.model.Cliente;
import com.btg.pactual.gestionfondos.model.Fondo;
import com.btg.pactual.gestionfondos.model.Suscripcion;
import jakarta.mail.MessagingException;

public interface NotificacionService {
    void sendEmail(String to, String subject, String text) throws MessagingException;
    void sendSms(String phoneNumber, String message);
    void enviarNotificacion(String tipoNotificacion, Cliente cliente, Suscripcion suscripcion) throws MessagingException;
}
