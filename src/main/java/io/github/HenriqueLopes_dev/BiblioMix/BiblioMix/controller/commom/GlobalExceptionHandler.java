package io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.commom;

import io.github.HenriqueLopes_dev.BiblioMix.BiblioMix.controller.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(RuntimeException.class)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public ResponseErrorDTO internalServerErrorHandler(RuntimeException e){
    return new ResponseErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocorreu um erro inexperado, tente novamente mais tarde!",
            List.of()
    );
}


}
