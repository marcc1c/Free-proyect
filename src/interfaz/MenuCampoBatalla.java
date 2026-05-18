package interfaz;

import invocaciones.Invocacion;
import logica.Gacha;
import logica.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCampoBatalla {
    public JPanel panelCampoBatalla;
    private JButton buttonCampaña;
    private JButton buttonVolver;
    private JButton buttonTorreInfinita;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuCampoBatalla");
        frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public MenuCampoBatalla() {
        buttonTorreInfinita.setText("Torre infinita " + Main.pisoTorreInfinita);

        buttonTorreInfinita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gacha gacha = new Gacha();

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCampoBatalla);

                Invocacion invocacion = gacha.crearInvocacion(
                        Main.pisoTorreInfinita / 2 + 1,
                        Main.pisoTorreInfinita / 2 + 1
                );
                frame.setContentPane(new PanelCombate(invocacion, true, 0,0 ).panelCombate);
                frame.revalidate();
                frame.repaint();
            }
        });
        buttonCampaña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCampoBatalla);
                frame.setContentPane(new PisoCampana().panelMenuCampana);
                frame.revalidate();
                frame.repaint();
            }
        });
        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCampoBatalla);
                frame.setContentPane(new GameMenu().panelGameMenu);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}


