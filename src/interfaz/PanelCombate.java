package interfaz;

import logica.Tarjetas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        buttonHuir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelCombate);
                frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}
