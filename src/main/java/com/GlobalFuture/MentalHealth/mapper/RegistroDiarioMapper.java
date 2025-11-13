package com.GlobalFuture.MentalHealth.mapper;

import com.GlobalFuture.MentalHealth.domain.dtos.Input.RegistroDiarioRequestDTO;
import com.GlobalFuture.MentalHealth.domain.dtos.output.PacienteResponseDTO;
import com.GlobalFuture.MentalHealth.domain.dtos.output.RegistroDiarioResponseDTO;
import com.GlobalFuture.MentalHealth.domain.model.Paciente;
import com.GlobalFuture.MentalHealth.domain.model.RegistroDiario;

public class RegistroDiarioMapper {

    /**
     * Converte um RegistroDiarioRequestDTO (dados de entrada) para uma Entidade RegistroDiario.
     * Note que o Paciente (entidade completa) precisa ser passado,
     * pois o DTO só tem o ID. O Service cuidará de buscar o Paciente.
     */
    public static RegistroDiario toEntity(RegistroDiarioRequestDTO dto, Paciente paciente) {
        RegistroDiario registro = new RegistroDiario();
        registro.setDataRegistro(dto.dataRegistro());
        registro.setNivelHumor(dto.nivelHumor());
        registro.setHorasSono(dto.horasSono());
        registro.setNivelAnsiedade(dto.nivelAnsiedade());
        registro.setAnotacao(dto.anotacao());
        registro.setPaciente(paciente); // Associa o paciente encontrado
        return registro;
    }

    /**
     * Converte uma Entidade RegistroDiario para um RegistroDiarioResponseDTO (dados de saída).
     */
    public static RegistroDiarioResponseDTO toResponseDTO(RegistroDiario entity) {
        // Usa o PacienteMapper para converter o Paciente aninhado
        PacienteResponseDTO pacienteDTO = PacienteMapper.toResponseDTO(entity.getPaciente());

        return new RegistroDiarioResponseDTO(
                entity.getId(),
                entity.getDataRegistro(),
                entity.getNivelHumor(),
                entity.getHorasSono(),
                entity.getNivelAnsiedade(),
                entity.getAnotacao(),
                pacienteDTO // Inclui o DTO do paciente
        );
    }

    /**
     * Atualiza uma Entidade RegistroDiario existente com dados de um DTO.
     */
    public static void updateEntityFromDTO(RegistroDiario entity, RegistroDiarioRequestDTO dto) {
        entity.setDataRegistro(dto.dataRegistro());
        entity.setNivelHumor(dto.nivelHumor());
        entity.setHorasSono(dto.horasSono());
        entity.setNivelAnsiedade(dto.nivelAnsiedade());
        entity.setAnotacao(dto.anotacao());
        // O paciente associado (pacienteId) geralmente não é atualizado,
        // mas se fosse, o Service precisaria buscar a nova entidade Paciente.
    }
}
