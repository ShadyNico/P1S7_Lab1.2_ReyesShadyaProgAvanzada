package ec.edu.espe.pa.agentes.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributo único: repetirlo devuelve 409 Conflict
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String os;

    @Column(nullable = false)
    private String username;

    // Decimal: porcentaje de uso de CPU (0.00 - 100.00)
    @Column(precision = 5, scale = 2)
    private BigDecimal cpuUsage;

    // Enumeración
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoAgente estado;

    // Booleano
    private Boolean activo;

    // Fecha
    private LocalDateTime lastConnection;

    // Auditoría
    @Column(updatable = false)
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    @PrePersist
    protected void onCreate() {
        creadoEn = LocalDateTime.now();
        actualizadoEn = creadoEn;
    }

    @PreUpdate
    protected void onUpdate() {
        actualizadoEn = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public BigDecimal getCpuUsage() { return cpuUsage; }
    public void setCpuUsage(BigDecimal cpuUsage) { this.cpuUsage = cpuUsage; }

    public EstadoAgente getEstado() { return estado; }
    public void setEstado(EstadoAgente estado) { this.estado = estado; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public LocalDateTime getLastConnection() { return lastConnection; }
    public void setLastConnection(LocalDateTime lastConnection) { this.lastConnection = lastConnection; }

    public LocalDateTime getCreadoEn() { return creadoEn; }
    public LocalDateTime getActualizadoEn() { return actualizadoEn; }
}
