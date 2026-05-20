package logica;

import invocaciones.Invocacion;
import items.Items;
import items.LootEntry;

import javax.swing.*;
import java.util.*;

public class Combate {

    public boolean turno(Invocacion invocacion1, Invocacion invocacion2, JTextPane logsCombate, boolean esEnemigo) {
        Random random = new Random();

        if (esEnemigo) {
            logsCombate.setText(logsCombate.getText() + "Enemigo ha hecho ");
        } else {
            logsCombate.setText(logsCombate.getText() + "Tu invocacion ha hecho ");

        }
        boolean invocacion2ConVida = true;
        double probCritico = random.nextDouble() * 100;
        double dañoFinal = invocacion1.getAtaque();

        if (probCritico <= invocacion1.getProbCritico()) {
            dañoFinal = dañoFinal * invocacion1.getDañoCritico();
            logsCombate.setText(logsCombate.getText() + "CRÍTICO ");

        }
        double dañoReal = dañoFinal - invocacion2.getDefensa();

        if (dañoReal < 0) {
            dañoReal = 0;
        }
        logsCombate.setText(logsCombate.getText() + (int) dañoReal + " puntos de daño\n");

        invocacion2.setVida(invocacion2.getVida() - dañoReal);
        if (invocacion2.getVida() < 0) {
            invocacion2.setVida(0);
        }

        if (invocacion2.getVida() <= 0) {
            invocacion2ConVida = false;
            invocacion1.subirExperiencia(calcularExperiencia(invocacion2));
            if (!esEnemigo) {
                calcularDrop(invocacion2, logsCombate);
            }
        }

        return invocacion2ConVida;
    }

    public double calcularExperiencia(Invocacion invocacion) {
        double multiplicadorPorRareza = 1;

        switch (invocacion.getRareza()) {
            case "Natural":
                multiplicadorPorRareza = 1.2;
                break;
            case "Raro":
                multiplicadorPorRareza = 1.5;
                break;
            case "Unico":
                multiplicadorPorRareza = 1.7;
                break;
            case "Extinto":
                multiplicadorPorRareza = 2;
                break;
            case "Primordial":
                multiplicadorPorRareza = 2.5;
                break;
        }
        double experiencia = invocacion.getNivel() * multiplicadorPorRareza;
        return experiencia;
    }

    public void calcularDrop(Invocacion invocacion, JTextPane logsCombate) {
        Random random = new Random();

        for (Map.Entry<String, ArrayList<LootEntry>> entry : Main.lootPorCalidad.entrySet()) {

            if (entry.getKey().equalsIgnoreCase(invocacion.getRareza())) {
                ArrayList<LootEntry> listaEntradas = entry.getValue();

                for (LootEntry entradaLoot : listaEntradas) {

                    double randomNumero = random.nextDouble(100);

                    if (randomNumero <= entradaLoot.getPorcentaje()) {
                        int cantidadItem = random.nextInt(
                                entradaLoot.getCantidadMaxima() - entradaLoot.getCantidadMinima() + 1
                        ) + entradaLoot.getCantidadMinima();

                        logsCombate.setText(logsCombate.getText() + "\n");

                        for (Items itemsJugador : Main.catalogoItems) {
                            if (itemsJugador.getId() == entradaLoot.getIdItem()) {
                                logsCombate.setText(logsCombate.getText() + "Has recibido " + itemsJugador.getNombre() + " x " + cantidadItem);
                                itemsJugador.setCantidad(itemsJugador.getCantidad() + cantidadItem);
                            }
                        }
                    }
                }
                logsCombate.setText(logsCombate.getText() + "\n");
            }
        }
    }
}

