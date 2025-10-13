package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Pacientes, Long> {
    Page<Pacientes> findByAtivoTrue(Pageable paginacao);

    @Query("SELECT p.ativo FROM Paciente p WHERE p.id = :id")
    Boolean findAtivoById(Long id);
}