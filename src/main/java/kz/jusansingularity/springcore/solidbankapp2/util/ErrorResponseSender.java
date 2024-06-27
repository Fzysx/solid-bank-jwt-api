package kz.jusansingularity.springcore.solidbankapp2.util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ErrorResponseSender {

    private final ObjectMapper objectMapper;

    public ErrorResponseSender(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", message);
        errorResponse.put("timestamp", String.valueOf(System.currentTimeMillis()));

        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}