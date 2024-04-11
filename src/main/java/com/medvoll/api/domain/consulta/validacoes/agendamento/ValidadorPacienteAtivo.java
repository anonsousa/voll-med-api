package com.medvoll.api.domain.consulta.validacoes.agendamento;

import com.medvoll.api.domain.ValidacaoException;
import com.medvoll.api.domain.consulta.DadosAgendamentoConsultaDto;
import com.medvoll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{
    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsultaDto dados){
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta nao pode ser agendada com paciente excluido");
        }
    }
}
