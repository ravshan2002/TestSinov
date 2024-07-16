package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody ProfileDTO profileDTO){
        String response = authService.registration(profileDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/verification/{userId}/{code}")
    public ResponseEntity<ProfileDTO> verification(@PathVariable("userId") Integer userId,@PathVariable("code") String code){

        ProfileDTO dto = authService.authorizationVerification(userId, code);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/sinov")
    public void sinov(){
        authService.sinov();
    }
}
