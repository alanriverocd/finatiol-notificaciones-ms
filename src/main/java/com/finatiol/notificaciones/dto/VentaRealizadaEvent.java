package com.finatiol.notificaciones.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VentaRealizadaEvent implements Serializable {

    private Long ventaId;
    private String usuario;
    private Double total;
    private LocalDateTime fecha;

    public VentaRealizadaEvent() {}

    public Long getVentaId() { return ventaId; }
    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
