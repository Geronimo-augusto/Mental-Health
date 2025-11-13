package com.GlobalFuture.MentalHealth.domain.dtos.output;

import java.time.LocalDate;

/**
 * DTO para enviar dados de Paciente como resposta da API.
 * Filtra dados sensíveis (ex: não envia a senha, se houvesse).
 */
public record PacienteResponseDTO(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento
) {}
