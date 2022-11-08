package es.inditex.brands.infrastructure.inbound.api.exception.handler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.inditex.brands.domain.exception.IncorrectBrandIdException;
import es.inditex.brands.domain.exception.IncorrectProductIdException;
import es.inditex.brands.domain.exception.PriceNotFoundException;
import es.inditex.brands.infrastructure.inbound.api.dto.ErrorDTO;

/**
 * @author Núria Curto
 *
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * Método controlador que personaliza la response de error en caso que se 
	 * produzca una Exception del tipo IncorrectBrandIdException
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<ErrorDTO>
	 * @throws IOException
	 */
	@ExceptionHandler(IncorrectBrandIdException.class)
	public ResponseEntity<ErrorDTO> springHandleIncorrectBrand(Exception ex, WebRequest request) throws IOException {
		return this.formateErrorResponse(ex, request);
    }
	
	/**
	 * Método controlador que personaliza la response de error en caso que se 
	 * produzca una Exception del tipo IncorrectProductIdException
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<ErrorDTO>
	 * @throws IOException
	 */
	@ExceptionHandler(IncorrectProductIdException.class)
	public ResponseEntity<ErrorDTO> springHandleIncorrectProduct(Exception ex, WebRequest request) throws IOException {
		return this.formateErrorResponse(ex, request);
    }
	
	/**
	 * Método controlador que personaliza la response de error en caso que se 
	 * produzca una Exception del tipo PriceNotFoundException
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<ErrorDTO>
	 * @throws IOException
	 */
	@ExceptionHandler(PriceNotFoundException.class)
	public ResponseEntity<ErrorDTO> springHandlePriceNotFound(Exception ex, WebRequest request) throws IOException {
		return this.formateErrorResponse(ex, request);
    }
	
	/**
	 * Método que forma la respnse personalizada de error
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity<ErrorDTO>
	 * @throws IOException
	 */
	private ResponseEntity<ErrorDTO> formateErrorResponse(Exception ex, WebRequest request) throws IOException {
		ErrorDTO errorDto = ErrorDTO.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(ex.getMessage()).build();
		
		return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
