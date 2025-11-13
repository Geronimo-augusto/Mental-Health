package com.GlobalFuture.MentalHealth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção customizada para ser lançada quando um RegistroDiario não é encontrado.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(Long id) {
        super("Registro Diário não encontrado com o ID: " + id);
    }
}
