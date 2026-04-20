package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal {

    private JPanel panelMain;
    private JButton botonNuevaPartida;
    private JButton cargarPartida;
    private JButton botonAjustes;
    private JButton botonSalir;

    public Principal() {
        botonNuevaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelMain);
                frame.setContentPane(new MenuInicial().panelMenuInicial);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Principal");
        frame.setContentPane(new Principal().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

    }
}