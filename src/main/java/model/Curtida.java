package model;

import java.sql.Timestamp;

public class Curtida {
    private int id;
    private int idUsuario;
    private int idMusica;
    private Timestamp dataCurtida;
    private String tituloMusica; // para exibir no histórico

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdMusica() { return idMusica; }
    public void setIdMusica(int idMusica) { this.idMusica = idMusica; }

    public Timestamp getDataCurtida() { return dataCurtida; }
    public void setDataCurtida(Timestamp dataCurtida) { this.dataCurtida = dataCurtida; }

    public String getTituloMusica() { return tituloMusica; }
    public void setTituloMusica(String tituloMusica) { this.tituloMusica = tituloMusica; }
}

