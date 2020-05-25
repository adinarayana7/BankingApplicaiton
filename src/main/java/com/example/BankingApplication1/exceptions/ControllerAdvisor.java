package com.example.BankingApplication1.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InsufficientCustomerDetails.class)
	public ResponseEntity<Object> handleInsufficientCustomerDetails(InsufficientCustomerDetails ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Insufficient Customer Details");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InavalidCredentials.class)
	public ResponseEntity<Object> handleInavalidCredentials(InavalidCredentials ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "InavalidCredentials");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotLoggedIn.class)
	public ResponseEntity<Object> handleCustomerNotLoggedIn(CustomerNotLoggedIn ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "customer not logged in");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SavingsAccountIdNotFoundException.class)
	public ResponseEntity<Object> handleSavingsAccountIdNotFoundException(SavingsAccountIdNotFoundException ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "invalid savingsAccount  id");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BeneficiaryAccountNotFound.class)
	public ResponseEntity<Object> handleBeneficiaryAccountNotFound(BeneficiaryAccountNotFound ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "enter valid Beneficiary AccountNumber");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InsufficientBalance.class)
	public ResponseEntity<Object> handleInsufficientBalance(InsufficientBalance ex,
			WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "entered amount is greater than the available balance");

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	

	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		body.put("errors", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
