package ec.edu.espe.pa.agentes.service;

import ec.edu.espe.pa.agentes.dto.AgentRequest;
import ec.edu.espe.pa.agentes.dto.AgentResponse;
import ec.edu.espe.pa.agentes.entity.Agent;
import ec.edu.espe.pa.agentes.entity.EstadoAgente;
import ec.edu.espe.pa.agentes.exception.BusinessRuleException;
import ec.edu.espe.pa.agentes.exception.DuplicateResourceException;
import ec.edu.espe.pa.agentes.exception.ResourceNotFoundException;
import ec.edu.espe.pa.agentes.mapper.AgentMapper;
import ec.edu.espe.pa.agentes.repository.AgentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AgentServiceImpl implements AgentService {
    private final AgentRepository repository;
    private final AgentMapper mapper;

    public AgentServiceImpl(AgentRepository repository, AgentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<AgentResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public Page<AgentResponse> buscarPorNombre(String nombre, Pageable pageable) {
        return repository.findByNameContainingIgnoreCase(nombre, pageable).map(mapper::toResponse);
    }

    @Override
    public AgentResponse obtener(Long id) {
        return mapper.toResponse(buscar(id));
    }

    @Override
    @Transactional
    public AgentResponse crear(AgentRequest request) {
        if (repository.existsByNameIgnoreCase(request.name().trim())) {
            throw new DuplicateResourceException("Ya existe un agente con el nombre '" + request.name() + "'");
        }
        validarReglas(request);
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    @Transactional
    public AgentResponse actualizar(Long id, AgentRequest request) {
        Agent agent = buscar(id);
        if (repository.existsByNameIgnoreCaseAndIdNot(request.name().trim(), id)) {
            throw new DuplicateResourceException("Ya existe un agente con el nombre '" + request.name() + "'");
        }
        validarReglas(request);
        mapper.update(agent, request);
        // saveAndFlush ejecuta @PreUpdate para que actualizadoEn se devuelva con valor
        return mapper.toResponse(repository.saveAndFlush(agent));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }

    private Agent buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agente no encontrado con id " + id));
    }

    // Regla de negocio: un agente OFFLINE no puede estar marcado como activo
    private void validarReglas(AgentRequest request) {
        if (request.estado() == EstadoAgente.OFFLINE && Boolean.TRUE.equals(request.activo())) {
            throw new BusinessRuleException("Un agente en estado OFFLINE no puede estar activo");
        }
    }
}
