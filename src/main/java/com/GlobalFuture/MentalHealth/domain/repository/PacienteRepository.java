package com.GlobalFuture.MentalHealth.domain.repository;


import com.GlobalFuture.MentalHealth.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório para a entidade Paciente.
 * Estende JpaRepository para obter métodos CRUD básicos (save, findById, findAll, delete).
 */
@Repository // Anotação opcional, mas boa prática
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Você pode adicionar consultas customizadas aqui (ex: findByEmail)
}
