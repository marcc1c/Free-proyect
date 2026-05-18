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
        String raza = determinarRaza();
        String rareza = determinarRareza(suerte);

        return crearInvocacion(nivel, raza, rareza);
    }


    public Invocacion crearInvocacion(int nivel, String raza, String rareza) {
        Invocacion invocacion = null;


        switch (raza) {
            case "Ave":
                invocacion = new Ave(0, nivel, raza, rareza);
                break;

            case "Felino":
                invocacion = new Felino(0, nivel, raza, rareza);
                break;

            case "Acuatico":
                invocacion = new Acuatico(0, nivel, raza, rareza);
                break;

            case "Insecto":
                invocacion = new Insecto(0, nivel, raza, rareza);
                break;
        }
        return invocacion;
    }

    public Invocacion crearInvocacion(int nivel, String rareza) {
        Invocacion invocacion = null;
        String raza = determinarRaza();

        switch (raza) {
            case "Ave":
                invocacion = new Ave(0, nivel, raza, rareza);
                break;
            case "Felino":
                invocacion = new Felino(0, nivel, raza, rareza);
                break;
            case "Acuatico":
                invocacion = new Acuatico(0, nivel, raza, rareza);
                break;
            case "Insecto":
                invocacion = new Insecto(0, nivel, raza, rareza);
                break;
        }
        return invocacion;
    }
}


