# Global Solution 2025 - API de Monitoramento de Saúde Mental
![Status: Concluído](https://img.shields.io/badge/Status-Conclu%C3%ADdo-brightgreen)

Projeto desenvolvido para a Global Solution 2025 da FIAP, no curso de Engenharia de Software (Turma 2ESPG), disciplina de Domain Driven Design - Java.

## 1. Contexto e Problema

A saúde mental e o bem-estar tornaram-se prioridades globais, especialmente no contexto pós-pandêmico e com a ascensão de novos modelos de trabalho. [cite_start]O aumento de casos de burnout, ansiedade e estresse exigem novas soluções[cite: 12]. [cite_start]A tecnologia pode ser uma aliada poderosa, oferecendo ferramentas que ajudam os indivíduos a monitorarem seu estado emocional[cite: 13].

[cite_start]Esta API RESTful é o **back-end** para uma plataforma de monitoramento de saúde mental, focada no registro de dados do paciente[cite: 14].

## 2. Conexão com o Tema (Futuro do Trabalho e ODS)

Este projeto se conecta ao tema "Futuro do Trabalho" e às ODS (Objetivos de Desenvolvimento Sustentável) da ONU:

* [cite_start]**O Futuro do Trabalho:** A API serve como base para ferramentas de monitoramento de bem-estar[cite: 33]. Em um cenário de trabalho híbrido ou remoto, onde a observação presencial é limitada, essas ferramentas são essenciais para que empresas e indivíduos possam identificar padrões de estresse e ansiedade, prevenindo o *burnout*.
* [cite_start]**ODS 3 (Saúde e Bem-Estar):** A solução ataca diretamente a meta de promover a saúde mental e o bem-estar[cite: 98].
* **ODS 8 (Trabalho Decente e Crescimento Econômico):** A API contribui para a **Meta 8.8**, que visa "proteger os direitos trabalhistas e promover ambientes de trabalho seguros". Um ambiente que ignora a saúde mental não é um ambiente seguro. [cite_start]A plataforma permite a coleta de dados que podem fundamentar ações corporativas para melhorar as condições de trabalho[cite: 34].

## 3. Integrantes do Grupo

| Nome Completo | RM |
| :--- | :--- |
| (Nome do Aluno 1) | (RM do Aluno 1) |
| (Nome do Aluno 2) | (RM do Aluno 2) |
| (Nome do Aluno 3) | (RM do Aluno 3) |

## 4. Tecnologias Utilizadas

* [cite_start]**Java 17** (ou 21) [cite: 151]
* [cite_start]**Spring Boot 3.x.x** [cite: 151]
* **Spring Web:** Para criação de controllers RESTful.
* [cite_start]**Spring Data JPA:** Para persistência de dados[cite: 133].
* [cite_start]**H2 Database:** Banco de dados relacional em memória[cite: 65].
* **Maven:** Gerenciador de dependências.
* [cite_start]**Bean Validation:** Para validação de dados de entrada[cite: 25, 71].

---

## 5. Como Executar o Projeto

### Pré-requisitos
* Java 17 (ou 21) instalado.
* Maven instalado.

### Passos
1.  Clone este repositório:
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    cd seu-repositorio
    ```
2.  Execute a aplicação usando o Maven:
    ```bash
    mvn spring-boot:run
    ```
3.  [cite_start]A API estará disponível em `http://localhost:8080`[cite: 154].

### Configuração do Banco de Dados
* [cite_start]A API está configurada para usar o banco de dados em memória **H2**[cite: 31, 107].
* [cite_start]O banco é criado e populado automaticamente ao iniciar a aplicação (`spring.jpa.hibernate.ddl-auto=create`)[cite: 68].
* [cite_start]Os dados iniciais (seeds) são carregados a partir do arquivo `src/main/resources/import.sql`[cite: 66, 109].

---

## 6. Uso da API em um Ambiente Real

Esta API foi desenhada para ser o cérebro por trás de uma aplicação (seja web ou móvel). Ela não possui interface gráfica, apenas fornece os dados.

Em um **ambiente real**, ela seria usada da seguinte forma:

1.  **Aplicativo do Paciente (Front-end):**
    * Um usuário se cadastra (a API criaria um `Paciente` usando `POST /pacientes`).
    * Diariamente, o app perguntaria: "Como você se sente?". O usuário preencheria os dados (humor, sono, ansiedade).
    * O app, então, enviaria esses dados para a API usando `POST /registros`, associando o registro ao ID do paciente.
    * O app poderia exibir gráficos históricos para o usuário, buscando os dados através de `GET /registros/paciente/{pacienteId}`.

2.  **Dashboard do Profissional de Saúde:**
    * Um profissional de saúde (psicólogo, terapeuta) teria um dashboard.
    * Com a devida autorização (controles de acesso não implementados neste escopo), ele poderia monitorar seus pacientes.
    * O dashboard consultaria `GET /registros/paciente/{pacienteId}` para ver a evolução do paciente e identificar pontos de atenção.

3.  **Dashboard Corporativo (RH - Foco no Futuro do Trabalho):**
    * Uma empresa poderia usar a plataforma para monitorar o bem-estar de suas equipes (de forma **anônima**).
    * Um dashboard de RH poderia consumir dados agregados da API (ex: `GET /registros?dataInicio=...&dataFim=...`) para identificar se o nível médio de ansiedade de um departamento específico está aumentando, permitindo ações preventivas antes de um *burnout* coletivo.

---

## 7. Documentação da API (Endpoints)

[cite_start]A seguir estão os endpoints RESTful implementados, com exemplos de requisições[cite: 92, 154].

### Recurso: Paciente (`/pacientes`)

#### `GET /pacientes`
* **Descrição:** Lista todos os pacientes cadastrados.
* **Status de Sucesso:** `200 OK`

#### `GET /pacientes/{id}`
* **Descrição:** Busca um paciente específico pelo seu ID.
* **Status de Sucesso:** `200 OK`
* **Status de Erro:** `404 Not Found` (se o ID não existir)

#### `POST /pacientes`
* **Descrição:** Cria um novo paciente.
* **Status de Sucesso:** `201 Created`
* **Status de Erro:** `400 Bad Request` (se a validação falhar)
* **Exemplo de Payload (JSON):**
    ```json
    {
      "nome": "João da Silva",
      "email": "joao.silva@email.com",
      "dataNascimento": "1995-03-20"
    }
    ```

#### `PUT /pacientes/{id}`
* **Descrição:** Atualiza um paciente existente.
* **Status de Sucesso:** `200 OK`
* **Status de Erro:** `404 Not Found` (se o ID não existir), `400 Bad Request` (validação)
* **Exemplo de Payload (JSON):**
    ```json
    {
      "nome": "João da Silva Santos",
      "email": "joao.santos@email.com",
      "dataNascimento": "1995-03-20"
    }
    ```

#### `DELETE /pacientes/{id}`
* **Descrição:** Remove um paciente pelo ID.
* **Status de Sucesso:** `204 No Content`
* **Status de Erro:** `404 Not Found` (se o ID não existir)

---

### Recurso: Registro Diário (`/registros`)

#### `GET /registros`
* **Descrição:** Lista todos os registros diários de todos os pacientes.
* **Status de Sucesso:** `200 OK`

#### `GET /registros/{id}`
* **Descrição:** Busca um registro diário específico pelo seu ID.
* **Status de Sucesso:** `200 OK`
* **Status de Erro:** `404 Not Found` (se o ID não existir)

#### `POST /registros`
* **Descrição:** Cria um novo registro diário.
* **Status de Sucesso:** `201 Created`
* **Status de Erro:** `400 Bad Request` (validação), `404 Not Found` (se o `pacienteId` não existir)
* **Exemplo de Payload (JSON):**
    ```json
    {
      "dataRegistro": "2025-11-13T15:00:00",
      "nivelHumor": 3,
      "horasSono": 6.5,
      "nivelAnsiedade": 4,
      "anotacao": "Dia de muita pressão no trabalho.",
      "pacienteId": 1 
    }
    ```

#### `PUT /registros/{id}`
* **Descrição:** Atualiza um registro diário existente.
* **Status de Sucesso:** `200 OK`
* **Status de Erro:** `404 Not Found` (ID do registro ou `pacienteId`), `400 Bad Request`
* **Exemplo de Payload (JSON):**
    ```json
    {
      "dataRegistro": "2025-11-13T16:00:00",
      "nivelHumor": 4,
      "horasSono": 7.0,
      "nivelAnsiedade": 2,
      "anotacao": "Consegui relaxar após o trabalho.",
      "pacienteId": 1
    }
    ```

#### `DELETE /registros/{id}`
* **Descrição:** Remove um registro diário pelo ID.
* **Status de Sucesso:** `204 No Content`
* **Status de Erro:** `404 Not Found` (se o ID não existir)

### Endpoint Bônus

#### `GET /registros/paciente/{pacienteId}`
* **Descrição:** Lista todos os registros diários de um paciente específico.
* **Status de Sucesso:** `200 OK`
* **Status de Erro:** `404 Not Found` (se o `pacienteId` não existir)

## 8. Como Testar a API

[cite_start]Você pode testar todos os endpoints usando uma ferramenta como **Postman**, **Insomnia** ou `curl`[cite: 93].

1.  Inicie a aplicação (`mvn spring-boot:run`).
2.  Abra o Postman.
3.  Faça uma requisição `GET` para `http://localhost:8080/pacientes`.
4.  Você deverá ver a lista dos 10 pacientes criados pelo `import.sql` retornada em JSON.
5.  Teste os outros endpoints, como `POST /pacientes` ou `GET /registros/paciente/1`.
