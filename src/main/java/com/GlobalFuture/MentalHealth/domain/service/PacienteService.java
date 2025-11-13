package com.GlobalFuture.MentalHealth.domain.service;


import com.GlobalFuture.MentalHealth.domain.dtos.Input.PacienteRequestDTO;
import com.GlobalFuture.MentalHealth.domain.dtos.output.PacienteResponseDTO;
import com.GlobalFuture.MentalHealth.domain.model.Paciente;
import com.GlobalFuture.MentalHealth.domain.repository.PacienteRepository;
import com.GlobalFuture.MentalHealth.exception.PacienteNaoEncontradoException;
import com.GlobalFuture.MentalHealth.mapper.PacienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Método privado para buscar Paciente ou lançar exceção
    private Paciente findPacienteById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> listarTodosPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PacienteResponseDTO buscarPacientePorId(Long id) {
        Paciente paciente = findPacienteById(id);
        return PacienteMapper.toResponseDTO(paciente);
    }

    @Transactional
    public PacienteResponseDTO criarPaciente(PacienteRequestDTO dto) {
        // (Opcional: Adicionar regra de negócio, ex: verificar se email já existe)
        // if (pacienteRepository.existsByEmail(dto.email())) {
        //    throw new IllegalArgumentException("E-mail já cadastrado");
        // }

        Paciente paciente = PacienteMapper.toEntity(dto);
        Paciente pacienteSalvo = pacienteRepository.save(paciente);
        return PacienteMapper.toResponseDTO(pacienteSalvo);
    }

    @Transactional
    public PacienteResponseDTO atualizarPaciente(Long id, PacienteRequestDTO dto) {
        Paciente pacienteExistente = findPacienteById(id);

        // Atualiza a entidade existente com os dados do DTO
        PacienteMapper.updateEntityFromDTO(pacienteExistente, dto);

        Paciente pacienteAtualizado = pacienteRepository.save(pacienteExistente);
        return PacienteMapper.toResponseDTO(pacienteAtualizado);
    }

    @Transactional
    public void deletarPaciente(Long id) {
        Paciente paciente = findPacienteById(id);
        pacienteRepository.delete(paciente);
    }
}
