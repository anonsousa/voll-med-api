package com.medvoll.api.domain.paciente;

import com.medvoll.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;


public record DadosAtualizacaoPacienteDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
