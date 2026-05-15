package interfaz;

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("nivelCampana");
        frame.setContentPane(new NivelCampana(1).panelPisoCampana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public NivelCampana(int piso) {

        if (Main.pisoCampana == piso) {
            if (Main.nivelCampana == 2) {
                buttonNivel2.setEnabled(false);
            }
            if (Main.nivelCampana == 3) {
                buttonNivel3.setEnabled(false);
            }        }





        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarjetas.saberInvocacionEquipada().setVida(Tarjetas.saberInvocacionEquipada().getVidaMaxima());
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                frame.setContentPane(new PisoCampana().panelMenuCampana);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}

