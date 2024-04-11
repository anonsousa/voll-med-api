package com.medvoll.api.domain.consulta.validacoes.agendamento;

import com.medvoll.api.domain.ValidacaoException;
import com.medvoll.api.domain.consulta.ConsultaRepository;
import com.medvoll.api.domain.consulta.DadosAgendamentoConsultaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente ja possui uma consulta agendada nesse dia");
        }
    }
}
