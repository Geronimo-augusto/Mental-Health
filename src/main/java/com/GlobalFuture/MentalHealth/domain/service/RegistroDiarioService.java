package com.GlobalFuture.MentalHealth.domain.service;

import com.GlobalFuture.MentalHealth.domain.dtos.Input.RegistroDiarioRequestDTO;
import com.GlobalFuture.MentalHealth.domain.dtos.output.RegistroDiarioResponseDTO;
import com.GlobalFuture.MentalHealth.domain.model.Paciente;
import com.GlobalFuture.MentalHealth.domain.model.RegistroDiario;
import com.GlobalFuture.MentalHealth.domain.repository.PacienteRepository;
import com.GlobalFuture.MentalHealth.domain.repository.RegistroDiarioRepository;
import com.GlobalFuture.MentalHealth.exception.PacienteNaoEncontradoException;
import com.GlobalFuture.MentalHealth.exception.RegistroNaoEncontradoException;
import com.GlobalFuture.MentalHealth.mapper.RegistroDiarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroDiarioService {

    @Autowired
    private RegistroDiarioRepository registroRepository;

    @Autowired
    private PacienteRepository pacienteRepository; // Necessário para associar o registro

    // Método privado para buscar Registro ou lançar exceção
    private RegistroDiario findRegistroById(Long id) {
        return registroRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(id));
    }

    // Método privado para buscar Paciente ou lançar exceção
    private Paciente findPacienteById(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new PacienteNaoEncontradoException(id));
    }

    @Transactional(readOnly = true)
    public List<RegistroDiarioResponseDTO> listarTodosRegistros() {
        return registroRepository.findAll()
                .stream()
                .map(RegistroDiarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RegistroDiarioResponseDTO buscarRegistroPorId(Long id) {
        RegistroDiario registro = findRegistroById(id);
        return RegistroDiarioMapper.toResponseDTO(registro);
    }

    @Transactional
    public RegistroDiarioResponseDTO criarRegistro(RegistroDiarioRequestDTO dto) {
        // Regra de Negócio: O Paciente deve existir para criar um registro
        Paciente paciente = findPacienteById(dto.pacienteId());

        RegistroDiario registro = RegistroDiarioMapper.toEntity(dto, paciente);
        RegistroDiario registroSalvo = registroRepository.save(registro);

        return RegistroDiarioMapper.toResponseDTO(registroSalvo);
    }

    @Transactional
    public RegistroDiarioResponseDTO atualizarRegistro(Long id, RegistroDiarioRequestDTO dto) {
        // 1. Verifica se o registro existe
        RegistroDiario registroExistente = findRegistroById(id);

        // 2. Verifica se o paciente (novo ou o mesmo) existe
        Paciente paciente = findPacienteById(dto.pacienteId());

        // 3. Atualiza os dados do registro
        RegistroDiarioMapper.updateEntityFromDTO(registroExistente, dto);

        // 4. Atualiza o paciente associado
        registroExistente.setPaciente(paciente);

        RegistroDiario registroAtualizado = registroRepository.save(registroExistente);
        return RegistroDiarioMapper.toResponseDTO(registroAtualizado);
    }

    @Transactional
    public void deletarRegistro(Long id) {
        RegistroDiario registro = findRegistroById(id);
        registroRepository.delete(registro);
    }

    // (Opcional - Bônus) Listar registros por paciente
    @Transactional(readOnly = true)
    public List<RegistroDiarioResponseDTO> listarRegistrosPorPaciente(Long pacienteId) {
        // Verifica se o paciente existe
        if (!pacienteRepository.existsById(pacienteId)) {
            throw new PacienteNaoEncontradoException(pacienteId);
        }

        return registroRepository.findByPacienteId(pacienteId)
                .stream()
                .map(RegistroDiarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}