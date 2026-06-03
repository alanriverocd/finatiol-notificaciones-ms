package com.finatiol.notificaciones.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AhorroDepositadoEvent {

    private Long cuentaId;
    private String username;
    private BigDecimal monto;
    private BigDecimal saldoActual;
    private LocalDateTime fecha;

    public AhorroDepositadoEvent() {}

    public Long getCuentaId() { return cuentaId; }
    public void setCuentaId(Long cuentaId) { this.cuentaId = cuentaId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public BigDecimal getSaldoActual() { return saldoActual; }
    public void setSaldoActual(BigDecimal saldoActual) { this.saldoActual = saldoActual; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
