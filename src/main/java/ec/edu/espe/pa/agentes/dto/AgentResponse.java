package ec.edu.espe.pa.agentes.dto;

import ec.edu.espe.pa.agentes.entity.EstadoAgente;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// DTO de salida: contrato público de la API, independiente de la entidad
public record AgentResponse(
        Long id, String name, String ip, String os, String username, BigDecimal cpuUsage,
        EstadoAgente estado, Boolean activo, LocalDateTime lastConnection,
        LocalDateTime creadoEn, LocalDateTime actualizadoEn
) {}
