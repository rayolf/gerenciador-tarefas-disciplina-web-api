Tratamento de Exceções Consistente
O tratamento de erros foi refinado para garantir que a API retorne códigos HTTP padrão e mensagens claras, conforme as boas práticas RESTful.

5.1. Tratamento de Erros de Validação (400 Bad Request)
Componente: RestExceptionHandler.java (pacote exception).

Função: Esta classe usa @ControllerAdvice e @ExceptionHandler para interceptar a exceção MethodArgumentNotValidException (lançada pelo @Valid).

Resultado: Em vez de um erro 500 ou uma resposta padrão, o manipulador retorna o status 400 Bad Request e um corpo JSON mapeando os campos inválidos e suas mensagens de erro.

5.2. Tratamento de Recurso Não Encontrado (404 Not Found)
Componente: ResourceNotFoundException.java (pacote exception).

Função: Esta é uma exceção customizada anotada com @ResponseStatus(HttpStatus.NOT_FOUND).

Implementação no Controller: Nos métodos GET, PUT e DELETE, o código agora usa o orElseThrow():

```java
return service.buscarPorId(id)
    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada..."));
```

Resultado: Quando uma tarefa com o ID fornecido não existe, a exceção é lançada, e o Spring automaticamente retorna o status 404 Not Found com a mensagem personalizada.
