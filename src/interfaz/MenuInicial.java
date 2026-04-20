package interfaz;

import javax.swing.*;

public class MenuInicial {

    private JButton botonInvocar;
    private JButton botonInventario;
    private JButton botonCampoBatalla;
    private JButton botonSalir;
    public JPanel panelMenuInicial;
    private JButton botonTrascender;
    private JButton botonLogros;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MenuInicial");
        frame.setContentPane(new MenuInicial().panelMenuInicial);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
