package com.medvoll.api.domain.consulta.validacoes.cancelamento;

import com.medvoll.api.domain.consulta.DadosCancelamentoConsultaDto;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsultaDto dados);

}
