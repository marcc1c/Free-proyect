package interfaz;

import logica.Tarjetas;

import javax.swing.*;

public class PanelCombate {
    private JButton buttonInvocar;
    private JTextPane textPanelRegistroCombate;
    public JPanel panelCombate;
    private JPanel panelTuInvocacion;
    private JPanel panelInvocacionEnemiga;
    private JButton buttonHuir;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Combate");
        frame.setContentPane(new PanelCombate().panelCombate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public PanelCombate() {

        Tarjetas.crearTarjetaInvocacion(Tarjetas.saberInvocacionEquipada(), panelTuInvocacion);
    }
}
