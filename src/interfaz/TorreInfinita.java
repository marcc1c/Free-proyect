package interfaz;

import invocaciones.*;
import logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TorreInfinita {

    public JPanel panelTorreInfinita;
    private JButton botonAtacar;
    private JButton botonSalir;
    private JTextArea resultadosTexto;
    private JPanel panelInvocacion1;
    private JPanel panelInvocacion2;
    private JPanel panelResultados;
    private JPanel panelBotones;
    private JPanel panelInferior;
    private JPanel panelContenido;
    private Main main;
    private Invocacion jugador;
    private Invocacion enemigo;
    private Gacha gacha = new Gacha();
    private Combate combate = new Combate();

    public TorreInfinita(Main main) {
        this.main = main;
        botonSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panelTorreInfinita);
                frame.setContentPane(new MenuInicial(main).panelMenuInicial);
                frame.revalidate();
                frame.repaint();
            }
        });
            botonAtacar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Invocacion enemigo = gacha.crearInvocacion(12, 1);
                    combate.turno(Main.saberEquipada(), enemigo);
                    combate.turno(enemigo, Main.saberEquipada());
                }
            });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("TorreInfinita");
        frame.setContentPane(new TorreInfinita(new Main()).panelTorreInfinita);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}