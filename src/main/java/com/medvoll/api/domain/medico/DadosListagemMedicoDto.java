package com.medvoll.api.domain.medico;

public record DadosListagemMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
