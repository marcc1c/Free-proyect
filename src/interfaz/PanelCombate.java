package interfaz;

import invocaciones.Invocacion;
import logica.Combate;
import logica.Gacha;
import logica.Main;
import logica.Tarjetas;

import javax.swing.*;
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
    static boolean esCampana = false;
    static int pisoCampanaCombate = 0;
    static int nivelCampanaCombate = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Combate");
        frame.setContentPane(new PanelCombate().panelCombate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PanelCombate() {
        this(false, 0, 0);
    }

    public PanelCombate(boolean campana, int piso, int nivelDelPiso) {
        Gacha gacha = new Gacha();
        buttonSalir.setVisible(false);

        esCampana = campana;
        if (campana) {
            esTorreInfinita = false;
        }
        pisoCampanaCombate = piso;
        nivelCampanaCombate = nivelDelPiso;

        Invocacion enemigo;
        if (campana) {
            String rareza = gacha.determinarRarezaCampana(piso, nivelDelPiso);
            enemigo = gacha.crearEnemigoCampana(piso, nivelDelPiso, rareza);
        } else {
            enemigo = gacha.crearInvocacion(Main.pisoTorreInfinita / 2 + 1, Main.pisoTorreInfinita / 2 + 1);
        }

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

                    if (esCampana && nivelCampanaCombate == Main.nivelCampana
                            && pisoCampanaCombate == Main.pisoCampana) {
                        if (nivelCampanaCombate >= 10) {
                            Main.pisoCampana++;
                            Main.nivelCampana = 1;
                        } else {
                            Main.nivelCampana++;
                        }
                    }

                    esTorreInfinita = false;
                    esCampana = false;
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

        buttonHuir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPanelRegistroCombate.setText(
                        textPanelRegistroCombate.getText() + "\nHas huido del combate.\n"
                );
                salirDelCombate();
            }
        });

        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salirDelCombate();
            }
        });
    }

    private void salirDelCombate() {
        Invocacion equipada = Tarjetas.saberInvocacionEquipada();
        if (equipada != null) {
            equipada.setVida(equipada.getVidaMaxima());
        }

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCombate);

        if (pisoCampanaCombate >= 1) {
            frame.setContentPane(new NivelCampana(pisoCampanaCombate).panelPisoCampana);
        } else {
            frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
        }

        frame.revalidate();
        frame.repaint();
    }
}
