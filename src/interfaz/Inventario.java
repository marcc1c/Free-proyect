package interfaz;

import invocaciones.Invocacion;
import logica.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Inventario {

    public JPanel panelInventario;
    private JButton buttonVolver;
    private JPanel panelGrid;
    private JScrollPane scrollPaneInventario;

    public Inventario() {
        scrollPaneInventario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneInventario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneInventario.getVerticalScrollBar().setUnitIncrement(18);

        mostrarInvocaciones(Main.inventarioInvocaciones);
    }

    public void mostrarInvocaciones(ArrayList<Invocacion> arrayInvocaciones) {

        panelGrid.removeAll();
        panelGrid.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelGrid.setBackground(new Color(55, 134, 219));

        for (Invocacion invocacion : arrayInvocaciones) {
            GameMenu gameMenu = new GameMenu();
            JPanel tarjeta = gameMenu.crearTarjetaInvocacion(invocacion);
            panelGrid.add(tarjeta);
        }

        ajustarAlturaPanel(arrayInvocaciones.size());

        panelGrid.revalidate();
        panelGrid.repaint();
    }

    private void ajustarAlturaPanel(int cantidadTarjetas) {

        int anchoScroll = scrollPaneInventario.getViewport().getWidth();

        if (anchoScroll <= 0) {
            anchoScroll = 900;
        }

        int anchoTarjeta = 240;
        int altoTarjeta = 330;
        int espacio = 20;

        int tarjetasPorFila = anchoScroll / (anchoTarjeta + espacio);

        if (tarjetasPorFila < 1) {
            tarjetasPorFila = 1;
        }

        int filas = cantidadTarjetas / tarjetasPorFila;

        if (cantidadTarjetas % tarjetasPorFila != 0) {
            filas++;
        }

        int altoTotal = filas * (altoTarjeta + espacio) + espacio;

        panelGrid.setPreferredSize(new Dimension(anchoScroll, altoTotal));
    }
}