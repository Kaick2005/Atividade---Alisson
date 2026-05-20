package br.com.ramos.nasaapi.controller;

import br.com.ramos.nasaapi.dto.AuthResponse;
import br.com.ramos.nasaapi.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO dto) {
        AuthResponse response;
        response = new AuthResponse();
        response.setToken("fake-token"); // placeholder

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("REGISTER OK");
    }
}