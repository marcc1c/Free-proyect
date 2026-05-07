package interfaz;
import cargar.*;
import logica.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IniciarSesion {
    public JPanel panelIniciarSesion;
    private JButton buttonCrearCuenta;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton buttonIniciarSesion;
    private JLabel labelAviso;

    public static void main(String[] args) {
        JFrame frame = new JFrame("iniciarSesion");
        frame.setContentPane(new IniciarSesion().panelIniciarSesion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public IniciarSesion() {
        buttonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConexionBD conexionBD = new ConexionBD();

                Main.idUsuario = conexionBD.iniciarSesion(textField1.getText(), passwordField1.getPassword());
                if (Main.idUsuario == -1) {
                    labelAviso.setText("Usuario o contraseña incorrecta");
                    } else {
                    Main.inicializarJuego();
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelIniciarSesion);
                    frame.setContentPane(new GameMenu().panelGameMenu);
                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

        buttonCrearCuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelIniciarSesion);
                frame.setContentPane(new Registrar().panelRegistrar);
                frame.revalidate();
                frame.repaint();
            }
        });
    }
}