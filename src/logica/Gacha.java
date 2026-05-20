package logica;
import invocaciones.*;

import java.util.Random;

public class Gacha {
    Random random = new Random();

    private String determinarRareza(int suerte) {
        Random random = new Random();
        double numAleatorio = random.nextDouble() * 100;
        String rareza;

        if (numAleatorio <= 0.1 * suerte) {
            rareza = "Primordial";
        } else if (numAleatorio <= 0.6 * suerte) {
            rareza = "Extinto";
        } else if (numAleatorio <= 3.0 * suerte) {
            rareza = "Unico";
        } else if (numAleatorio <= 10.0 * suerte) {
            rareza = "Raro";
        } else if (numAleatorio <= 35.0 * (suerte * 0.7)) {
            rareza = "Natural";
        } else {
            rareza = "Comun";
        }
        return rareza;
    }

    private String determinarRaza() {
        Random random = new Random();
        double numAleatorio = random.nextDouble() * 100;
        String raza = "";

        if (numAleatorio <= 25) {
            raza = "Ave";
        } else if (numAleatorio <= 50) {
            raza = "Felino";
        } else if (numAleatorio <= 75) {
            raza = "Acuatico";
        } else if (numAleatorio <= 100) {
            raza = "Insecto";
        }
        return raza;
    }

    public Invocacion crearInvocacion(int suerte, int nivel) {
        Invocacion invocacion = crearInvocacion(nivel, determinarRareza(suerte));
        return invocacion;
    }

    public Invocacion crearInvocacion(int nivel, String rareza) {
        String raza = determinarRaza();
        Invocacion invocacion = new Felino(0, nivel, rareza);

        switch (raza) {
            case "Ave":
                invocacion = new Ave(0, nivel, rareza);
                break;
            case "Felino":
                invocacion = new Felino(0, nivel, rareza);
                break;
            case "Acuatico":
                invocacion = new Acuatico(0, nivel, rareza);
                break;
            case "Insecto":
                invocacion = new Insecto(0, nivel, rareza);
                break;
            default:
                break;
        }

        return invocacion;
    }

    public String determinarRarezaCampana(int piso, int nivelDelPiso) {
        String[][] rarezasPorPiso = {
                {"Comun", "Comun", "Natural"},
                {"Natural", "Natural", "Raro"},
                {"Raro", "Raro", "Unico"},
                {"Unico", "Unico", "Extinto"},
                {"Extinto", "Extinto", "Primordial"},
                {"Primordial", "Primordial", "Primordial"}
        };

        int indicePiso = piso - 1;
        if (indicePiso < 0) {
            indicePiso = 0;
        }
        if (indicePiso >= rarezasPorPiso.length) {
            indicePiso = rarezasPorPiso.length - 1;
        }

        int indiceNivel;
        if (nivelDelPiso <= 3) {
            indiceNivel = 0;
        } else if (nivelDelPiso <= 7) {
            indiceNivel = 1;
        } else {
            indiceNivel = 2;
        }

        String rareza = rarezasPorPiso[indicePiso][indiceNivel];
        return rareza;
    }

    public Invocacion crearEnemigoCampana(int piso, int nivelDelPiso, String rareza) {
        int[][] rangosPorPiso = {
                {1, 3},
                {5, 7},
                {10, 13},
                {15, 17},
                {22, 22},
                {23, 25}
        };

        int nivelDeLaInvocacion = 1;

        int indicePiso = piso - 1;
        if (indicePiso < 0) {
            indicePiso = 0;
        }
        if (indicePiso >= rangosPorPiso.length) {
            indicePiso = rangosPorPiso.length - 1;
        }

        int nivelMinimo = rangosPorPiso[indicePiso][0];
        int nivelMaximo = rangosPorPiso[indicePiso][1];
        int nivelMedio = (int) Math.round((nivelMinimo + nivelMaximo) / 2.0);

        if (nivelDelPiso <= 3) {
            nivelDeLaInvocacion = nivelMinimo;
        } else if (nivelDelPiso <= 7) {
            nivelDeLaInvocacion = nivelMedio;
        } else {
            nivelDeLaInvocacion = nivelMaximo;
        }

        return crearInvocacion(nivelDeLaInvocacion, rareza);
    }
}


