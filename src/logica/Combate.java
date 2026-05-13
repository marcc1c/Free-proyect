package logica;

import invocaciones.Invocacion;
import items.Items;
import items.LootEntry;

import java.util.*;

public class Combate {

    public boolean turno(Invocacion invocacion1, Invocacion invocacion2) {
        Random random = new Random();

        boolean invocacion2ConVida = true;
        double probCritico = random.nextDouble();
        double dañoFinal = invocacion1.getAtaque();

        if (probCritico <= invocacion1.getProbCritico()) {
            dañoFinal = dañoFinal * invocacion1.getDañoCritico();
        }
        double dañoReal = dañoFinal - invocacion2.getDefensa();

        if (dañoReal < 0) {
            dañoReal = 0;
        }

        invocacion2.setVida(invocacion2.getVida() - dañoReal);
        System.out.println("Vida: " + invocacion2.getVida());

        if (invocacion2.getVida() <= 0) {
            invocacion2ConVida = false;
            invocacion1.subirExperiencia(calcularExperiencia(invocacion2));
            invocacion2.setVida(invocacion2.getVidaMaxima());
            invocacion1.setVida(invocacion1.getVidaMaxima());
            calcularDrop(invocacion2);
        }

        return invocacion2ConVida;
    }

    public int calcularExperiencia(Invocacion invocacion) {
        double multiplicadorPorRareza = 1;

        switch (invocacion.getRareza()) {
            case "Natural":
                multiplicadorPorRareza = 1.2;
                break;
            case "Raro":
                multiplicadorPorRareza = 1.5;
                break;
            case "Único":
                multiplicadorPorRareza = 1.7;
                break;
            case "Extinto":
                multiplicadorPorRareza = 2;
                break;
            case "Primordial":
                multiplicadorPorRareza = 2.5;
                break;
        }
        return (int) Math.round(invocacion.getNivel() * multiplicadorPorRareza);
    }

    public void calcularDrop(Invocacion invocacion) {
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

                        for (Items itemsJugador : Main.catalogoItems) {
                            if (itemsJugador.getId() == entradaLoot.getIdItem()) {
                                itemsJugador.setCantidad(itemsJugador.getCantidad() + cantidadItem);
                                System.out.println("Se ha sumado el objeto " + itemsJugador);
                            }
                        }
                    }
                }
            }
        }
    }
}

