package ec.edu.espe.pa.agentes.mapper;

import ec.edu.espe.pa.agentes.dto.AgentRequest;
import ec.edu.espe.pa.agentes.dto.AgentResponse;
import ec.edu.espe.pa.agentes.entity.Agent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgentMapper {

    public Agent toEntity(AgentRequest r) {
        Agent agent = new Agent();
        update(agent, r);
        return agent;
    }

    public void update(Agent agent, AgentRequest r) {
        agent.setName(r.name().trim());
        agent.setIp(r.ip().trim());
        agent.setOs(r.os().trim());
        agent.setUsername(r.username().trim());
        agent.setCpuUsage(r.cpuUsage());
        agent.setEstado(r.estado());
        agent.setActivo(r.activo());
        agent.setLastConnection(r.lastConnection() != null ? r.lastConnection() : LocalDateTime.now());
    }

    public AgentResponse toResponse(Agent a) {
        return new AgentResponse(a.getId(), a.getName(), a.getIp(), a.getOs(), a.getUsername(),
                a.getCpuUsage(), a.getEstado(), a.getActivo(), a.getLastConnection(),
                a.getCreadoEn(), a.getActualizadoEn());
    }
}
