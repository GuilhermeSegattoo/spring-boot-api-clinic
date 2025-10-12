package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DTOMedicos(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
    public DTOMedicos(Medicos medicos){
        this(medicos.getId(), medicos.getNome(), medicos.getEmail(), medicos.getTelefone(), medicos.getCrm(), medicos.getEspecialidade(), medicos.getEndereco());
    }
}
