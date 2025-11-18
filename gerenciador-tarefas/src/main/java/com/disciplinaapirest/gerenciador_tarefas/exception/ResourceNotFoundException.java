package com.disciplinaapirest.gerenciador_tarefas.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exceção customizada para ser lançada quando um recurso (Tarefa) não é
 * encontrado.
 * O Spring, via @ResponseStatus, mapeia esta exceção automaticamente para o
 * Status HTTP 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Construtor que aceita uma mensagem de erro
    public ResourceNotFoundException(String message) {
        super(message);
    }
}