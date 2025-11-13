package com.GlobalFuture.MentalHealth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção customizada para ser lançada quando um Paciente não é encontrado.
 * @ResponseStatus(HttpStatus.NOT_FOUND) informa ao Spring para retornar 404
 * quando esta exceção não for tratada por um @RestControllerAdvice.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PacienteNaoEncontradoException extends RuntimeException {

    public PacienteNaoEncontradoException(Long id) {
        super("Paciente não encontrado com o ID: " + id);
    }

    public PacienteNaoEncontradoException(String message) {
        super(message);
    }
}
