# FEItv

FEItv é uma plataforma de informações sobre vídeos desenvolvida como projeto da disciplina CCM310 da FEI. Inspirada em serviços de streaming como YouTube e Netflix, a FEItv permite que usuários pesquisem, curtam e favoritam filmes e séries — sem reproduzir os vídeos.

---

## Tecnologias

- **Java** — linguagem principal
- **Java Swing** — interface gráfica (GUI)
- **JDBC + PostgreSQL** — conexão e persistência de dados
- **MVC (Model-View-Controller)** — padrão arquitetural

Todos os dados são persistidos no banco de dados PostgreSQL, ou seja, nenhuma informação é perdida ao fechar o programa.

---

## Funcionalidades

### Usuário
- Cadastrar nova conta
- Realizar login com conta existente
- Buscar vídeos pelo nome (pesquisa por substring)
- Visualizar informações detalhadas de filmes e séries
- Curtir e descurtir vídeos
- Gerenciar lista de favoritos: adicionar e remover vídeos

---

## Arquitetura

O projeto segue o padrão MVC:

- **Model** — classes Java que representam o domínio (`Video`, `Filme`, `Serie`, `Usuario`, etc.)
- **View** — JFrames Swing para cada tela (login, resultados de pesquisa, favoritos, etc.)
- **Controller** — classes intermediárias que tratam as ações do usuário e se comunicam com a camada DAO

### Diagrama de Classes

```
         Video  (classe abstrata)
        /      \
    Filme      Serie
                |
           <<interface>>
            Situacao

    Usuario
```

---

## Banco de Dados

| Tabela | Descrição |
|---|---|
| `tbvideos` | Armazena todos os vídeos (filmes e séries), com coluna `tipo` para distingui-los |
| `tbfilmes` | Dados específicos de filmes (ligada a `tbvideos` pelo `id`) |
| `tbseries` | Dados específicos de séries: número de temporadas, episódios e situação |
| `tbcurtidas` | Armazena curtidas — chave primária composta de `usuario` + `id` (vídeo) |
| `tbfavoritos` | Armazena favoritos — chave primária composta de `usuario` + `id` (vídeo) |

---

## Como Executar

1. Certifique-se de que o PostgreSQL está instalado e em execução
2. Crie o banco de dados e execute os scripts de criação das tabelas (backup123)
3. Configure a conexão na classe `Conexao` com suas credenciais do banco
4. Abra o projeto na sua IDE (NetBeans recomendado)
5. Execute a classe principal para iniciar a aplicação

---

## Estrutura do Projeto

```
src/
├── model/          # Classes de domínio (Video, Filme, Serie, Usuario...)
├── view/           # JFrames Swing (TelaLogin, TelaLogado, TelaResultados...)
├── controller/     # Controladores de cada tela
└── dao/            # Data Access Objects (VideoDAO, UsuarioDAO...)
```

---

## Informações Acadêmicas

- **Disciplina:** CCM310
- **Instituição:** FEI
- **Professores:** Profa. Gabriela Biondi, Prof. Isaac de Jesus
