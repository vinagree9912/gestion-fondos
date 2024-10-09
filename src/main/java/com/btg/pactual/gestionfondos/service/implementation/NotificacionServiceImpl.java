package com.btg.pactual.gestionfondos.service.implementation;
import com.btg.pactual.gestionfondos.dto.SuscripcionRequestDTO;
import com.btg.pactual.gestionfondos.model.Cliente;
import com.btg.pactual.gestionfondos.model.Fondo;
import com.btg.pactual.gestionfondos.model.Suscripcion;
import com.btg.pactual.gestionfondos.repository.ClienteRepository;
import com.btg.pactual.gestionfondos.repository.FondoRepository;
import com.btg.pactual.gestionfondos.service.NotificacionService;
import com.btg.pactual.gestionfondos.utils.Constantes;
import com.btg.pactual.gestionfondos.utils.enums.TipoNotificacion;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

import java.text.MessageFormat;

@Slf4j
@Service
@AllArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {


    private final JavaMailSender mailSender;
    private final SnsClient snsClient;
    private final FondoRepository fondoRepository;

    @Override
    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("sasgadson@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
        log.info(Constantes.SEND_EMAIL_LOG, to, subject);
    }

    @Override
    public void sendSms(String phoneNumber, String message) {
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build();
        snsClient.publish(request);
        log.info(Constantes.SEND_SMS_LOG, phoneNumber, message);
    }

    @Override
    public void enviarNotificacion(String tipoNotificacion, Cliente cliente, Suscripcion suscripcion) throws MessagingException {
        Fondo fondo = fondoRepository.findById(suscripcion.getFondoId());
        String nombreCompleto = cliente.getNombre() + " " + cliente.getApellido();
        String fondoNombre = fondo.getNombre();
        String suscripcionEstado = suscripcion.getEstado().toString();
        String mensaje = String.format(Constantes.MENSAJESMS, nombreCompleto, fondoNombre, suscripcionEstado);
        if(tipoNotificacion.equals(TipoNotificacion.EMAIL.toString())) {
            String subject =  MessageFormat.format(Constantes.SUSCRIPCION_EXITOSA, nombreCompleto, fondoNombre);
            sendEmail(cliente.getEmail(), subject, mensaje);
        } else if(tipoNotificacion.equals(TipoNotificacion.SMS.toString())) {
            sendSms(cliente.getTelefono(), mensaje);
        }
    }
}
