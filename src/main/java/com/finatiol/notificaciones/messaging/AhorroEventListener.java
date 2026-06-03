package com.finatiol.notificaciones.messaging;

import com.finatiol.notificaciones.dto.AhorroDepositadoEvent;
import com.finatiol.notificaciones.dto.EmailRequestDTO;
import com.finatiol.notificaciones.service.NotificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AhorroEventListener {

    private static final Logger log = LoggerFactory.getLogger(AhorroEventListener.class);

    private final NotificacionService notificacionService;

    public AhorroEventListener(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @RabbitListener(queues = "ahorro.notificaciones.queue")
    public void onAhorroDepositado(AhorroDepositadoEvent event) {
        log.info("[RabbitMQ] Depósito recibido: cuenta={} usuario={} monto={}",
                event.getCuentaId(), event.getUsername(), event.getMonto());

        try {
            EmailRequestDTO email = new EmailRequestDTO();
            email.setDestinatario("finatiols@gmail.com");
            email.setAsunto("💰 Depósito en Cuenta de Ahorro #" + event.getCuentaId());
            email.setMensaje("Se realizó un depósito de $" + event.getMonto()
                    + " en la cuenta de ahorro #" + event.getCuentaId()
                    + " del usuario " + event.getUsername()
                    + ". Saldo actual: $" + event.getSaldoActual()
                    + ". Fecha: " + event.getFecha());

            notificacionService.enviarEmail(email);
        } catch (Exception e) {
            log.error("[RabbitMQ] Error procesando evento de ahorro cuenta={}: {}",
                    event.getCuentaId(), e.getMessage(), e);
        }
    }
}
