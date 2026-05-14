package interfaz;

import invocaciones.Invocacion;
import logica.Combate;
import logica.Gacha;
import logica.Main;
import logica.Tarjetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCombate {
    private JButton buttonAtacar;
    private JTextPane textPanelRegistroCombate;
    public JPanel panelCombate;
    private JPanel panelTuInvocacion;
    private JPanel panelInvocacionEnemiga;
    private JButton buttonHuir;
    private JButton buttonSalir;
    private JButton buttonHabilidades;

    static boolean esTorreInfinita = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Combate");
        frame.setContentPane(new PanelCombate().panelCombate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public PanelCombate() {


        Gacha gacha = new Gacha();
        buttonSalir.setVisible(false);

        Invocacion enemigo = gacha.crearInvocacion(Main.pisoTorreInfinita / 2+1, Main.pisoTorreInfinita / 2+1);

        Tarjetas.mostrarSoloInvocacion(Tarjetas.saberInvocacionEquipada(), panelTuInvocacion);
        Tarjetas.mostrarSoloInvocacion(enemigo, panelInvocacionEnemiga);

        buttonAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Combate combate = new Combate();

                Invocacion jugador = Tarjetas.saberInvocacionEquipada();

                boolean enemigoSigueVivo = combate.turno(jugador, enemigo, textPanelRegistroCombate, false);

                if (!enemigoSigueVivo) {
                    textPanelRegistroCombate.setText(textPanelRegistroCombate.getText() + "\nHAS GANADO\n");

                    buttonAtacar.setVisible(false);
                    buttonHuir.setVisible(false);
                    buttonHabilidades.setVisible(false);
                    buttonSalir.setVisible(true);
                    if (esTorreInfinita) {
                        Main.pisoTorreInfinita++;
                    }
                    esTorreInfinita = false;
                } else {

                    boolean jugadorSigueVivo = combate.turno(enemigo, jugador, textPanelRegistroCombate, true);

                    if (!jugadorSigueVivo) {
                        textPanelRegistroCombate.setText(textPanelRegistroCombate.getText() + "\nHAS PERDIDO\n");

                        buttonAtacar.setVisible(false);
                        buttonHuir.setVisible(false);
                        buttonHabilidades.setVisible(false);
                        buttonSalir.setVisible(true);
                    }
                }

                Tarjetas.mostrarSoloInvocacion(Tarjetas.saberInvocacionEquipada(), panelTuInvocacion);
                Tarjetas.mostrarSoloInvocacion(enemigo, panelInvocacionEnemiga);
                textPanelRegistroCombate.setText(textPanelRegistroCombate.getText() + "\n");
            }

        });
        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarjetas.saberInvocacionEquipada().setVida(Tarjetas.saberInvocacionEquipada().getVidaMaxima());
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCombate);
                frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
                frame.revalidate();
                frame.repaint();
            }
        });

        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarjetas.saberInvocacionEquipada().setVida(Tarjetas.saberInvocacionEquipada().getVidaMaxima());
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCombate);
                frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
    }
