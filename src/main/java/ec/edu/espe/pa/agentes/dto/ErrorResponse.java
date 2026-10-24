package ec.edu.espe.pa.agentes.dto;

import java.time.LocalDateTime;
import java.util.List;

// Estructura uniforme de todas las respuestas de error
public record ErrorResponse(LocalDateTime timestamp, int status, String error,
                            String message, String path, List<String> errors) {}
