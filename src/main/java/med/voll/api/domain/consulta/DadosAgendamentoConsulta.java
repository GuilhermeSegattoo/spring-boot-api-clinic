package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

public record DadosAgendamentoConsulta(
    Long idMedico,
    @NotNull
    Long idPaciente,
    @NotNull
    @Future
    LocalDateTime data,
    Especialidade especialidade
) {
}
