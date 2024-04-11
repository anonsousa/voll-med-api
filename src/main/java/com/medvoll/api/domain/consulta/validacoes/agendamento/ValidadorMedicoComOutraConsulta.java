package com.medvoll.api.domain.consulta.validacoes.agendamento;

import com.medvoll.api.domain.ValidacaoException;
import com.medvoll.api.domain.consulta.ConsultaRepository;
import com.medvoll.api.domain.consulta.DadosAgendamentoConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dados){
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Medico ja possui outra consulta agendada nesse mesmo horario.");
        }
    }
}
