package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {
    
    @Autowired
    private MedicoRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoOptional = repository.findById(dados.idMedico());
        if (medicoOptional.isEmpty() || !medicoOptional.get().getAtivo()) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído ou inativo");
        }
}

}