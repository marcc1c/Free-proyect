package interfaz;

import cargar.DescargarDatos;
import invocaciones.Invocacion;
import logica.Gacha;
import logica.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu {

    public JPanel panelGameMenu;
    private JButton buttonInvocar;
    private JButton buttonInventario;
    private JButton buttonCampoDeBatalla;
    private JButton buttonTrascender;
    private JButton buttonLogros;
    private JButton buttonCerrarSesion;

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
                Invocacion nuevaInvocacion = gacha.crearInvocacion(Main.suerte, 1);
                nuevaInvocacion.setId(Main.siguienteIdEnPartida());
                Main.inventarioInvocaciones.add(nuevaInvocacion);
            }
        });

        buttonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DescargarDatos descargarDatos = new DescargarDatos();
                descargarDatos.guardarPartida(Main.idUsuario);
                Main.limpiarDatosSesion();

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelGameMenu);
                frame.setContentPane(new IniciarSesion().panelIniciarSesion);
                frame.revalidate();
                frame.repaint();
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
