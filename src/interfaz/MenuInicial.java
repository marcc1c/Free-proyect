package interfaz;

import invocaciones.*;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial {

    private Gacha gacha = new Gacha();
    private Main main = new Main();

    private JButton botonInvocar;
    private JButton botonInventario;
    private JButton botonCampoBatalla;
    private JButton botonSalir;
    public JPanel panelMenuInicial;
    private JButton botonTrascender;
    private JButton botonLogros;
    private JPanel panelDerecha;
    private JScrollPane scrollInventario;
    private JPanel ventanaInventario;
    private JTextArea textAreaInventario;

    public MenuInicial() {
        ventanaInventario.setLayout(new BoxLayout(ventanaInventario, BoxLayout.Y_AXIS));
        scrollInventario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        botonInvocar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invocar();
            }
        });


        botonInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });
    }
    public void invocar() {
        Invocacion nuevaInvocacion = gacha.crearInvocacion(1);
            System.out.println(nuevaInvocacion);
            main.inventario.add(nuevaInvocacion);
    }

    public void mostrarInventario() {
        ventanaInventario.removeAll();
        ventanaInventario.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (Invocacion invocacion : main.inventario) {
            JPanel tarjeta = new JPanel();
            tarjeta.setPreferredSize(new Dimension(180, 220));
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

            JLabel labelNivel = new JLabel("Nivel: " + invocacion.getNivel());
            JLabel labelRaza = new JLabel("Raza: " + invocacion.getRaza());
            JLabel labelRareza = new JLabel("Rareza: " + invocacion.getRareza());
            JLabel labelVida = new JLabel("Vida: " + invocacion.getVida() + "/" + invocacion.getVidaMaxima());
            JLabel labelAtaque = new JLabel("Ataque: " + invocacion.getAtaque());
            JLabel labelDefensa = new JLabel("Defensa: " + invocacion.getDefensa());

            JButton botonUsar = new JButton("Usar");
            JButton botonEliminar = new JButton("Eliminar");

            labelNivel.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelRaza.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelRareza.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelVida.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelAtaque.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelDefensa.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonUsar.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);

            tarjeta.add(Box.createVerticalStrut(10));
            tarjeta.add(labelNivel);
            tarjeta.add(labelRaza);
            tarjeta.add(labelRareza);
            tarjeta.add(labelVida);
            tarjeta.add(labelAtaque);
            tarjeta.add(labelDefensa);
            tarjeta.add(Box.createVerticalGlue());
            tarjeta.add(botonUsar);
            tarjeta.add(Box.createVerticalStrut(5));
            tarjeta.add(botonEliminar);
            tarjeta.add(Box.createVerticalStrut(10));

            ventanaInventario.add(tarjeta);

        }

        ventanaInventario.revalidate();
        ventanaInventario.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuInicial");
        frame.setContentPane(new MenuInicial().panelMenuInicial);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}