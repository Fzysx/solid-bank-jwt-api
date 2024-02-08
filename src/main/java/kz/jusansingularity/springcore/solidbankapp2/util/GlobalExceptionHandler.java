package kz.jusansingularity.springcore.solidbankapp2.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AccountActionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        AccountActionResponse accountActionResponse = new AccountActionResponse(
                ex.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(accountActionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException(AccountNotFoundException ex) {
        AccountErrorResponse response = new AccountErrorResponse(
                "Account with this ID not found", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleException(SQLException ex) {
        AccountErrorResponse response = new AccountErrorResponse(
                "SQL Error. Operation was canceled", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        AccountErrorResponse response = new AccountErrorResponse(
                "Parameter is not valid", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> handleInsufficientFundsException(InsufficientFundsException ex) {
        AccountErrorResponse response = new AccountErrorResponse(
                "Insufficient funds on account", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
