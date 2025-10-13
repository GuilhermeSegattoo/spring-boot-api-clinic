package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndData(Long idMedico, java.time.LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, java.time.LocalDateTime primeiroHorario, java.time.LocalDateTime ultimoHorario);
    
}
