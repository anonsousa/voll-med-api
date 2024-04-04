package com.medvoll.api.domain.medico;

import com.medvoll.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarMedicoDto(

        @NotNull Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
