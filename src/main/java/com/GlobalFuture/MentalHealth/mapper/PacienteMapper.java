package com.GlobalFuture.MentalHealth.mapper;


import com.GlobalFuture.MentalHealth.domain.dtos.Input.PacienteRequestDTO;
import com.GlobalFuture.MentalHealth.domain.dtos.output.PacienteResponseDTO;
import com.GlobalFuture.MentalHealth.domain.model.Paciente;

/**
 * Classe de Mapeamento  para converter entre Paciente (Entidade)
 * e seus DTOs (PacienteRequestDTO, PacienteResponseDTO).
 */
public class PacienteMapper {

    /**
     * Converte um PacienteRequestDTO (dados de entrada) para uma Entidade Paciente.
     * Usado ao Criar um novo paciente.
     */
    public static Paciente toEntity(PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setNome(dto.nome());
        paciente.setEmail(dto.email());
        paciente.setDataNascimento(dto.dataNascimento());
        return paciente;
    }

    /**
     * Converte uma Entidade Paciente para um PacienteResponseDTO (dados de saída).
     * Usado ao Listar ou Buscar um paciente.
     */
    public static PacienteResponseDTO toResponseDTO(Paciente entity) {
        return new PacienteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getDataNascimento()
        );
    }

    /**
     * Atualiza uma Entidade Paciente existente com dados de um PacienteRequestDTO.
     * Usado ao Atualizar um paciente.
     */
    public static void updateEntityFromDTO(Paciente entity, PacienteRequestDTO dto) {
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setDataNascimento(dto.dataNascimento());
    }
}