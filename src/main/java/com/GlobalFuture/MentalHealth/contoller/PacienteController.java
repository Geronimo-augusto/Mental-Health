package com.GlobalFuture.MentalHealth.contoller;

import com.GlobalFuture.MentalHealth.domain.dtos.Input.PacienteRequestDTO;
import com.GlobalFuture.MentalHealth.domain.dtos.output.PacienteResponseDTO;
import com.GlobalFuture.MentalHealth.domain.service.PacienteService;
import jakarta.validation.Valid; // Importante para ativar a validação
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes") // Define o prefixo da rota para este controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    /**
     * Endpoint para LISTAR TODOS os pacientes.
     * GET /pacientes
     */
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> listarTodosPacientes() {
        List<PacienteResponseDTO> pacientes = pacienteService.listarTodosPacientes();
        return ResponseEntity.ok(pacientes); // Retorna 200 OK
    }

    /**
     * Endpoint para BUSCAR UM paciente por ID.
     * GET /pacientes/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> buscarPacientePorId(@PathVariable Long id) {
        PacienteResponseDTO paciente = pacienteService.buscarPacientePorId(id);
        return ResponseEntity.ok(paciente); // Retorna 200 OK
    }

    /**
     * Endpoint para CRIAR um novo paciente.
     * POST /pacientes
     * Usa @Valid para acionar as validações do DTO[cite: 71].
     */
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> criarPaciente(@Valid @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO pacienteSalvo = pacienteService.criarPaciente(dto);

        // Cria a URI para o novo recurso criado (boa prática REST)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pacienteSalvo.id())
                .toUri();

        return ResponseEntity.created(location).body(pacienteSalvo); // Retorna 201 Created [cite: 48]
    }

    /**
     * Endpoint para ATUALIZAR um paciente existente.
     * PUT /pacientes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> atualizarPaciente(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequestDTO dto) {

        PacienteResponseDTO pacienteAtualizado = pacienteService.atualizarPaciente(id, dto);
        return ResponseEntity.ok(pacienteAtualizado); // Retorna 200 OK
    }

    /**
     * Endpoint para DELETAR um paciente.
     * DELETE /pacientes/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content [cite: 116]
    }
}