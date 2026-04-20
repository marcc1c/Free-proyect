package interfaz;

import invocaciones.*;
import logica.*;

import javax.swing.*;
import java.awt.*;

public class TorreInfinita {

    public JPanel panelTorreInfinita;
    private JPanel panelContenido;
    private Main main;
    private Invocacion jugador;
    private Invocacion enemigo;
    private Gacha gacha = new Gacha();
    private boolean turnoJugador = true;
    private boolean combateTerminado = false;

    public TorreInfinita() {
        this.main = main;

        for (Invocacion inv : main.inventario) {
            if (inv.isEquipado()) {
                jugador = inv;
                break;
            }
        }

        enemigo = gacha.crearInvocacion(jugador != null ? jugador.getNivel() : 1);

        panelContenido = new JPanel(new BorderLayout(10, 10));
        panelContenido.setBackground(new Color(20, 20, 35));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        if (jugador == null) {
            JLabel sinJugador = new JLabel("No tienes ninguna invocación equipada.", SwingConstants.CENTER);
            sinJugador.setForeground(Color.WHITE);
            sinJugador.setFont(new Font("SansSerif", Font.BOLD, 16));
            panelContenido.add(sinJugador, BorderLayout.CENTER);

            JButton botonVolver = new JButton("Volver");
            estilizarBoton(botonVolver, new Color(100, 100, 100));
            botonVolver.addActionListener(e -> volver());
            JPanel panelVolver = new JPanel();
            panelVolver.setOpaque(false);
            panelVolver.add(botonVolver);
            panelContenido.add(panelVolver, BorderLayout.SOUTH);

            panelTorreInfinita.setLayout(new BorderLayout());
            panelTorreInfinita.add(panelContenido, BorderLayout.CENTER);
            return;
        }

        JPanel panelCombatientes = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCombatientes.setOpaque(false);
        panelCombatientes.add(crearTarjetaCombatiente(jugador, "TÚ"));
        panelCombatientes.add(crearTarjetaCombatiente(enemigo, "ENEMIGO"));

        JTextArea logCombate = new JTextArea();
        logCombate.setEditable(false);
        logCombate.setBackground(new Color(15, 15, 25));
        logCombate.setForeground(new Color(200, 200, 200));
        logCombate.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logCombate.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        logCombate.setText("⚔ ¡Comienza el combate!\n");

        JScrollPane scrollLog = new JScrollPane(logCombate);
        scrollLog.setPreferredSize(new Dimension(0, 150));
        scrollLog.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 120)));
        scrollLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JButton botonAtacar = new JButton("⚔ Atacar");
        estilizarBoton(botonAtacar, new Color(50, 100, 180));

        JButton botonVolver = new JButton("↩ Volver");
        estilizarBoton(botonVolver, new Color(100, 100, 100));

        botonAtacar.addActionListener(e -> {
            if (!turnoJugador || combateTerminado) return;

            double dañoJugador = calcularDaño(jugador, enemigo);
            enemigo.setVida(enemigo.getVida() - dañoJugador);
            logCombate.append("➤ Tu " + jugador.getRaza() + " ataca por " + Math.round(dañoJugador) + " de daño.\n");
            actualizarBarras(panelCombatientes);

            if (enemigo.getVida() <= 0) {
                enemigo.setVida(0);
                actualizarBarras(panelCombatientes);
                double expGanada = 20 + enemigo.getNivel() * 5;
                jugador.setExperiencia(jugador.getExperiencia() + expGanada);
                jugador.subirExperiencia(jugador);
                logCombate.append("🏆 ¡Victoria! Ganaste " + Math.round(expGanada) + " EXP.\n");
                combateTerminado = true;
                botonAtacar.setEnabled(false);
                return;
            }

            turnoJugador = false;
            Timer timer = new Timer(800, ae -> {
                double dañoEnemigo = calcularDaño(enemigo, jugador);
                jugador.setVida(jugador.getVida() - dañoEnemigo);
                logCombate.append("➤ El enemigo ataca por " + Math.round(dañoEnemigo) + " de daño.\n");
                actualizarBarras(panelCombatientes);

                if (jugador.getVida() <= 0) {
                    jugador.setVida(0);
                    actualizarBarras(panelCombatientes);
                    logCombate.append("💀 Has sido derrotado...\n");
                    combateTerminado = true;
                    botonAtacar.setEnabled(false);
                } else {
                    turnoJugador = true;
                }
                logCombate.setCaretPosition(logCombate.getDocument().getLength());
            });
            timer.setRepeats(false);
            timer.start();
            logCombate.setCaretPosition(logCombate.getDocument().getLength());
        });

        botonVolver.addActionListener(e -> {
            if (jugador.getVida() <= 0) jugador.setVida(jugador.getVidaMaxima());
            volver();
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelBotones.setOpaque(false);
        panelBotones.add(botonAtacar);
        panelBotones.add(botonVolver);

        JPanel panelSur = new JPanel(new BorderLayout(0, 10));
        panelSur.setOpaque(false);
        panelSur.add(scrollLog, BorderLayout.CENTER);
        panelSur.add(panelBotones, BorderLayout.SOUTH);

        panelContenido.add(panelCombatientes, BorderLayout.CENTER);
        panelContenido.add(panelSur, BorderLayout.SOUTH);

        panelTorreInfinita.setLayout(new BorderLayout());
        panelTorreInfinita.add(panelContenido, BorderLayout.CENTER);
    }

    private void estilizarBoton(JButton boton, Color color) {
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private JPanel crearTarjetaCombatiente(Invocacion inv, String etiqueta) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(new Color(30, 30, 45));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 120), 1),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)
        ));

        JLabel labelEtiqueta = new JLabel(etiqueta, SwingConstants.CENTER);
        labelEtiqueta.setForeground(new Color(160, 160, 200));
        labelEtiqueta.setFont(new Font("SansSerif", Font.BOLD, 11));
        labelEtiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelRaza = new JLabel(inv.getRaza(), SwingConstants.CENTER);
        labelRaza.setForeground(Color.WHITE);
        labelRaza.setFont(new Font("SansSerif", Font.BOLD, 15));
        labelRaza.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelNivel = new JLabel("Nv." + inv.getNivel(), SwingConstants.CENTER);
        labelNivel.setForeground(new Color(160, 160, 200));
        labelNivel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        labelNivel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(80, 80, 120));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));

        JProgressBar barraVida = new JProgressBar(0, (int) inv.getVidaMaxima());
        barraVida.setValue((int) inv.getVida());
        barraVida.setStringPainted(true);
        barraVida.setString(Math.round(inv.getVida()) + "/" + Math.round(inv.getVidaMaxima()));
        barraVida.setForeground(new Color(220, 70, 70));
        barraVida.setBackground(new Color(60, 30, 30));
        barraVida.setBorderPainted(false);
        barraVida.setFont(new Font("SansSerif", Font.BOLD, 10));
        barraVida.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
        barraVida.setAlignmentX(Component.LEFT_ALIGNMENT);
        barraVida.setName("barraVida");

        JLabel labelAtaque = new JLabel("⚔ Ataque: " + Math.round(inv.getAtaque()));
        labelAtaque.setForeground(Color.WHITE);
        labelAtaque.setFont(new Font("SansSerif", Font.PLAIN, 11));
        labelAtaque.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel labelDefensa = new JLabel("🛡 Defensa: " + Math.round(inv.getDefensa()));
        labelDefensa.setForeground(Color.WHITE);
        labelDefensa.setFont(new Font("SansSerif", Font.PLAIN, 11));
        labelDefensa.setAlignmentX(Component.LEFT_ALIGNMENT);

        tarjeta.add(labelEtiqueta);
        tarjeta.add(Box.createVerticalStrut(4));
        tarjeta.add(labelRaza);
        tarjeta.add(labelNivel);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(sep);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(barraVida);
        tarjeta.add(Box.createVerticalStrut(6));
        tarjeta.add(labelAtaque);
        tarjeta.add(Box.createVerticalStrut(3));
        tarjeta.add(labelDefensa);

        return tarjeta;
    }

    private void actualizarBarras(JPanel panelCombatientes) {
        actualizarBarraEnTarjeta((JPanel) panelCombatientes.getComponent(0), jugador);
        actualizarBarraEnTarjeta((JPanel) panelCombatientes.getComponent(1), enemigo);
    }

    private void actualizarBarraEnTarjeta(JPanel tarjeta, Invocacion inv) {
        for (Component c : tarjeta.getComponents()) {
            if (c instanceof JProgressBar && "barraVida".equals(c.getName())) {
                JProgressBar barra = (JProgressBar) c;
                barra.setValue((int) Math.max(0, inv.getVida()));
                barra.setString(Math.round(Math.max(0, inv.getVida())) + "/" + Math.round(inv.getVidaMaxima()));
                break;
            }
        }
        tarjeta.revalidate();
        tarjeta.repaint();
    }

    private double calcularDaño(Invocacion atacante, Invocacion defensor) {
        double daño = Math.max(1, atacante.getAtaque() - defensor.getDefensa() * 0.3);
        if (Math.random() * 100 < atacante.getProbCritico()) {
            daño *= atacante.getDañoCritico();
        }
        return daño;
    }

    private void volver() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelTorreInfinita);
        frame.setContentPane(new MenuInicial().panelMenuInicial);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TorreInfinita");
        frame.setContentPane(new TorreInfinita().panelTorreInfinita);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}