package com.finatiol.notificaciones.messaging;

import com.finatiol.notificaciones.dto.EmailRequestDTO;
import com.finatiol.notificaciones.dto.VentaRealizadaEvent;
import com.finatiol.notificaciones.service.NotificacionService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VentaEventListener {

    private static final Logger log = LoggerFactory.getLogger(VentaEventListener.class);

    private final NotificacionService notificacionService;
    private final Counter mensajesRecibidosCounter;

    public VentaEventListener(NotificacionService notificacionService, MeterRegistry meterRegistry) {
        this.notificacionService = notificacionService;
        this.mensajesRecibidosCounter = Counter.builder("notificaciones_eventos_rabbitmq_total")
                .description("Total de eventos recibidos desde RabbitMQ")
                .register(meterRegistry);
    }

    @RabbitListener(queues = "ventas.notificaciones.queue")
    public void onVentaRealizada(VentaRealizadaEvent event) {
        mensajesRecibidosCounter.increment();
        log.info("[RabbitMQ] Venta recibida: id={} usuario={} total={}",
                event.getVentaId(), event.getUsuario(), event.getTotal());

        try {
            EmailRequestDTO email = new EmailRequestDTO();
            email.setDestinatario("finatiols@gmail.com");
            email.setAsunto("✅ Venta #" + event.getVentaId() + " registrada");
            email.setMensaje("Se registró la venta #" + event.getVentaId()
                    + " del usuario " + event.getUsuario()
                    + " por un total de $" + event.getTotal()
                    + ". ¡Gracias por su compra!");

            notificacionService.enviarEmail(email);
        } catch (Exception e) {
            log.error("[RabbitMQ] Error procesando evento de venta id={}: {}",
                    event.getVentaId(), e.getMessage(), e);
        }
    }
}

