package logica;

import invocaciones.Invocacion;

import java.util.Random;

public class Combate {

    public void turno(Invocacion invocacion1, Invocacion invocacion2) {
        Random random = new Random();
        double probCritico = random.nextDouble();
        double dañoFinal = invocacion1.getAtaque();

        if (probCritico < invocacion1.getProbCritico()) {
            dañoFinal = dañoFinal * invocacion1.getDañoCritico();
        }
        double dañoReal = dañoFinal - invocacion2.getDefensa();
        if (dañoReal < 0) dañoReal = 0;

        invocacion2.setVida(invocacion2.getVida() - dañoReal);
        System.out.println("Vida: " + invocacion2.getVida());
    }
}
