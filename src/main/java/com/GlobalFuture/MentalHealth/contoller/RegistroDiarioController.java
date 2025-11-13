package com.GlobalFuture.MentalHealth.contoller;

import com.GlobalFuture.MentalHealth.domain.dtos.Input.RegistroDiarioRequestDTO;
import com.GlobalFuture.MentalHealth.domain.dtos.output.RegistroDiarioResponseDTO;
import com.GlobalFuture.MentalHealth.domain.service.RegistroDiarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/registros") // Define o prefixo da rota
public class RegistroDiarioController {

    @Autowired
    private RegistroDiarioService registroService;

    /**
     * Endpoint para LISTAR TODOS os registros.
     * GET /registros
     */
    @GetMapping
    public ResponseEntity<List<RegistroDiarioResponseDTO>> listarTodosRegistros() {
        List<RegistroDiarioResponseDTO> registros = registroService.listarTodosRegistros();
        return ResponseEntity.ok(registros);
    }

    /**
     * Endpoint para BUSCAR UM registro por ID.
     * GET /registros/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<RegistroDiarioResponseDTO> buscarRegistroPorId(@PathVariable Long id) {
        RegistroDiarioResponseDTO registro = registroService.buscarRegistroPorId(id);
        return ResponseEntity.ok(registro);
    }

    /**
     * Endpoint para CRIAR um novo registro.
     * POST /registros
     */
    @PostMapping
    public ResponseEntity<RegistroDiarioResponseDTO> criarRegistro(@Valid @RequestBody RegistroDiarioRequestDTO dto) {
        RegistroDiarioResponseDTO registroSalvo = registroService.criarRegistro(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registroSalvo.id())
                .toUri();

        return ResponseEntity.created(location).body(registroSalvo); // Retorna 201 Created
    }

    /**
     * Endpoint para ATUALIZAR um registro existente.
     * PUT /registros/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<RegistroDiarioResponseDTO> atualizarRegistro(
            @PathVariable Long id,
            @Valid @RequestBody RegistroDiarioRequestDTO dto) {

        RegistroDiarioResponseDTO registroAtualizado = registroService.atualizarRegistro(id, dto);
        return ResponseEntity.ok(registroAtualizado); // Retorna 200 OK
    }

    /**
     * Endpoint para DELETAR um registro.
     * DELETE /registros/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable Long id) {
        registroService.deletarRegistro(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }



    /**
     * Endpoint para listar todos os registros de um paciente específico.
     * GET /pacientes/{pacienteId}/registros
     * (Poderia estar no PacienteController também, é uma escolha de design)
     */
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<RegistroDiarioResponseDTO>> listarRegistrosPorPaciente(@PathVariable Long pacienteId) {
        List<RegistroDiarioResponseDTO> registros = registroService.listarRegistrosPorPaciente(pacienteId);
        return ResponseEntity.ok(registros);
    }
}
