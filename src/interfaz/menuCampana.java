package interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuCampana {
    public JPanel panelMenuCampana;
    private JButton buttonPiso2;
    private JButton buttonPiso3;
    private JButton buttonPiso1;
    private JButton buttonPiso4;
    private JButton buttonPiso5;
    private JButton buttonVolver;

    public static void main(String[] args) {
        JFrame frame = new JFrame("menuCampana");
        frame.setContentPane(new menuCampana().panelMenuCampana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public menuCampana() {
        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelMenuCampana);
                frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
                frame.revalidate();
                frame.repaint();

                buttonPiso2.isDisplayable();
            }
        });
    }
}
