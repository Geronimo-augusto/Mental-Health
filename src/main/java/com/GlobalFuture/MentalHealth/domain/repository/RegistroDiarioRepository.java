package com.GlobalFuture.MentalHealth.domain.repository;


import com.GlobalFuture.MentalHealth.domain.model.RegistroDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface de repositório para a entidade RegistroDiario.
 */
@Repository
public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long> {

    // Exemplo de consulta customizada (bônus) [cite: 122]
    // Encontra todos os registros de um paciente específico
    List<RegistroDiario> findByPacienteId(Long pacienteId);
}