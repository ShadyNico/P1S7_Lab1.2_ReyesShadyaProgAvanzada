package ec.edu.espe.pa.agentes.controller;

import ec.edu.espe.pa.agentes.dto.AgentRequest;
import ec.edu.espe.pa.agentes.dto.AgentResponse;
import ec.edu.espe.pa.agentes.service.AgentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/agents")
public class AgentController {
    private final AgentService service;

    public AgentController(AgentService service) {
        this.service = service;
    }

    // GET /api/agents?page=0&size=10&sort=name,asc
    @GetMapping
    public Page<AgentResponse> listar(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return service.listar(pageable);
    }

    // GET /api/agents/buscar?nombre=agente&page=0&size=10
    @GetMapping("/buscar")
    public Page<AgentResponse> buscar(@RequestParam String nombre,
                                      @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return service.buscarPorNombre(nombre, pageable);
    }

    @GetMapping("/{id}")
    public AgentResponse obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<AgentResponse> crear(@Valid @RequestBody AgentRequest request) {
        AgentResponse creado = service.crear(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.id())
                .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/{id}")
    public AgentResponse actualizar(@PathVariable Long id, @Valid @RequestBody AgentRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
