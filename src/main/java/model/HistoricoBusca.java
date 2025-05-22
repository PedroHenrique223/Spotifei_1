package model;

import java.sql.Timestamp;

public class HistoricoBusca {
    private int id;
    private int idUsuario;
    private String termoBusca;
    private Timestamp dataBusca;

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getTermoBusca() { return termoBusca; }
    public void setTermoBusca(String termoBusca) { this.termoBusca = termoBusca; }

    public Timestamp getDataBusca() { return dataBusca; }
    public void setDataBusca(Timestamp dataBusca) { this.dataBusca = dataBusca; }
}
