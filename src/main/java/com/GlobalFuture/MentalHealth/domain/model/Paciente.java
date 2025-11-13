package com.GlobalFuture.MentalHealth.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_PACIENTE") // Define o nome da tabela no banco
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Deixa o banco gerenciar o ID auto-incremental
    private Long id;

    @NotBlank(message = "O nome é obrigatório") // [cite: 72, 75]
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres") // [cite: 72]
    @Column(name = "nm_paciente", length = 100) // Define o nome da coluna
    private String nome;

    @Email(message = "Formato de e-mail inválido") // [cite: 72, 75]
    @NotBlank(message = "O e-mail é obrigatório") // [cite: 72]
    @Column(name = "ds_email", unique = true, length = 100) // Garante que o email seja único
    private String email;

    @NotNull(message = "A data de nascimento é obrigatória")
    @PastOrPresent(message = "A data de nascimento deve estar no passado ou presente") // [cite: 72, 75]
    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

    /**
     * Relacionamento: Um Paciente pode ter muitos Registros Diários.
     * 'mappedBy = "paciente"' indica que o lado 'Paciente' é o inverso
     * e o lado 'RegistroDiario' (o campo 'paciente') gerencia o relacionamento.
     * 'cascade = CascadeType.ALL' faz com que os registros sejam deletados se o paciente for.
     * 'orphanRemoval = true' remove registros do banco se forem removidos da lista.
     */
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RegistroDiario> registros;

    // --- Construtores ---
    /**
     * Construtor padrão (vazio) exigido pelo JPA.
     */
    public Paciente() {
    }

    // Construtor com campos (útil para testes ou seeds)
    public Paciente(String nome, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // --- Getters e Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<RegistroDiario> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroDiario> registros) {
        this.registros = registros;
    }
}
