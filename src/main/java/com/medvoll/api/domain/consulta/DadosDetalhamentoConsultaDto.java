package com.medvoll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDto(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamentoConsultaDto(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
