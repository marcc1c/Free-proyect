package interfaz;

import invocaciones.Invocacion;
import logica.Gacha;
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
        Gacha gacha = new Gacha();

        if (Main.pisoCampana == piso) {
            if (Main.nivelCampana < 2) {
                buttonNivel2.setEnabled(false);
            }
            if (Main.nivelCampana < 3) {
                buttonNivel3.setEnabled(false);
            }
            if (Main.nivelCampana < 3) {
                buttonNivel3.setEnabled(false);
            }
            if (Main.nivelCampana < 4) {
                buttonNivel4.setEnabled(false);
            }
            if (Main.nivelCampana < 5) {
                buttonNivel5.setEnabled(false);
            }
            if (Main.nivelCampana < 6) {
                buttonNivel6.setEnabled(false);
            }
            if (Main.nivelCampana < 7) {
                buttonNivel7.setEnabled(false);
            }
            if (Main.nivelCampana < 8) {
                buttonNivel8.setEnabled(false);
            }
            if (Main.nivelCampana < 9) {
                buttonNivel9.setEnabled(false);
            }
            if (Main.nivelCampana < 10) {
                buttonNivel10.setEnabled(false);
            }
        }
            buttonNivel1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) ((piso-1) * 5), "Comun");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false, 1, piso).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Comun");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false, 2, piso).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Comun");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false, 3, piso).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Natural");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false, 4, piso).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Natural");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false,5 ,piso ).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Natural");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false, 6, piso).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Raro");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false, 7,piso ).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Raro");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false,8 , piso).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel9.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Raro");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false,9 ,piso ).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });

            buttonNivel10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion((int) (piso * 5), "Unico");
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelPisoCampana);
                    frame.setContentPane(new PanelCombate(enemigo, false,10 ,piso ).panelCombate);
                    frame.revalidate();
                    frame.repaint();
                }
            });
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

