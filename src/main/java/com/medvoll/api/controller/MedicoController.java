package com.medvoll.api.controller;

import com.medvoll.api.domain.medico.DadosListagemMedicoDto;
import com.medvoll.api.domain.medico.Medico;
import com.medvoll.api.domain.medico.MedicoRepository;
import com.medvoll.api.domain.medico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicoDto dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDto(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity listarTodos(@PathVariable(value = "id")Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoMedicoDto(medico));
    }


    @GetMapping()
    public ResponseEntity<Page<DadosListagemMedicoDto>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedicoDto dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoMedicoDto(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable(value = "id")Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}