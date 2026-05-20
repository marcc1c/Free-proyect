package interfaz;

import logica.Tarjetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inventario {

    public JPanel panelInventario;
    private JButton buttonVolver;
    private JPanel panelGrid;
    private JButton buttonInvocaciones;
    private JButton buttonObjetos;

    public Inventario() {

        panelGrid.setLayout(new BorderLayout());
        panelGrid.setBackground(new Color(55, 134, 219));

        Tarjetas.mostrarTarjetasInventario(0, panelGrid);

        buttonInvocaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarjetas.mostrarTarjetasInventario(0, panelGrid);
            }
        });

        buttonObjetos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarjetas.mostrarTarjetasInventario(1, panelGrid);
            }
        });

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelInventario);
                frame.setContentPane(new GameMenu().panelGameMenu);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}