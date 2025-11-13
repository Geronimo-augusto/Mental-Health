package com.GlobalFuture.MentalHealth.domain.dtos.output;

import java.time.LocalDateTime;

/**
 * DTO para enviar dados de RegistroDiario como resposta da API.
 * Inclui um DTO aninhado do Paciente para exibir seus dados.
 */
public record RegistroDiarioResponseDTO(
        Long id,
        LocalDateTime dataRegistro,
        Integer nivelHumor,
        Double horasSono,
        Integer nivelAnsiedade,
        String anotacao,
        PacienteResponseDTO paciente // Retorna o objeto Paciente (como DTO)
) {}
