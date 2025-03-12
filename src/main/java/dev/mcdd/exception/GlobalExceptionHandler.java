package dev.mcdd.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {BusinessException.class})
	public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		log.error("Business Error Handled  ===> ", ex);
		ErrorResponseException errorResponseException =
			new ErrorResponseException(
				HttpStatus.INTERNAL_SERVER_ERROR,
				ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()),
				ex.getCause());
		return handleExceptionInternal(
			errorResponseException,
			errorResponseException.getBody(),
			errorResponseException.getHeaders(),
			errorResponseException.getStatusCode(),
			request);
	}

	@SuppressWarnings("NullableProblems")
	@Override
	@Nullable
	public ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request) {
		log.error("MethodArgumentNotValidException Handled  ===> ", ex);
		ErrorResponseException errorResponseException =
			new ErrorResponseException(
				status, ProblemDetail.forStatusAndDetail(status, ex.getMessage()), ex.getCause());
		return handleExceptionInternal(
			errorResponseException,
			errorResponseException.getBody(),
			errorResponseException.getHeaders(),
			errorResponseException.getStatusCode(),
			request);
	}

}
