package com.finatiol.notificaciones.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailRequestDTO {

    @Email
    @NotBlank
    private String destinatario;

    @NotBlank
    private String asunto;

    @NotBlank
    private String mensaje;

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(
            String destinatario) {

        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(
            String asunto) {

        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(
            String mensaje) {

        this.mensaje = mensaje;
    }
}