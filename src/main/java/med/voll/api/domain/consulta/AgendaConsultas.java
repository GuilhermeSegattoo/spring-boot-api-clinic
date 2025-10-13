package med.voll.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.Medicos;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaConsultas {
    
    @Autowired
    private ConsultaRepository repository;
    
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

  public void agendar(DadosAgendamentoConsulta dados) {
    // Lógica para agendar a consulta

    if(!pacienteRepository.existsById(dados.idPaciente())){
        throw new RuntimeException("ID do paciente informado não existe!");
    }

    if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
        throw new RuntimeException("ID do médico informado não existe!");
    }

    var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
    var medico = escolherMedico(dados);

    var consulta = new Consulta(null, medico, paciente, dados.data(), null);
    repository.save(consulta);
  }

  private Medicos escolherMedico(DadosAgendamentoConsulta dados) {
    if (dados.idMedico() != null) {
      return medicoRepository.getReferenceById(dados.idMedico());
    }
    if (dados.especialidade() == null) {
      throw new RuntimeException("Especialidade é obrigatória quando o médico não for escolhido.");
    }

    return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade() ,dados.data());
  }

  public void cancelar(DadosCancelamentoConsulta dados){
    if(!repository.existsById(dados.id())){
        throw new RuntimeException("ID da consulta informado não existe!");
    }

     var consulta = repository.getReferenceById(dados.id());
     consulta.cancelar(dados.motivo());
  }

 

}
