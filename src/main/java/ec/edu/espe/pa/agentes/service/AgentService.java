package ec.edu.espe.pa.agentes.service;

import ec.edu.espe.pa.agentes.dto.AgentRequest;
import ec.edu.espe.pa.agentes.dto.AgentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgentService {
    Page<AgentResponse> listar(Pageable pageable);

    Page<AgentResponse> buscarPorNombre(String nombre, Pageable pageable);

    AgentResponse obtener(Long id);

    AgentResponse crear(AgentRequest request);

    AgentResponse actualizar(Long id, AgentRequest request);

    void eliminar(Long id);
}
