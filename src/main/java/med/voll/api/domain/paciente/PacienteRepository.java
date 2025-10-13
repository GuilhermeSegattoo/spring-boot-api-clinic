package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Pacientes, Long> {
    Page<Pacientes> findByAtivoTrue(Pageable paginacao);

    Boolean findAtivoById(Long id);
}