package logica;

import items.Items;
import invocaciones.Invocacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tarjetas {

    public static Invocacion saberInvocacionEquipada() {
        Invocacion invocacionEquipada = null;

        for (Invocacion invocacion : Main.inventarioInvocaciones) {
            if (invocacion.isEquipado()) {
                invocacionEquipada = invocacion;
            }
        }

        return invocacionEquipada;
    }

    public static void equiparInvocacion(Invocacion invocacion) {

        for (Invocacion invo : Main.inventarioInvocaciones) {
            invo.setEquipado(false);
        }

        invocacion.setEquipado(true);
    }

    public static void mostrarTarjetasInventario(int tipo, JPanel panelContenedor) {

        ArrayList<Items> arrayItems = Main.catalogoItems;
        ArrayList<Invocacion> arrayInvocaciones = Main.inventarioInvocaciones;

        panelContenedor.removeAll();
        panelContenedor.setLayout(new BorderLayout());
        panelContenedor.setBackground(new Color(55, 134, 219));

        JPanel panelTarjetas = new JPanel();
        panelTarjetas.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        panelTarjetas.setBackground(new Color(55, 134, 219));
        panelTarjetas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(panelTarjetas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(55, 134, 219));

        panelContenedor.add(scrollPane, BorderLayout.CENTER);

        if (tipo == 0) {
            for (Invocacion invocacion : arrayInvocaciones) {
                JPanel tarjeta = crearTarjetaInvocacionInventario(invocacion, panelContenedor);
                panelTarjetas.add(tarjeta);
            }
        } else {
            for (Items item : arrayItems) {
                if (item.getCantidad() != 0) {
                    JPanel tarjeta = crearTarjetaItem(item);
                    panelTarjetas.add(tarjeta);
                }
            }
        }

        int anchoDisponible = panelContenedor.getWidth();

        if (anchoDisponible <= 0) {
            anchoDisponible = 1000;
        }

        int cantidadTarjetas;

        if (tipo == 0) {
            cantidadTarjetas = arrayInvocaciones.size();
        } else {
            cantidadTarjetas = arrayItems.size();
        }

        int altoTarjeta = 330;
        int anchoTarjeta = 240;
        int espacio = 20;

        int tarjetasPorFila = anchoDisponible / (anchoTarjeta + espacio);

        if (tarjetasPorFila < 1) {
            tarjetasPorFila = 1;
        }

        int filas = cantidadTarjetas / tarjetasPorFila;

        if (cantidadTarjetas % tarjetasPorFila != 0) {
            filas++;
        }

        int altoTotal = filas * (altoTarjeta + espacio) + 60;

        panelTarjetas.setPreferredSize(new Dimension(anchoDisponible, altoTotal));

        panelTarjetas.revalidate();
        panelTarjetas.repaint();

        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    public static void mostrarSoloInvocacion(Invocacion invocacion, JPanel panelContenedor) {
        panelContenedor.removeAll();

        JPanel tarjeta = crearTarjetaInvocacion(invocacion);

        panelContenedor.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelContenedor.setBackground(new Color(55, 134, 219));
        panelContenedor.add(tarjeta);

        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    public static JPanel crearTarjetaInvocacion(Invocacion invocacion) {
        Color colorBorde = obtenerColorRareza(invocacion.getRareza());

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

        JLabel labelRaza = new JLabel(invocacion.getRaza());
        labelRaza.setForeground(Color.WHITE);
        labelRaza.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel labelRareza = new JLabel(invocacion.getRareza());
        labelRareza.setForeground(colorBorde);
        labelRareza.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelNivel = new JLabel("Nv. " + invocacion.getNivel());
        labelNivel.setForeground(Color.WHITE);
        labelNivel.setFont(new Font("Arial", Font.BOLD, 14));

        JProgressBar barraExperiencia = new JProgressBar(0, (int) invocacion.getExperienciaMaxima());
        barraExperiencia.setValue((int) invocacion.getExperiencia());
        barraExperiencia.setStringPainted(true);
        barraExperiencia.setString((int) invocacion.getExperiencia() + "/" + (int) invocacion.getExperienciaMaxima());
        barraExperiencia.setForeground(new Color(80, 120, 255));
        barraExperiencia.setBackground(new Color(45, 55, 65));
        barraExperiencia.setFont(new Font("Arial", Font.BOLD, 12));

        JProgressBar barraVida = new JProgressBar(0, (int) invocacion.getVidaMaxima());
        barraVida.setValue((int) invocacion.getVida());
        barraVida.setStringPainted(true);
        barraVida.setString((int) invocacion.getVida() + "/" + (int) invocacion.getVidaMaxima());
        barraVida.setForeground(new Color(220, 60, 60));
        barraVida.setBackground(new Color(45, 55, 65));
        barraVida.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel labelDefensa = new JLabel("Defensa: " + (int) invocacion.getDefensa());
        JLabel labelAtaque = new JLabel("Ataque: " + (int) invocacion.getAtaque());
        JLabel labelCritico = new JLabel("Crit%: " + (int) invocacion.getProbCritico());
        JLabel labelDanoCritico = new JLabel("Crit DMG: x" + String.format("%.1f", invocacion.getDañoCritico()));
        labelDefensa.setForeground(Color.WHITE);
        labelAtaque.setForeground(Color.WHITE);
        labelCritico.setForeground(Color.WHITE);
        labelDanoCritico.setForeground(Color.WHITE);

        labelDefensa.setFont(new Font("Arial", Font.BOLD, 13));
        labelAtaque.setFont(new Font("Arial", Font.BOLD, 13));
        labelCritico.setFont(new Font("Arial", Font.BOLD, 13));
        labelDanoCritico.setFont(new Font("Arial", Font.BOLD, 13));

        tarjeta.add(labelRaza);
        tarjeta.add(labelRareza);
        tarjeta.add(labelNivel);
        tarjeta.add(barraExperiencia);
        tarjeta.add(barraVida);
        tarjeta.add(labelDefensa);
        tarjeta.add(labelAtaque);
        tarjeta.add(labelCritico);
        tarjeta.add(labelDanoCritico);

        return tarjeta;
    }

    public static JPanel crearTarjetaInvocacionInventario(Invocacion invocacion, JPanel panelContenedor) {
        JPanel tarjeta = crearTarjetaInvocacion(invocacion);

        prepararTarjetaInvocacionParaInventario(tarjeta, invocacion, panelContenedor);

        return tarjeta;
    }

    public static void prepararTarjetaInvocacionParaInventario(JPanel tarjeta, Invocacion invocacion, JPanel panelContenedor) {
        JButton buttonUsar = new JButton("Usar");
        pintarBotonUsar(buttonUsar, invocacion);

        JButton buttonEliminar = new JButton("Eliminar");
        buttonEliminar.setBackground(new Color(255, 77, 94));

        buttonUsar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equiparInvocacion(invocacion);
                mostrarTarjetasInventario(0, panelContenedor);
            }
        });

        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.inventarioInvocaciones.remove(invocacion);
                mostrarTarjetasInventario(0, panelContenedor);
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.setBackground(new Color(10, 37, 56));
        panelBotones.add(buttonUsar);
        panelBotones.add(buttonEliminar);

        tarjeta.add(panelBotones);
    }

    public static JPanel crearTarjetaInvocacion(Invocacion invocacion, JPanel panelContenedor) {
        return crearTarjetaInvocacionInventario(invocacion, panelContenedor);
    }

    public static JPanel crearTarjetaItem(Items item) {

        Color colorBorde = obtenerColorRareza(item.getRareza());

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

        JTextArea textAreaDescripcion = new JTextArea(item.getDescripcion());
        textAreaDescripcion.setForeground(Color.WHITE);
        textAreaDescripcion.setFont(new Font("Arial", Font.BOLD, 14));
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        textAreaDescripcion.setBackground(new Color(10, 37, 56));
        textAreaDescripcion.setEditable(false);
        textAreaDescripcion.setFocusable(false);

        JLabel labelCantidad = new JLabel("Cantidad: " + item.getCantidad());
        labelCantidad.setForeground(Color.WHITE);
        labelCantidad.setFont(new Font("Arial", Font.BOLD, 14));

        tarjeta.add(labelNombre);
        tarjeta.add(labelRareza);
        tarjeta.add(new JSeparator());
        tarjeta.add(textAreaDescripcion);
        tarjeta.add(labelCantidad);

        return tarjeta;
    }

    public static void pintarBotonUsar(JButton buttonUsar, Invocacion invocacion) {

        buttonUsar.setOpaque(true);
        buttonUsar.setContentAreaFilled(true);
        buttonUsar.setFocusPainted(false);

        if (invocacion.isEquipado()) {
            buttonUsar.setText("Equipado");
            buttonUsar.setBackground(new Color(253, 207, 98));
            buttonUsar.setForeground(Color.BLACK);
        } else {
            buttonUsar.setText("Usar");
            buttonUsar.setBackground(new Color(240, 240, 240));
            buttonUsar.setForeground(Color.BLACK);
        }
    }

    public static Color obtenerColorRareza(String rareza) {

        Color color = Color.WHITE;

        switch (rareza) {
            case "Comun":
                color = Color.WHITE;
                break;

            case "Común":
                color = Color.WHITE;
                break;

            case "Natural":
                color = Color.GREEN;
                break;

            case "Raro":
                color = new Color(55, 134, 219);
                break;

            case "Unico":
                color = new Color(253, 207, 98);
                break;

            case "Único":
                color = new Color(253, 207, 98);
                break;

            case "Extinto":
                color = new Color(255, 138, 31);
                break;

            case "Primordial":
                color = new Color(255, 77, 94);
                break;
        }

        return color;
    }
}