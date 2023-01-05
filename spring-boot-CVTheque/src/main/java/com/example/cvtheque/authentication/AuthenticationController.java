package com.example.cvtheque.authentication;

import com.example.cvtheque.security.config.JwtUtil;
import com.example.cvtheque.users.UserDto;
import com.example.cvtheque.users.UserEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDto user) throws IOException {
        return authenticationService.signIn(user);
    }

    @PostMapping("/check-token")
    public ResponseEntity<Boolean> createAuthenticationToken(@RequestBody String token) throws IOException {
        try {
            Boolean expired = !jwtUtil.isTokenExpired(token);
            return ResponseEntity.ok(expired);
        }catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

}
