package com.finatiol.notificaciones.service;

import com.finatiol.notificaciones.dto.EmailRequestDTO;

public interface NotificacionService {

    void enviarEmail(
            EmailRequestDTO request);
}