package com.medvoll.api.domain.consulta.validacoes.agendamento;

import com.medvoll.api.domain.ValidacaoException;
import com.medvoll.api.domain.consulta.DadosAgendamentoConsultaDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{
    public void validar(DadosAgendamentoConsultaDto dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do horario de funcionamento.");
        }
    }
}
