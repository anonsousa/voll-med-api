package com.medvoll.api.controller;

import com.medvoll.api.domain.consulta.ConsultaService;
import com.medvoll.api.domain.consulta.DadosAgendamentoConsultaDto;
import com.medvoll.api.domain.consulta.DadosCancelamentoConsultaDto;
import com.medvoll.api.domain.consulta.DadosDetalhamentoConsultaDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDto dados){
        var dto = consultaService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsultaDto dados){
        consultaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
