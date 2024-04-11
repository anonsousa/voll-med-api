package com.medvoll.api.domain.consulta.validacoes.cancelamento;

import com.medvoll.api.domain.ValidacaoException;
import com.medvoll.api.domain.consulta.ConsultaRepository;
import com.medvoll.api.domain.consulta.DadosAgendamentoConsultaDto;
import com.medvoll.api.domain.consulta.DadosCancelamentoConsultaDto;
import com.medvoll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosCancelamentoConsultaDto dados){
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if(diferencaEmHoras < 24){
            throw new ValidacaoException("Consulta deve ser cancelada com antecedencia minima de 24 horas!");
        }
    }
}
