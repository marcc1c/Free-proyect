package interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CampoBatalla {
    private JButton botonTorreInfinita;
    private JButton botonAventura;
    private JButton botonSalir;
    JPanel panelBatallaInicial;

    public CampoBatalla() {
        botonTorreInfinita.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelBatallaInicial);
                frame.setContentPane(new TorreInfinita().panelTorreInfinita);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CampoBatalla");
        frame.setContentPane(new CampoBatalla().panelBatallaInicial);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
