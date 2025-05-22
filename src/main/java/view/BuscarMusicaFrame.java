package view;

import controller.MusicaController;
import model.Musica;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class BuscarMusicaFrame extends JFrame {

    private JTextField campoBusca;
    private JButton botaoBuscar;
    private JTextArea areaResultado;

    private MusicaController musicaController;
    private int idUsuario;

    public BuscarMusicaFrame(Connection conexao, int idUsuario) {
        this.musicaController = new MusicaController(conexao);
        this.idUsuario = idUsuario;

        setTitle("Buscar Músicas");
        setSize(400, 300);
        setLayout(new BorderLayout());

        campoBusca = new JTextField();
        botaoBuscar = new JButton("Buscar");
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(campoBusca, BorderLayout.CENTER);
        painelTopo.add(botaoBuscar, BorderLayout.EAST);

        add(painelTopo, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        botaoBuscar.addActionListener(e -> {
            String termo = campoBusca.getText();
            List<Musica> musicas = musicaController.buscarMusicas(idUsuario, termo);
            areaResultado.setText("");

            if (musicas.isEmpty()) {
                areaResultado.setText("Nenhuma música encontrada.");
            } else {
                for (Musica m : musicas) {
                    areaResultado.append(m.getNome() + " - " + m.getArtista() + " (" + m.getGenero() + ")\n");
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
