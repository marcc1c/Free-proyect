package interfaz;

import javax.swing.*;

public class PanelCombate {
    private JButton buttonInvocar;
    private JTextPane textPane1;
    public JPanel panelCombate;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Combate");
        frame.setContentPane(new PanelCombate().panelCombate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public PanelCombate() {

    }
}
