package controller;

import dao.HistoricoBuscaDAO;
import dao.MusicaDAO;
import model.Musica;

import java.sql.Connection;
import java.util.List;

public class MusicaController {
    private MusicaDAO musicaDAO;
    private HistoricoBuscaDAO historicoBuscaDAO;

    public MusicaController(Connection connection) {
        this.musicaDAO = new MusicaDAO(connection);
        this.historicoBuscaDAO = new HistoricoBuscaDAO(connection);
    }

    public boolean cadastrarMusica(String nome, String artista, String genero) {
        Musica musica = new Musica(0, nome, artista, genero);
        return musicaDAO.create(musica);
    }

    public List<Musica> listarMusicas() {
        return musicaDAO.read();
    }

    public boolean removerMusica(int id) {
        return musicaDAO.delete(id);
    }

    //Função para buscar músicas e registrar no histórico
    public List<Musica> buscarMusicas(int idUsuario, String termoBusca) {
        try {
            historicoBuscaDAO.registrarBusca(idUsuario, termoBusca);
        } catch (Exception e) {
            System.out.println("Erro ao registrar busca no histórico: " + e.getMessage());
        }
        return musicaDAO.buscarMusicas(termoBusca);
    }
}
