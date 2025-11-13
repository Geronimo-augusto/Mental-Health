/* * =============================================
 * SEEDS PARA A TABELA DE PACIENTES (TB_PACIENTE)
 * =============================================
 * Colunas: (nm_paciente, ds_email, dt_nascimento)
 * Criando 10 pacientes.
 */

INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Ana Silva', 'ana.silva@email.com', '1990-05-15'); /* ID 1 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Bruno Costa', 'bruno.c@email.com', '1985-11-30'); /* ID 2 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Carla Dias', 'carla.dias@email.com', '2000-02-10'); /* ID 3 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Daniel Martins', 'daniel.m@email.com', '1995-07-20'); /* ID 4 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Elena Ferreira', 'elena.f@email.com', '1988-01-05'); /* ID 5 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Fábio Guedes', 'fabio.g@email.com', '1992-09-18'); /* ID 6 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Gabriela Lima', 'gabi.lima@email.com', '2001-03-22'); /* ID 7 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Hugo Nogueira', 'hugo.n@email.com', '1979-12-01'); /* ID 8 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('Isabela Moreira', 'isa.m@email.com', '1998-06-14'); /* ID 9 */
INSERT INTO TB_PACIENTE (nm_paciente, ds_email, dt_nascimento) VALUES ('João Pereira', 'joao.p@email.com', '1983-10-25'); /* ID 10 */


/* * =======================================================
 * SEEDS PARA A TABELA DE REGISTROS (TB_REGISTRO_DIARIO)
 * =======================================================
 * Colunas: (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id)
 * Criando 15 registros distribuídos entre os 10 pacientes.
 */

INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-10T09:00:00', 3, 7.5, 4, 'Reunião estressante hoje.', 1);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-11T08:30:00', 4, 8.0, 2, 'Dormi bem, me sinto descansada.', 1);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-12T09:15:00', 2, 6.0, 5, 'Dificuldade para dormir, muito ansiosa com o trabalho.', 1);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-10T10:00:00', 2, 5.0, 5, 'Muita ansiedade com o projeto novo.', 2);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-11T10:15:00', 3, 6.5, 3, 'Consegui focar um pouco mais hoje.', 2);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-11T12:00:00', 5, 8.0, 1, 'Dia excelente! Consegui completar todas as tarefas.', 3);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-11T14:00:00', 4, 7.0, 2, 'Me sentindo produtivo.', 4);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-12T14:30:00', 3, 7.5, 3, 'Dia normal, sem grandes picos de estresse.', 4);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-12T08:00:00', 2, 5.5, 4, 'Acordei cansada.', 5);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-10T22:00:00', 3, 6.0, 3, 'OK.', 6);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-11T21:00:00', 2, 7.0, 4, 'Muita pressão por resultados.', 6);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-12T13:00:00', 5, 8.0, 1, 'Sinto que estou me adaptando bem.', 7);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-12T07:00:00', 3, 6.0, 3, 'Mais um dia.', 8);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-11T11:00:00', 4, 7.0, 2, 'Consegui fazer uma pausa para meditação.', 9);
INSERT INTO TB_REGISTRO_DIARIO (dt_registro, nr_nivel_humor, qt_horas_sono, nr_nivel_ansiedade, ds_anotacao, paciente_id) VALUES ('2025-11-10T23:00:00', 2, 4.0, 5, 'Insônia. Não consigo parar de pensar nas entregas.', 10);