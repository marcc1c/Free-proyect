package interfaz;

import invocaciones.*;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial {

    private Gacha gacha = new Gacha();
    private Main main;

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

    public MenuInicial(Main main) {
        this.main = main;
        scrollInventario.setMinimumSize(new Dimension(200, 0));
        scrollInventario.setPreferredSize(new Dimension(200, 0));
        scrollInventario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollInventario.setViewportView(ventanaInventario);
        scrollInventario.getVerticalScrollBar().setUnitIncrement(20);

        botonInvocar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invocar();
                mostrarInventario();
            }
        });

        botonCampoBatalla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Invocacion invocacion : main.inventario) {
                    if (invocacion.isEquipado()) {
                        invocacion.setExperiencia(invocacion.getExperiencia() + 5);
                        invocacion.subirExperiencia(invocacion);
                        mostrarInventario();
                    }
                }
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelMenuInicial);
                frame.setContentPane(new Principal().panelMain);
                frame.revalidate();
                frame.repaint();
            }
        });

        botonCampoBatalla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelMenuInicial);
                frame.setContentPane(new CampoBatalla(main).panelBatallaInicial);
                frame.revalidate();
                frame.repaint();
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
        ventanaInventario.setLayout(new BoxLayout(ventanaInventario, BoxLayout.Y_AXIS));

        for (Invocacion invocacion : main.inventario) {

            JPanel tarjeta = new JPanel();
            tarjeta.setPreferredSize(new Dimension(Integer.MAX_VALUE, 280));
            tarjeta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 280));
            tarjeta.setBackground(new Color(30, 30, 45));
            tarjeta.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(80, 80, 120), 1),
                    BorderFactory.createEmptyBorder(10, 14, 10, 14)
            ));
            tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

            // Encabezado: Raza + Rareza
            JPanel panelEncabezado = new JPanel(new BorderLayout());
            panelEncabezado.setOpaque(false);
            panelEncabezado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));

            JLabel labelRaza = new JLabel(invocacion.getRaza());
            labelRaza.setForeground(Color.WHITE);
            labelRaza.setFont(new Font("SansSerif", Font.BOLD, 13));

            JLabel labelRareza = new JLabel(invocacion.getRareza());
            labelRareza.setFont(new Font("SansSerif", Font.BOLD, 11));
            switch (invocacion.getRareza()) {
                case "Primordial": labelRareza.setForeground(new Color(46, 178, 78)); break;
                case "Extinto":    labelRareza.setForeground(new Color(219, 60, 27)); break;
                case "Único":      labelRareza.setForeground(new Color(208, 196, 84)); break;
                case "Raro":       labelRareza.setForeground(new Color(60, 141, 192)); break;
                default:           labelRareza.setForeground(new Color(180, 180, 180)); break;
            }

            panelEncabezado.add(labelRaza, BorderLayout.WEST);
            panelEncabezado.add(labelRareza, BorderLayout.EAST);

            // Separador
            JSeparator separador = new JSeparator();
            separador.setForeground(new Color(80, 80, 120));
            separador.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));

            // Nivel + Barra EXP en la misma fila
            JPanel panelNivelExp = new JPanel(new BorderLayout(6, 0));
            panelNivelExp.setOpaque(false);
            panelNivelExp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 18));

            JLabel labelNivel = new JLabel("Nv." + invocacion.getNivel());
            labelNivel.setForeground(Color.WHITE);
            labelNivel.setFont(new Font("SansSerif", Font.BOLD, 11));

            long expActual = Math.round(invocacion.getExperiencia());
            long expMaxima = Math.round(invocacion.getExperienciaMaxima());

            JProgressBar barraExp = new JProgressBar(0, (int) expMaxima);
            barraExp.setValue((int) expActual);
            barraExp.setStringPainted(true);
            barraExp.setString(expActual + "/" + expMaxima);
            barraExp.setForeground(new Color(255, 190, 40));
            barraExp.setBackground(new Color(60, 50, 20));
            barraExp.setBorderPainted(false);
            barraExp.setFont(new Font("SansSerif", Font.BOLD, 10));

            panelNivelExp.add(labelNivel, BorderLayout.WEST);
            panelNivelExp.add(barraExp, BorderLayout.CENTER);

            // Barra de vida
            long vida = Math.round(invocacion.getVida());
            long vidaMaxima = Math.round(invocacion.getVidaMaxima());

            JProgressBar barraVida = new JProgressBar(0, (int) vidaMaxima);
            barraVida.setValue((int) vida);
            barraVida.setStringPainted(true);
            barraVida.setString(vida + "/" + vidaMaxima);
            barraVida.setForeground(new Color(220, 70, 70));
            barraVida.setBackground(new Color(60, 30, 30));
            barraVida.setBorderPainted(false);
            barraVida.setFont(new Font("SansSerif", Font.BOLD, 10));
            barraVida.setMaximumSize(new Dimension(Integer.MAX_VALUE, 14));
            barraVida.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Defensa sola en su fila
            JPanel panelDefensa = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            panelDefensa.setOpaque(false);
            panelDefensa.setMaximumSize(new Dimension(Integer.MAX_VALUE, 18));
            JLabel labelDefensa = new JLabel("🛡 Defensa: " + Math.round(invocacion.getDefensa()));
            labelDefensa.setForeground(Color.WHITE);
            labelDefensa.setFont(new Font("SansSerif", Font.PLAIN, 11));
            panelDefensa.add(labelDefensa);

            // Ataque, Crit% y Crit DMG en vertical
            JPanel panelAtaqueCrits = new JPanel(new GridLayout(3, 1, 0, 3));
            panelAtaqueCrits.setOpaque(false);
            panelAtaqueCrits.setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));

            JLabel labelAtaque = new JLabel("⚔ Ataque: " + Math.round(invocacion.getAtaque()));
            labelAtaque.setForeground(Color.WHITE);
            labelAtaque.setFont(new Font("SansSerif", Font.PLAIN, 11));

            JLabel labelCrit = new JLabel("🎯 Crit%: " + String.format("%.1f", invocacion.getProbCritico()) + "%");
            labelCrit.setForeground(Color.WHITE);
            labelCrit.setFont(new Font("SansSerif", Font.PLAIN, 11));

            JLabel labelDanoCrit = new JLabel("💥 Crit DMG: " + String.format("%.1f", invocacion.getDañoCritico()));
            labelDanoCrit.setForeground(Color.WHITE);
            labelDanoCrit.setFont(new Font("SansSerif", Font.PLAIN, 11));

            panelAtaqueCrits.add(labelAtaque);
            panelAtaqueCrits.add(labelCrit);
            panelAtaqueCrits.add(labelDanoCrit);

            // Botones
            JPanel panelBotones = new JPanel(new GridLayout(1, 2, 6, 0));
            panelBotones.setOpaque(false);
            panelBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

            JButton botonUsar = new JButton("Usar");
            botonUsar.setBackground(invocacion.isEquipado() ? new Color(193, 121, 48) : new Color(50, 100, 180));
            botonUsar.setForeground(Color.WHITE);
            botonUsar.setFocusPainted(false);
            botonUsar.setBorderPainted(false);
            botonUsar.setFont(new Font("SansSerif", Font.BOLD, 11));
            botonUsar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JButton botonEliminar = new JButton("Eliminar");
            botonEliminar.setBackground(new Color(160, 40, 40));
            botonEliminar.setForeground(Color.WHITE);
            botonEliminar.setFocusPainted(false);
            botonEliminar.setBorderPainted(false);
            botonEliminar.setFont(new Font("SansSerif", Font.BOLD, 11));
            botonEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            botonEliminar.addActionListener(e -> {
                main.inventario.remove(invocacion);
                mostrarInventario();
            });

            botonUsar.addActionListener(e -> {
                invocacion.setEquipado(!invocacion.isEquipado());
                botonUsar.setBackground(invocacion.isEquipado() ? new Color(193, 121, 48) : new Color(50, 100, 180));
            });

            panelBotones.add(botonUsar);
            panelBotones.add(botonEliminar);

            // Ensamblar tarjeta
            tarjeta.add(panelEncabezado);
            tarjeta.add(Box.createVerticalStrut(6));
            tarjeta.add(separador);
            tarjeta.add(Box.createVerticalStrut(6));
            tarjeta.add(panelNivelExp);
            tarjeta.add(Box.createVerticalStrut(6));
            tarjeta.add(barraVida);
            tarjeta.add(Box.createVerticalStrut(6));
            tarjeta.add(panelDefensa);
            tarjeta.add(Box.createVerticalStrut(3));
            tarjeta.add(panelAtaqueCrits);
            tarjeta.add(Box.createVerticalGlue());
            tarjeta.add(panelBotones);

            ventanaInventario.add(tarjeta);
            ventanaInventario.add(Box.createVerticalStrut(10));
        }

        ventanaInventario.setPreferredSize(new Dimension(
                ventanaInventario.getWidth(),
                main.inventario.size() * 295 + 20
        ));

        ventanaInventario.revalidate();
        ventanaInventario.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuInicial");
        frame.setContentPane(new MenuInicial(new Main()).panelMenuInicial);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}