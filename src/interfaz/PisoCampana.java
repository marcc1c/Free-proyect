package interfaz;

import logica.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PisoCampana {
    public JPanel panelMenuCampana;
    private JButton buttonPiso2;
    private JButton buttonPiso3;
    private JButton buttonPiso1;
    private JButton buttonPiso4;
    private JButton buttonPiso5;
    private JButton buttonVolver;

    public static void main(String[] args) {
        JFrame frame = new JFrame("menuCampana");
        frame.setContentPane(new PisoCampana().panelMenuCampana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PisoCampana() {
        if (Main.pisoCampana < 2) {
            buttonPiso2.setEnabled(false);
        }
        if (Main.pisoCampana < 3) {
            buttonPiso3.setEnabled(false);
        }
        if (Main.pisoCampana < 4) {
            buttonPiso4.setEnabled(false);
        }
        if (Main.pisoCampana < 5) {
            buttonPiso5.setEnabled(false);
        }

        buttonPiso1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNiveles(1);
            }
        });
        buttonPiso2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNiveles(2);
            }
        });

        buttonPiso3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNiveles(3);
            }
        });

        buttonPiso4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNiveles(4);
            }
        });

        buttonPiso5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNiveles(5);
            }
        });

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelMenuCampana);
                frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
                frame.revalidate();
                frame.repaint();
            }
        });

    }
    private void abrirNiveles(int piso) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelMenuCampana);

        NivelCampana nivelCampana = new NivelCampana(piso);

        frame.setContentPane(nivelCampana.panelPisoCampana);
        frame.revalidate();
        frame.repaint();
    }
}
