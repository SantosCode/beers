package br.com.midway.beersapi.resource.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.MongoWriteException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.UnexpectedTypeException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@ControllerAdvice
public class BeerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return getObjectResponseEntityListErros(ex, headers, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return getObjectResponseEntityListErros(ex, headers, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return getObjectResponseEntityListErros(ex, headers, request);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler({ MongoWriteException.class })
    public ResponseEntity<Object> handleDuplicateKeyException(MongoWriteException ex,
            WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler({ UnexpectedTypeException.class })
    public ResponseEntity<Object> handleUnexpectedTypeException(UnexpectedTypeException ex,
            WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler({ NoSuchElementException.class })
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex,
            WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    @ExceptionHandler({ ParametroException.class })
    public ResponseEntity<Object> handleParametroException(ParametroException ex,
            WebRequest request) {
        return getObjectResponseEntity(ex, request);
    }

    private ResponseEntity<Object> getObjectResponseEntityListErros(Exception ex,
            HttpHeaders headers, WebRequest request) {
        val mensagemUsuario = "Requisi\u00e7\u00e3o inv\u00e1lida";
        val mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
        val erros = Collections.singletonList(new Erro(mensagemUsuario, mensagemDev));
        log.error(mensagemDev, ex);
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> getObjectResponseEntity(Exception ex, WebRequest request) {
        String mensagemUsuario = "Requisi\u00e7\u00e3o inv\u00e1lida";
        String mensagemDev = ExceptionUtils.getRootCauseMessage(ex);
        log.error(mensagemDev, ex);
        return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDev),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @AllArgsConstructor
    public static class Erro {

        @JsonProperty("mensagem-usuario")
        @Getter
        private String mensagemUsuario;
        @JsonProperty("mensagem-desenvolvedor")
        @Getter
        private String mensagemDev;
    }
}
