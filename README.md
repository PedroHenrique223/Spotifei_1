#  Spotifei

Spotifei é um sistema de gerenciamento de músicas, playlists e histórico de execução, desenvolvido como projeto acadêmico utilizando **Java**, **Swing** para a interface gráfica e **PostgreSQL** como banco de dados.

---

## Funcionalidades

- Cadastro e Login de Usuários
- Cadastro de Músicas (com informações de título, artista, álbum, ano e gênero)
- Cadastro de Artistas (com nome e gênero)
- Criação e Gerenciamento de Playlists
- Adicionar e Remover Músicas das Playlists
- Curtir e Descurtir Músicas
- Histórico de Execução das músicas ouvidas
- Busca de Músicas com histórico das pesquisas

---

## Tecnologias Utilizadas

- **Java** - Linguagem principal
- **Swing** - Interface gráfica (JFrame)
- **JDBC** - Conexão com banco de dados
- **PostgreSQL** - Banco de dados relacional

---

## Estrutura do Projeto

```text
src/
  controller/
    PlaylistController.java
    MusicaController.java
    UsuarioController.java
    ArtistaController.java
  dao/
    Conexao.java
    PlaylistDAO.java
    MusicaDAO.java
    UsuarioDAO.java
    ArtistaDAO.java
    HistoricoDAO.java
  model/
    Playlist.java
    Musica.java
    Usuario.java
    Artista.java
    Historico.java
  view/
    DashboardFrame.java
    LoginFrame.java
    RegisterFrame.java
    MusicaFrame.java
    PlaylistFrame.java
    ArtistaFrame.java
    HistoricoFrame.java
    BuscarMusicaFrame.java
```

## Banco de Dados

O banco de dados **Spotifei_1** possui as seguintes tabelas principais:

- **usuario**
- **musica**
- **artista**
- **playlist**
- **playlist_musica**
- **historico**

### Exemplo de tabela `artista`:

```sql
CREATE TABLE artista (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    genero VARCHAR(50)
);
