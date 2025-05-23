package controller;

import dao.HistoricoBuscaDAO;
import dao.MusicaDAO;
import model.Musica;

import java.sql.Connection;
import java.util.List;

public class MusicaController {
    private MusicaDAO musicaDAO;
    private HistoricoBuscaDAO historicoBuscaDAO;

    // construtor que inicializa os DAOs com a conexão
    public MusicaController(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
        this.historicoBuscaDAO = new HistoricoBuscaDAO(connection);
    }

    // cadastra uma nova música no banco
    public boolean cadastrarMusica(String nome, String artista, String genero) {
        Musica musica = new Musica(0, nome, artista, genero); // cria a música com id 0 (gerado no banco)
        return musicaDAO.create(musica);
    }

    // retorna a lista de todas as músicas cadastradas
    public List<Musica> listarMusicas() {
        return musicaDAO.read();
    }

    // remove uma música pelo id
    public boolean removerMusica(int id) {
        return musicaDAO.delete(id);
    }

    // busca músicas pelo termo e registra a busca no histórico
    public List<Musica> buscarMusicas(int idUsuario, String termoBusca) {
        try {
            historicoBuscaDAO.registrarBusca(idUsuario, termoBusca); // registra a busca
        } catch (Exception e) {
            System.out.println("erro ao registrar busca no histórico: " + e.getMessage());
        }
        return musicaDAO.buscarMusicas(termoBusca); // retorna as músicas encontradas
    }
}
