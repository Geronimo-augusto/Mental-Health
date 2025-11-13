package com.GlobalFuture.MentalHealth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handler Global de Exceções.
 * Captura exceções específicas e retorna respostas HTTP formatadas.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento para exceções "Não Encontrado" (404)
    @ExceptionHandler({PacienteNaoEncontradoException.class, RegistroNaoEncontradoException.class})
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(RuntimeException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Tratamento para erros de validação (Bean Validation) (400)
    // Exigido pelo PDF [cite: 78]
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        // Coleta todas as mensagens de erro de validação
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        String errorMessage = "Erro de validação: " + String.join(", ", errors);

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Tratamento genérico para outros erros (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno no servidor: " + ex.getMessage(),
                LocalDateTime.now()
        );
        // (Idealmente, logar o 'ex' aqui)
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


record ApiErrorResponse(int status, String message, LocalDateTime timestamp) {}