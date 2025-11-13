package com.GlobalFuture.MentalHealth.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_REGISTRO_DIARIO") // Define o nome da tabela
public class RegistroDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data do registro é obrigatória") //
    @PastOrPresent(message = "A data do registro não pode ser futura") //
    @Column(name = "dt_registro")
    private LocalDateTime dataRegistro;

    @NotNull(message = "O nível de humor é obrigatório")
    @Min(value = 1, message = "Nível de humor deve ser no mínimo 1") //
    @Max(value = 5, message = "Nível de humor deve ser no máximo 5") //
    @Column(name = "nr_nivel_humor")
    private Integer nivelHumor;

    @Min(value = 0, message = "Horas de sono não podem ser negativas")
    @Column(name = "qt_horas_sono")
    private Double horasSono;

    @NotNull(message = "O nível de ansiedade é obrigatório")
    @Min(value = 1, message = "Nível de ansiedade deve ser no mínimo 1")
    @Max(value = 5, message = "Nível de ansiedade deve ser no máximo 5")
    @Column(name = "nr_nivel_ansiedade")
    private Integer nivelAnsiedade;

    @Column(name = "ds_anotacao", length = 500) // Anotação opcional
    private String anotacao;

    // --- Relacionamento ---
    /**
     * Relacionamento: Muitos Registros Diários pertencem a Um Paciente.
     * Esta é a "owning side" (lado dono) do relacionamento.
     * 'fetch = FetchType.LAZY' é uma otimização para não carregar o paciente
     * do banco a menos que seja explicitamente solicitado.
     * '@JoinColumn' define qual coluna nesta tabela (TB_REGISTRO_DIARIO)
     * armazena a chave estrangeira (o ID do paciente).
     */
    @ManyToOne(fetch = FetchType.LAZY) //
    @JoinColumn(name = "paciente_id", nullable = false) // 'nullable = false' garante que um registro sempre tenha um paciente
    private Paciente paciente;

    // --- Construtores ---
    /**
     * Construtor padrão (vazio) exigido pelo JPA.
     */
    public RegistroDiario() {
    }

    // Construtor com campos (útil para testes ou seeds)
    public RegistroDiario(LocalDateTime dataRegistro, Integer nivelHumor, Double horasSono, Integer nivelAnsiedade, Paciente paciente) {
        this.dataRegistro = dataRegistro;
        this.nivelHumor = nivelHumor;
        this.horasSono = horasSono;
        this.nivelAnsiedade = nivelAnsiedade;
        this.paciente = paciente;
    }

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Integer getNivelHumor() {
        return nivelHumor;
    }

    public void setNivelHumor(Integer nivelHumor) {
        this.nivelHumor = nivelHumor;
    }

    public Double getHorasSono() {
        return horasSono;
    }

    public void setHorasSono(Double horasSono) {
        this.horasSono = horasSono;
    }

    public Integer getNivelAnsiedade() {
        return nivelAnsiedade;
    }

    public void setNivelAnsiedade(Integer nivelAnsiedade) {
        this.nivelAnsiedade = nivelAnsiedade;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
