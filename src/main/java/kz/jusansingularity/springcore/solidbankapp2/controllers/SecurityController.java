package kz.jusansingularity.springcore.solidbankapp2.controllers;

import kz.jusansingularity.springcore.solidbankapp2.DTO.JwtRequestDTO;
import kz.jusansingularity.springcore.solidbankapp2.DTO.RegistrationUserDTO;
import kz.jusansingularity.springcore.solidbankapp2.service.secureServices.AuthService;
import kz.jusansingularity.springcore.solidbankapp2.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final AuthService authService;
    private final JwtTokenUtils jwtTokenUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDTO authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDTO registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }

}
