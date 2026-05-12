package interfaz;

import logica.*;
import invocaciones.Invocacion;
import logica.Combate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameMenu {

    public JPanel panelGameMenu;
    private JButton buttonInvocar;
    private JButton buttonInventario;
    private JButton buttonCampoDeBatalla;
    private JButton buttonTrascender;
    private JButton buttonLogros;
    private JButton buttonSalir;

    private JScrollPane scrollPaneInvocaciones;

    public GameMenu() {


        buttonInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelGameMenu);
                frame.setContentPane(new Inventario().panelInventario);
                frame.revalidate();
                frame.repaint();
            }
        });
        buttonCampoDeBatalla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelGameMenu);
                frame.setContentPane(new MenuCampoBatalla().panelCampoBatalla);
                frame.revalidate();
                frame.repaint();
            }
        });

        buttonInvocar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gacha gacha = new Gacha();
                gacha.crearInvocacion(1, 1);
            }
        });
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("GameMenu");
        frame.setContentPane(new GameMenu().panelGameMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}