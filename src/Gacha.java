import invocaciones.Ave;

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
            rareza = "Único";
        } else if (numAleatorio <= 10.0 * suerte) {
            rareza = "Raro";
        } else if (numAleatorio <= 35.0 * (suerte * 0.7)) {
            rareza = "Natural";
        } else {
            rareza = "Común";
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
        } else if  (numAleatorio <= 75) {
            raza = "Acuatico";
        } else if (numAleatorio <= 100) {
            raza = "Insectoide";
        }

        return raza;
    }




}


