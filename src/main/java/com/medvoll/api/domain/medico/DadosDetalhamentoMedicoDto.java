package com.medvoll.api.domain.medico;

import com.medvoll.api.domain.endereco.Endereco;

public record DadosDetalhamentoMedicoDto(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedicoDto(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
