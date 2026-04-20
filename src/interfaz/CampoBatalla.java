package interfaz;

import logica.Main;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CampoBatalla {
    private JButton botonTorreInfinita;
    private JButton botonAventura;
    private JButton botonSalir;
    JPanel panelBatallaInicial;
    private Main main;

    public CampoBatalla(Main main) {
        this.main = main;
        botonTorreInfinita.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelBatallaInicial);
                frame.setContentPane(new TorreInfinita(main).panelTorreInfinita);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CampoBatalla");
        frame.setContentPane(new CampoBatalla(new Main()).panelBatallaInicial);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
