package com.GlobalFuture.MentalHealth.domain.dtos.Input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

/**
 * DTO para receber dados de criação ou atualização de RegistroDiario.
 */
public record RegistroDiarioRequestDTO(
        @NotNull(message = "A data do registro é obrigatória")
        @PastOrPresent(message = "A data do registro não pode ser futura")
        LocalDateTime dataRegistro,

        @NotNull(message = "O nível de humor é obrigatório")
        @Min(value = 1, message = "Nível de humor deve ser no mínimo 1")
        @Max(value = 5, message = "Nível de humor deve ser no máximo 5")
        Integer nivelHumor,

        @Min(value = 0, message = "Horas de sono não podem ser negativas")
        Double horasSono,

        @NotNull(message = "O nível de ansiedade é obrigatório")
        @Min(value = 1, message = "Nível de ansiedade deve ser no mínimo 1")
        @Max(value = 5, message = "Nível de ansiedade deve ser no máximo 5")
        Integer nivelAnsiedade,

        String anotacao, // Opcional

        @NotNull(message = "O ID do paciente é obrigatório")
        Long pacienteId // Importante: Recebemos apenas o ID do paciente
) {}