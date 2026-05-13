package interfaz;

import invocaciones.Invocacion;
import logica.Combate;
import logica.Gacha;
import logica.Main;
import logica.Tarjetas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelCombate {
    private JButton buttonAtacar;
    private JTextPane textPanelRegistroCombate;
    public JPanel panelCombate;
    private JPanel panelTuInvocacion;
    private JPanel panelInvocacionEnemiga;
    private JButton buttonHuir;
    private JButton buttonSalir;
    private JButton buttonHabilidades;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Combate");
        frame.setContentPane(new PanelCombate().panelCombate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public PanelCombate() {
        Gacha gacha = new Gacha();
        buttonSalir.setVisible(false);
        Invocacion enemigo = gacha.crearInvocacion(Main.pisoTorreInfinita/2, Main.pisoTorreInfinita/2);

        Tarjetas.crearTarjetaInvocacion(Tarjetas.saberInvocacionEquipada(), panelTuInvocacion);

        buttonAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Combate combate = new Combate();
                String ganadoOperdido = "";
                if (!combate.turno(Tarjetas.saberInvocacionEquipada(), enemigo, textPanelRegistroCombate, false)) {
                    ganadoOperdido = "HAS GANADO";
                } else if (!combate.turno(enemigo, Tarjetas.saberInvocacionEquipada(), textPanelRegistroCombate, true)) {
                    ganadoOperdido = "HAS PERDIDO";
                }
                textPanelRegistroCombate.setText(textPanelRegistroCombate.getText() + ganadoOperdido);
                buttonAtacar.setVisible(false);
                buttonHuir.setVisible(false);
                buttonHabilidades.setVisible(false);
                buttonSalir.setVisible(true);
            }
        });
        buttonSalir.addActionListener(new ActionListener() {
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
