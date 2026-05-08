package interfaz;

import logica.Main;
import logica.Tarjetas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Inventario {

    public JPanel panelInventario;
    private JButton buttonVolver;
    private JPanel panelGrid;
    private JScrollPane scrollPaneInventario;
    private JButton buttonInvocaciones;
    private JButton buttonObjetos;

    public Inventario() {
        Tarjetas.mostrarTarjetasInventario(0, panelGrid, scrollPaneInventario);

        panelGrid.setBackground(new Color(55, 134, 219));
        panelGrid.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));


        buttonInvocaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarjetas.mostrarTarjetasInventario(0, panelGrid, scrollPaneInventario);
            }
        });

        buttonObjetos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarjetas.mostrarTarjetasInventario(1, panelGrid, scrollPaneInventario);
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