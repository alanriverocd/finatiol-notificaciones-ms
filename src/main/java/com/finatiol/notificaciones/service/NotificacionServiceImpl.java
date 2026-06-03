package com.finatiol.notificaciones.service;

import com.finatiol.common.constants.notificaciones.ErrorMessages;
import com.finatiol.notificaciones.dto.EmailRequestDTO;
import com.finatiol.notificaciones.exception.NotificacionException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final JavaMailSender mailSender;
    private final Counter emailsEnviadosCounter;
    private final Counter emailsErroresCounter;

    public NotificacionServiceImpl(JavaMailSender mailSender, MeterRegistry meterRegistry) {
        this.mailSender = mailSender;
        this.emailsEnviadosCounter = Counter.builder("notificaciones_emails_enviados_total")
                .description("Total de emails enviados exitosamente")
                .register(meterRegistry);
        this.emailsErroresCounter = Counter.builder("notificaciones_emails_errores_total")
                .description("Total de errores al enviar emails")
                .register(meterRegistry);
    }

    @Override
    public void enviarEmail(EmailRequestDTO request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getDestinatario());
            message.setSubject(request.getAsunto());
            message.setText(request.getMensaje());
            mailSender.send(message);
            emailsEnviadosCounter.increment();
        } catch (MailException ex) {
            emailsErroresCounter.increment();
            throw new NotificacionException(ErrorMessages.ERROR_ENVIO_EMAIL);
        }
    }
}