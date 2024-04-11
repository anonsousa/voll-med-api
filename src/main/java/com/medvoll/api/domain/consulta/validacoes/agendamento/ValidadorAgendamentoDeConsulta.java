package com.medvoll.api.domain.consulta.validacoes.agendamento;

import com.medvoll.api.domain.consulta.DadosAgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsultaDto dados);
}
