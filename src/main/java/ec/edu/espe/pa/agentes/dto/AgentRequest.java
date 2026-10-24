package ec.edu.espe.pa.agentes.dto;

import ec.edu.espe.pa.agentes.entity.EstadoAgente;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// DTO de entrada: no incluye id ni campos de auditoría
public record AgentRequest(
        @NotBlank(message = "Nombre es obligatorio")
        @Size(max = 100, message = "Nombre no puede superar 100 caracteres") String name,

        @NotBlank(message = "IP es obligatoria") String ip,

        @NotBlank(message = "Sistema operativo es obligatorio") String os,

        @NotBlank(message = "Usuario es obligatorio") String username,

        @NotNull(message = "Uso de CPU es obligatorio")
        @DecimalMin(value = "0.0", message = "Uso de CPU no puede ser negativo")
        @DecimalMax(value = "100.0", message = "Uso de CPU no puede superar 100")
        @Digits(integer = 3, fraction = 2, message = "Uso de CPU admite máximo 2 decimales") BigDecimal cpuUsage,

        @NotNull(message = "Estado es obligatorio") EstadoAgente estado,

        @NotNull(message = "Activo es obligatorio") Boolean activo,

        // Opcional: si no se envía se usa la fecha actual
        @PastOrPresent(message = "Última conexión no puede ser una fecha futura") LocalDateTime lastConnection
) {}
