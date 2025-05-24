# Relatório do Projeto Spotifei

## 1. Introdução
O presente relatório tem como objetivo apresentar o desenvolvimento do projeto **Spotifei**, um sistema de gerenciamento de músicas, playlists e histórico de execução.  
O projeto foi idealizado como parte das atividades práticas do curso de Ciência da Computação, com foco na aplicação de conceitos de desenvolvimento de software, banco de dados e interface gráfica.

O Spotifei foi desenvolvido utilizando a linguagem de programação **Java**, com a biblioteca gráfica **Swing** para a construção da interface do usuário.  
A persistência de dados foi realizada por meio do banco de dados relacional **PostgreSQL**, utilizando a API **JDBC** para a comunicação entre a aplicação e o banco.

---

## 2. Objetivos

O desenvolvimento do Spotifei teve como principais objetivos:

- Implementar um sistema completo de gerenciamento de músicas, playlists e histórico de execução.
- Aplicar conceitos de Programação Orientada a Objetos (POO) com a linguagem Java.
- Utilizar o padrão de arquitetura MVC (Model-View-Controller) para a organização do projeto.
- Desenvolver uma interface gráfica interativa utilizando Java Swing.
- Realizar a integração de sistemas com o banco de dados relacional PostgreSQL por meio do JDBC.
- Consolidar conhecimentos em modelagem de dados e operações CRUD (Create, Read, Update, Delete).

---

## 3. Tecnologias Utilizadas

- **Java**: Linguagem de programação principal utilizada para implementar a lógica de negócio, as entidades, os controladores e as interfaces gráficas.
- **Swing**: Biblioteca gráfica utilizada para o desenvolvimento das interfaces do usuário.
- **JDBC**: API utilizada para realizar a conexão e manipulação dos dados no banco PostgreSQL.
- **PostgreSQL**: Sistema Gerenciador de Banco de Dados Relacional (SGBD) utilizado para armazenar todas as informações persistentes do sistema.
- **Apache NetBeans**: Ambiente de Desenvolvimento Integrado (IDE) utilizado na implementação e execução do projeto.

---

## 4. Funcionalidades Implementadas

O sistema Spotifei contempla as seguintes funcionalidades principais:

- Cadastro e autenticação de usuários.
- Cadastro, atualização e remoção de músicas com informações como título, artista, álbum, ano e gênero.
- Cadastro de artistas e associação com as músicas.
- Criação e gerenciamento de playlists personalizadas.
- Adição e remoção de músicas nas playlists.
- Sistema de curtidas em músicas.
- Histórico de execução, permitindo rastrear as músicas ouvidas pelos usuários.
- Sistema de busca com registro do histórico de pesquisas realizadas.

---

## 5. Estrutura do Projeto

O projeto foi estruturado utilizando o padrão **MVC**, dividindo as classes em pacotes específicos, conforme a responsabilidade de cada uma:

- **Controller**: responsável pelo controle de fluxo entre a interface e o modelo de dados, contendo classes como `PlaylistController`, `MusicaController`, `UsuarioController` e `ArtistaController`.
- **DAO (Data Access Object)**: responsável pela comunicação com o banco de dados, contendo classes como `Conexao`, `PlaylistDAO`, `MusicaDAO`, `UsuarioDAO`, `ArtistaDAO` e `HistoricoDAO`.
- **Model**: responsável pela representação das entidades do sistema, contendo classes como `Playlist`, `Musica`, `Usuario`, `Artista` e `Historico`.
- **View**: responsável pela interface gráfica com o usuário, contendo classes como `DashboardFrame`, `LoginFrame`, `RegisterFrame`, `MusicaFrame`, `PlaylistFrame`, `ArtistaFrame`, `HistoricoFrame` e `BuscarMusicaFrame`.

Essa organização permite uma melhor manutenção, reutilização de código e separação clara das responsabilidades.

---

## 6. Modelagem do Banco de Dados

O banco de dados relacional utilizado foi o **PostgreSQL**, com o nome `Spotifei_1`.  
A modelagem seguiu os princípios de normalização, garantindo a integridade e consistência dos dados.

As principais tabelas criadas foram:

- `usuario`: responsável pelo armazenamento dos dados de autenticação dos usuários.
- `musica`: responsável pelo armazenamento das informações das músicas cadastradas.
- `artista`: responsável pelo armazenamento dos dados dos artistas.
- `playlist`: responsável pelo armazenamento das playlists criadas pelos usuários.
- `playlist_musica`: responsável pela relação entre playlists e músicas.
- `historico`: responsável pelo armazenamento do histórico de execução das músicas.

**Exemplo de comando SQL para criação da tabela `artista`:**

```sql
CREATE TABLE artista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    genero VARCHAR(50)
);
```
## 7. Resultados Obtidos

O desenvolvimento do Spotifei proporcionou a consolidação de diversos conhecimentos adquiridos ao longo do curso, tais como:

- A prática da Programação Orientada a Objetos.
- O uso do padrão MVC na organização de sistemas.
- A integração entre a aplicação Java e o banco de dados relacional.
- A criação de interfaces gráficas utilizando a biblioteca Swing.
- A modelagem de bancos de dados relacionais e a execução de comandos SQL.

O sistema se mostrou funcional, atendendo aos requisitos definidos e apresentando uma interface gráfica intuitiva e amigável ao usuário.

## 8. Conclusão

O projeto Spotifei alcançou os objetivos propostos, sendo uma experiência enriquecedora no desenvolvimento de sistemas completos que envolvem múltiplas camadas de tecnologia.  
A aplicação prática de conceitos teóricos foi essencial para o amadurecimento das habilidades em programação, modelagem de dados e desenvolvimento de interfaces gráficas.

Além disso, o projeto demonstrou a importância da utilização de boas práticas de desenvolvimento, como a separação de responsabilidades e a adoção de padrões arquiteturais, contribuindo para a criação de um sistema bem estruturado e de fácil manutenção.

