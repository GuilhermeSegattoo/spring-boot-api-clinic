package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisFechamentoClinica = dataConsulta.getHour() > 18;

        if (domingo || antesAberturaClinica || depoisFechamentoClinica) {
            throw new RuntimeException("Consulta fora do horário de funcionamento da clínica");
        }

    }

}
