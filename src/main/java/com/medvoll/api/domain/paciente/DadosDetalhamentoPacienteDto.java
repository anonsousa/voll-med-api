package com.medvoll.api.domain.paciente;

import com.medvoll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPacienteDto(Long id, String nome, String email, String telefone, String cpf, Endereco endreco) {

    public DadosDetalhamentoPacienteDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
