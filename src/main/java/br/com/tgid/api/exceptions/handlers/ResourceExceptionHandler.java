package br.com.tgid.api.exceptions.handlers;

import br.com.tgid.api.exceptions.ObjectNotFoundException;
import br.com.tgid.api.exceptions.dto.ErrorMessageDTO;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @version 0.0.1
 * @author pablojg9
 * @since 20/01/2024
 * */
@RestControllerAdvice
public class ResourceExceptionHandler {

    /**
     * @Param ex, pega a message ao fazer a validacao de alguma exception.
     * @Param request, pega o Servet da requisicao.
     * @exception ObjectNotFoundException, retorna o exception de not found do objeto {@link ErrorMessageDTO}.
     * */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> objectNotFoundExceptionHandler(ObjectNotFoundException ex, HttpServletRequest request) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessageDTO);
    }
}
