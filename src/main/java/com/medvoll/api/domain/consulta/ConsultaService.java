package com.medvoll.api.domain.consulta;

import com.medvoll.api.domain.ValidacaoException;
import com.medvoll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import com.medvoll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import com.medvoll.api.domain.medico.Medico;
import com.medvoll.api.domain.medico.MedicoRepository;
import com.medvoll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;
    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;



    public DadosDetalhamentoConsultaDto agendar(DadosAgendamentoConsultaDto dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado nao existe");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw  new ValidacaoException("Id do medico informado nao existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if(medico == null){
            throw  new ValidacaoException("Nao existe medico disponivel nessa data");
        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsultaDto(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDto dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade e obrigado quando o medico nao for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsultaDto dados){
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Id da consulta nao existe.");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
