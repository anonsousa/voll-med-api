package com.medvoll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultaDto(
        @NotNull Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
