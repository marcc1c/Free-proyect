package interfaz;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicial {

    private JButton botonInvocar;
    private JButton botonInventario;
    private JButton botonCampoBatalla;
    private JButton botonSalir;
    public JPanel panelMenuInicial;
    private JButton botonTrascender;
    private JButton botonLogros;

    public MenuInicial() {
        botonInvocar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuInicial");
        frame.setContentPane(new MenuInicial().panelMenuInicial);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
