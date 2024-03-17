package br.com.fiap.pedido.api.handler;

import br.com.fiap.pedido.core.exception.PedidoInexistenteException;
import br.com.fiap.pedido.core.exception.PedidoStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handlerPedidoInexistenteException(PedidoInexistenteException ex){
        final var details = new ExceptionDetails(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handlerPedidoStatusException(PedidoStatusException ex){
        final var details = new ExceptionDetails(ex.getMessage());
        return ResponseEntity.badRequest().body(details);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handlerException(Exception ex){
        final var details = new ExceptionDetails(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(details);
    }
}


