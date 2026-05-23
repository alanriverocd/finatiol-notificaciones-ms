package com.finatiol.notificaciones.service;

import com.finatiol.notificaciones.constants.ErrorMessages;
import com.finatiol.notificaciones.dto.EmailRequestDTO;
import com.finatiol.notificaciones.exception.NotificacionException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private final JavaMailSender mailSender;

    public NotificacionServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarEmail(EmailRequestDTO request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getDestinatario());
            message.setSubject(request.getAsunto());
            message.setText(request.getMensaje());
            mailSender.send(message);
        } catch (MailException ex) {
            throw new NotificacionException(ErrorMessages.ERROR_ENVIO_EMAIL);
        }
    }
}