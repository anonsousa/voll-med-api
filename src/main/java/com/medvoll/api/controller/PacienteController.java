package com.medvoll.api.controller;

import com.medvoll.api.domain.paciente.DadosListagemPacienteDto;
import com.medvoll.api.domain.paciente.Paciente;
import com.medvoll.api.domain.paciente.PacienteRepository;
import jakarta.validation.Valid;
import com.medvoll.api.domain.paciente.*;
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
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacienteDto dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDto(paciente));
    }

    @GetMapping("{id}")
    public ResponseEntity listarTodos(@PathVariable("id")Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DadosDetalhamentoPacienteDto(paciente));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacienteDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPacienteDto dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoPacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
