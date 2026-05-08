package interfaz;

import invocaciones.Invocacion;
import items.Items;
import logica.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Inventario {

    public JPanel panelInventario;
    private JButton buttonVolver;
    private JPanel panelGrid;
    private JScrollPane scrollPaneInventario;
    private JButton buttonInvocaciones;
    private JButton buttonObjetos;

    private int pestañaActual = 0;

    public Inventario() {

        scrollPaneInventario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneInventario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneInventario.getVerticalScrollBar().setUnitIncrement(18);

        panelGrid.setBackground(new Color(55, 134, 219));
        panelGrid.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        scrollPaneInventario.getViewport().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                mostrarTarjeta(Main.inventarioInvocaciones, Main.catalogoItems, pestañaActual);
            }
        });

        mostrarTarjeta(Main.inventarioInvocaciones, Main.catalogoItems, 0);

        buttonInvocaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pestañaActual = 0;
                mostrarTarjeta(Main.inventarioInvocaciones, Main.catalogoItems, pestañaActual);
            }
        });

        buttonObjetos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pestañaActual = 1;
                mostrarTarjeta(Main.inventarioInvocaciones, Main.catalogoItems, pestañaActual);
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

    public void mostrarTarjeta(ArrayList<Invocacion> arrayInvocaciones, ArrayList<Items> arrayItems, int numeroArray) {

        panelGrid.removeAll();
        panelGrid.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelGrid.setBackground(new Color(55, 134, 219));

        int cantidadTarjetas;

        if (numeroArray == 0) {
            cantidadTarjetas = arrayInvocaciones.size();

            for (Invocacion invocacion : arrayInvocaciones) {
                JPanel tarjeta = GameMenu.crearTarjetaInvocacion(invocacion);
                panelGrid.add(tarjeta);
            }

        } else {
            cantidadTarjetas = arrayItems.size();

            for (Items item : arrayItems) {
                JPanel tarjeta = crearTarjetaItem(item);
                panelGrid.add(tarjeta);
            }
        }

        ajustarAlturaPanel(cantidadTarjetas);

        panelGrid.revalidate();
        panelGrid.repaint();

        scrollPaneInventario.revalidate();
        scrollPaneInventario.repaint();
    }

    public JPanel crearTarjetaItem(Items item) {

        Color colorBorde = GameMenu.obtenerColorRareza(item.getRareza());

        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new GridLayout(0, 1, 5, 5));
        tarjeta.setPreferredSize(new Dimension(240, 330));
        tarjeta.setMaximumSize(new Dimension(240, 330));
        tarjeta.setMinimumSize(new Dimension(240, 330));
        tarjeta.setBackground(new Color(10, 37, 56));
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 3),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        JLabel labelNombre = new JLabel(item.getNombre());
        labelNombre.setForeground(Color.WHITE);
        labelNombre.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel labelRareza = new JLabel(item.getRareza());
        labelRareza.setForeground(colorBorde);
        labelRareza.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea labelDescripcion = new JTextArea("Descripción: " + item.getDescripcion());
        labelDescripcion.setMaximumSize(new Dimension(210, 80));

        labelDescripcion.setForeground(Color.WHITE);
        labelDescripcion.setFont(new Font("Arial", Font.BOLD, 14));
        labelDescripcion.setBackground(new Color(10, 37, 56));

        labelDescripcion.setLineWrap(true);
        labelDescripcion.setWrapStyleWord(true);
        labelDescripcion.setEditable(false);


        JLabel labelCantidad = new JLabel("Cantidad: " + item.getCantidad());
        labelCantidad.setForeground(Color.WHITE);
        labelCantidad.setFont(new Font("Arial", Font.BOLD, 14));

        JButton buttonUsar = new JButton("Usar");
        JButton buttonEliminar = new JButton("Eliminar");

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(10, 37, 56));
        panelBotones.add(buttonUsar);
        panelBotones.add(buttonEliminar);

        tarjeta.add(labelNombre);
        tarjeta.add(labelRareza);
        tarjeta.add(new JSeparator());
        tarjeta.add(labelDescripcion);
        tarjeta.add(labelCantidad);
        tarjeta.add(panelBotones);

        return tarjeta;
    }

    private void ajustarAlturaPanel(int cantidadTarjetas) {

        int anchoScroll = scrollPaneInventario.getViewport().getWidth();

        if (anchoScroll <= 0) {
            anchoScroll = 900;
        }

        int anchoTarjeta = 240;
        int altoTarjeta = 330;
        int espacio = 20;

        int tarjetasPorFila = (anchoScroll - espacio) / (anchoTarjeta + espacio);

        if (tarjetasPorFila < 1) {
            tarjetasPorFila = 1;
        }

        int filas = cantidadTarjetas / tarjetasPorFila;

        if (cantidadTarjetas % tarjetasPorFila != 0) {
            filas++;
        }

        int altoTotal = filas * (altoTarjeta + espacio) + espacio;

        panelGrid.setPreferredSize(new Dimension(anchoScroll, altoTotal));
        panelGrid.setMinimumSize(new Dimension(anchoScroll, altoTotal));

        panelGrid.revalidate();
        panelGrid.repaint();
    }
}