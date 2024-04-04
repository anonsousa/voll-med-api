package com.medvoll.api.controller;

import com.medvoll.api.domain.usuario.DataAuthenticationDto;
import com.medvoll.api.domain.usuario.Usuario;
import com.medvoll.api.infra.security.DadosTokenJWTdto;
import com.medvoll.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthenticationDto dados){
        var Authtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(Authtoken);

        var tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());


        return ResponseEntity.ok(new DadosTokenJWTdto(tokenJWT));
    }
}
