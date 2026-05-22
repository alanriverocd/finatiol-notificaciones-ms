package com.finatiol.notificaciones.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.finatiol.notificaciones.dto.EmailRequestDTO;

@Service
public class NotificacionServiceImpl
        implements NotificacionService {

    private final JavaMailSender
            mailSender;

    public NotificacionServiceImpl(
            JavaMailSender mailSender) {

        this.mailSender = mailSender;
    }

    @Override
    public void enviarEmail(
            EmailRequestDTO request) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(
                request.getDestinatario());

        message.setSubject(
                request.getAsunto());

        message.setText(
                request.getMensaje());

        mailSender.send(message);
    }
}