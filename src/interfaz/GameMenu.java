package interfaz;

import invocaciones.Invocacion;
import logica.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameMenu {

    public JPanel panelGameMenu;
    private JButton buttonInvocar;
    private JButton buttonInventario;
    private JButton buttonCamoDeBatalla;
    private JButton buttonTrascender;
    private JButton buttonLogros;
    private JButton buttonSalir;

    private JScrollPane scrollPaneInvocaciones;
    private JPanel panelInvocaciones;

    public GameMenu() {

        scrollPaneInvocaciones.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneInvocaciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneInvocaciones.getVerticalScrollBar().setUnitIncrement(18);

        mostrarInvocaciones(Main.inventarioInvocaciones);

        buttonInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelGameMenu);
                frame.setContentPane(new Inventario().panelInventario);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public static void main(String[] args) {

        Main.idUsuario = 1;
        Main.inicializarJuego();

        JFrame frame = new JFrame("GameMenu");
        frame.setContentPane(new GameMenu().panelGameMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void mostrarInvocaciones(ArrayList<Invocacion> arrayInvocaciones) {
        panelInvocaciones.removeAll();
        panelInvocaciones.setLayout(new BoxLayout(panelInvocaciones, BoxLayout.Y_AXIS));
        panelInvocaciones.setBackground(new Color(55, 134, 219));
        panelInvocaciones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Invocacion invocacion : arrayInvocaciones) {
            JPanel tarjeta = crearTarjetaInvocacion(invocacion);
            tarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelInvocaciones.add(tarjeta);
            panelInvocaciones.add(Box.createVerticalStrut(20));
        }

        panelInvocaciones.revalidate();
        panelInvocaciones.repaint();

        scrollPaneInvocaciones.revalidate();
        scrollPaneInvocaciones.repaint();
    }

    public static JPanel crearTarjetaInvocacion(Invocacion invocacion) {

        Color colorBorde = obtenerColorRareza(invocacion.getRareza());

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new GridLayout(0, 1, 5, 5));
        tarjeta.setPreferredSize(new Dimension(240, 330));
        tarjeta.setMaximumSize(new Dimension(240, 330));
        tarjeta.setMinimumSize(new Dimension(240, 330));
        tarjeta.setBackground(new Color(10, 37, 56));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 3),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        JLabel labelRaza = new JLabel(invocacion.getRaza());
        labelRaza.setForeground(Color.WHITE);
        labelRaza.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel labelRareza = new JLabel(invocacion.getRareza());
        labelRareza.setForeground(colorBorde);
        labelRareza.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelNivel = new JLabel("Nv. " + invocacion.getNivel());
        labelNivel.setForeground(Color.WHITE);
        labelNivel.setFont(new Font("Arial", Font.BOLD, 14));

        JProgressBar barraExperiencia = new JProgressBar(0, (int) invocacion.getExperienciaMaxima());
        barraExperiencia.setValue((int) invocacion.getExperiencia());
        barraExperiencia.setStringPainted(true);
        barraExperiencia.setString((int) invocacion.getExperiencia() + "/" + (int) invocacion.getExperienciaMaxima());
        barraExperiencia.setForeground(colorBorde);
        barraExperiencia.setBackground(new Color(45, 55, 65));
        barraExperiencia.setFont(new Font("Arial", Font.BOLD, 12));

        JProgressBar barraVida = new JProgressBar(0, (int) invocacion.getVidaMaxima());
        barraVida.setValue((int) invocacion.getVida());
        barraVida.setStringPainted(true);
        barraVida.setString((int) invocacion.getVida() + "/" + (int) invocacion.getVidaMaxima());
        barraVida.setForeground(new Color(220, 60, 60));
        barraVida.setBackground(new Color(45, 55, 65));
        barraVida.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel labelDefensa = new JLabel("Defensa: " + invocacion.getDefensa());
        JLabel labelAtaque = new JLabel("Ataque: " + invocacion.getAtaque());
        JLabel labelCritico = new JLabel("Crit%: " + invocacion.getProbCritico());
        JLabel labelDanoCritico = new JLabel("Crit DMG: " + invocacion.getDañoCritico());

        labelDefensa.setForeground(Color.WHITE);
        labelAtaque.setForeground(Color.WHITE);
        labelCritico.setForeground(Color.WHITE);
        labelDanoCritico.setForeground(Color.WHITE);

        labelDefensa.setFont(new Font("Arial", Font.BOLD, 13));
        labelAtaque.setFont(new Font("Arial", Font.BOLD, 13));
        labelCritico.setFont(new Font("Arial", Font.BOLD, 13));
        labelDanoCritico.setFont(new Font("Arial", Font.BOLD, 13));

        JButton buttonUsar = new JButton("Usar");
        JButton buttonEliminar = new JButton("Eliminar");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(10, 37, 56));
        panelBotones.add(buttonUsar);
        panelBotones.add(buttonEliminar);

        tarjeta.add(labelRaza);
        tarjeta.add(labelRareza);
        tarjeta.add(new JSeparator());
        tarjeta.add(labelNivel);
        tarjeta.add(barraExperiencia);
        tarjeta.add(barraVida);
        tarjeta.add(labelDefensa);
        tarjeta.add(labelAtaque);
        tarjeta.add(labelCritico);
        tarjeta.add(labelDanoCritico);
        tarjeta.add(panelBotones);

        return tarjeta;
    }

    public static Color obtenerColorRareza(String rareza) {

        Color color = Color.WHITE;

        switch (rareza) {
            case "Comun":
                color = Color.WHITE;
                break;

            case "Común":
                color = Color.WHITE;
                break;

            case "Natural":
                color = Color.GREEN;
                break;

            case "Raro":
                color = new Color(55, 134, 219);
                break;

            case "Unico":
                color = new Color(253, 207, 98);
                break;

            case "Único":
                color = new Color(253, 207, 98);
                break;

            case "Extinto":
                color = new Color(255, 138, 31);
                break;

            case "Primordial":
                color = new Color(255, 77, 94);
                break;
        }

        return color;
    }
}