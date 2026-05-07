package interfaz;

import cargar.ConexionBD;
import cargar.ConexionBD.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrar {
    public JPanel panelRegistrar;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton buttonRegistrar;
    private JButton buttonIniciarSesion;
    private JLabel labelAviso;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registrar");
        frame.setContentPane(new Registrar().panelRegistrar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Registrar() {

        buttonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelRegistrar);
                frame.setContentPane(new IniciarSesion().panelIniciarSesion);
                frame.revalidate();
                frame.repaint();
            }
        });

        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConexionBD conexionBD = new ConexionBD();
                if (conexionBD.existeUsuario(textField1.getText())) {
                    labelAviso.setText("Ya existe este usuario");
                } else {
                    conexionBD.registraUsuario(textField1.getText(), passwordField1.getPassword());
                    labelAviso.setText("Usuario registrado con exito");
                }
            }
        });
    }


}
