package com.finatiol.notificaciones.controller;

import com.finatiol.notificaciones.constants.SuccessCodes;
import com.finatiol.notificaciones.constants.SuccessMessages;
import com.finatiol.notificaciones.dto.ApiResponse;
import com.finatiol.notificaciones.dto.EmailRequestDTO;
import com.finatiol.notificaciones.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificaciones")
@Tag(name = "Notificaciones", description = "API para envío de notificaciones por email")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping("/email")
    @Operation(summary = "Enviar email", description = "Envía un correo electrónico al destinatario especificado")
    public ResponseEntity<ApiResponse<Void>> enviarEmail(
            @Valid @RequestBody EmailRequestDTO request) {

        notificacionService.enviarEmail(request);
        return ResponseEntity.ok(ApiResponse.success(
                SuccessCodes.EMAIL_ENVIADO,
                SuccessMessages.EMAIL_ENVIADO,
                null));
    }
}
