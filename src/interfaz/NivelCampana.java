package interfaz;

import invocaciones.Invocacion;
import logica.Main;
import logica.Tarjetas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NivelCampana {
    private JButton buttonNivel2;
    private JButton buttonNivel3;
    private JButton buttonNivel1;
    private JButton buttonNivel4;
    private JButton buttonNivel5;
    private JButton buttonVolver;
    private JButton buttonNivel6;
    private JButton buttonNivel8;
    private JButton buttonNivel7;
    private JButton buttonNivel9;
    private JButton buttonNivel10;
    public JPanel panelPisoCampana;

    private final int piso;

    public static void main(String[] args) {
        JFrame frame = new JFrame("nivelCampana");
        frame.setContentPane(new NivelCampana(1).panelPisoCampana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public NivelCampana(int piso) {
        this.piso = piso;

        configurarBotonesNivel(buttonNivel1, 1);
        configurarBotonesNivel(buttonNivel2, 2);
        configurarBotonesNivel(buttonNivel3, 3);
        configurarBotonesNivel(buttonNivel4, 4);
        configurarBotonesNivel(buttonNivel5, 5);
        configurarBotonesNivel(buttonNivel6, 6);
        configurarBotonesNivel(buttonNivel7, 7);
        configurarBotonesNivel(buttonNivel8, 8);
        configurarBotonesNivel(buttonNivel9, 9);
        configurarBotonesNivel(buttonNivel10, 10);

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Invocacion equipada = Tarjetas.saberInvocacionEquipada();
                if (equipada != null) {
                    equipada.setVida(equipada.getVidaMaxima());
                }
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                frame.setContentPane(new PisoCampana().panelMenuCampana);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private void configurarBotonesNivel(JButton boton, int nivel) {
        boton.setEnabled(puedeJugarNivel(nivel));

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarCombateCampana(nivel);
            }
        });
    }

    private boolean puedeJugarNivel(int nivel) {
        boolean puedeJugar;

        if (piso > Main.pisoCampana) {
            puedeJugar = false;
        } else if (piso < Main.pisoCampana) {
            puedeJugar = true;
        } else {
            // Mismo piso maximo: solo niveles ya alcanzados o el actual, no superiores
            puedeJugar = nivel <= Main.nivelCampana;
        }

        return puedeJugar;
    }

    private void iniciarCombateCampana(int nivel) {
        if (puedeJugarNivel(nivel) && Tarjetas.puedeEntrarEnCombate(panelPisoCampana)) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
            frame.setContentPane(new PanelCombate(true, piso, nivel).panelCombate);
            frame.revalidate();
            frame.repaint();
        }
    }
}
