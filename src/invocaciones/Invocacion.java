package invocaciones;

import java.math.MathContext;

public abstract class Invocacion {

    protected int id, nivel;
    protected int ascension = 0;
    protected double experiencia = 0;
    protected double experienciaMaxima = 10;


    protected double vida, vidaMaxima, ataque, defensa, probCritico, dañoCritico, multiVida, multiAtaque, multiDefensa,
            multiProbCritico, multiDañoCritico, multiExteriencia;
    protected String raza, rareza;
    protected boolean equipado;

    public Invocacion() {
    }

    public Invocacion(int id, int nivel, String raza, String rareza) {

        calcularMultiplicadores(raza);
        calcularStats(rareza);
        this.id = id;
        this.nivel = nivel;
        this.raza = raza;
        this.rareza = rareza;
    }

    private void asignarStats(double vida, double ataque, double defensa, double probCritico, double dañoCritico) {
        this.vida = vida * this.multiVida;
        this.vidaMaxima = vida * this.multiVida;
        this.ataque = ataque * this.multiAtaque;
        this.defensa = defensa * this.multiDefensa;
        this.probCritico = probCritico * this.multiProbCritico;
        this.dañoCritico = dañoCritico * this.multiDañoCritico;
    }

    public void asignarMultiplicadores(double multiVida, double multiAtaque, double multiDefensa,
                                       double multiProbCritico, double multiDañoCritico) {
        this.multiVida = multiVida;
        this.multiAtaque = multiAtaque;
        this.multiDefensa = multiDefensa;
        this.multiProbCritico = multiProbCritico;
        this.multiDañoCritico = multiDañoCritico;
    }


    public void calcularStats(String rareza) {

        switch (rareza) {
            case "Común":
                asignarStats(20, 5, 0, 10, 1.5);
                break;
            case "Natural":
                asignarStats(30, 7, 1, 11, 1.5);
                break;
            case "Raro":
                asignarStats(45, 9, 2, 12, 1.6);
                break;
            case "Único":
                asignarStats(60, 12, 3, 13, 1.6);
                break;
            case "Extinto":
                asignarStats(75, 15, 4, 14, 1.7);
                break;
            case "Primordial":
                asignarStats(90, 18, 5, 15, 1.8);
                break;
        }
    }

    public void calcularMultiplicadores(String raza) {

        switch (raza) {
            case "Ave":
                asignarMultiplicadores(0.8, 2.8, 0.8, 1.5, 1);
                break;
            case "Felino":
                asignarMultiplicadores(1, 2, 1, 1.75, 1);
                break;
            case "Insecto":
                asignarMultiplicadores(2.2, 2.4, 2.2, 1, 1);
                break;
            case "Acuatico":
                asignarMultiplicadores(1.5, 1, 1.7, 1.5, 1);
        }
    }

    public boolean subirExperiencia(Invocacion enemigo) {
        boolean subido = false;

            this.experiencia += enemigo.multiExteriencia;

            if (this.experiencia >= this.experienciaMaxima) {
                this.experiencia -= this.experienciaMaxima;
                this.nivel++;
                this.experienciaMaxima = Math.round(10 * Math.pow(this.nivel, 1.15));
                subido = true;

                double incProb;
                int incFuerza;
                int incVida;

                switch (this.rareza) {

                    case "Común":
                        this.ataque = (this.ataque / this.multiAtaque) + 1;
                        this.vidaMaxima = (this.vidaMaxima / this.multiVida) + 4;
                        this.defensa = (this.defensa / this.multiDefensa) + 0.6;
                        this.probCritico = (this.probCritico / this.multiProbCritico) + 0.5;
                        this.dañoCritico = (this.dañoCritico / this.multiDañoCritico) + 0.05;
                        break;

                    case "Natural":
                        this.ataque = (this.ataque / this.multiAtaque) + 1;
                        this.vidaMaxima = (this.vidaMaxima / this.multiVida) + 5;
                        this.defensa = (this.defensa / this.multiDefensa) + 0.85;
                        this.probCritico = (this.probCritico / this.multiProbCritico) + 0.6;
                        this.dañoCritico = (this.dañoCritico / this.multiDañoCritico) + 0.08;
                        break;

                    case "Raro":
                        this.ataque = (this.ataque / this.multiAtaque) + 2;
                        this.vidaMaxima = (this.vidaMaxima / this.multiVida) + 7;
                        this.defensa = (this.defensa / this.multiDefensa) + 1;
                        this.probCritico = (this.probCritico / this.multiProbCritico) + 0.7;
                        this.dañoCritico = (this.dañoCritico / this.multiDañoCritico) + 0.1;
                        break;

                    case "Único":
                        this.ataque = (this.ataque / this.multiAtaque) + 4;
                        this.vidaMaxima = (this.vidaMaxima / this.multiVida) + 9;
                        this.defensa = (this.defensa / this.multiDefensa) + 1.5;
                        this.probCritico = (this.probCritico / this.multiProbCritico) + 0.8;
                        this.dañoCritico = (this.dañoCritico / this.multiDañoCritico) + 0.1;
                        break;

                    case "Extinto":
                        this.ataque = (this.ataque / this.multiAtaque) + 4;
                        this.vidaMaxima = (this.vidaMaxima / this.multiVida) + 12;
                        this.defensa = (this.defensa / this.multiDefensa) + 2;
                        this.probCritico = (this.probCritico / this.multiProbCritico) + 0.9;
                        this.dañoCritico = (this.dañoCritico / this.multiDañoCritico) + 0.12;
                        break;

                    case "Primordial":
                        this.ataque = (this.ataque / this.multiAtaque) + 5;
                        this.vidaMaxima = (this.vidaMaxima / this.multiVida) + 15;
                        this.defensa = (this.defensa / this.multiDefensa) + 2.2;
                        this.probCritico = (this.probCritico / this.multiProbCritico) + 1;
                        this.dañoCritico = (this.dañoCritico / this.multiDañoCritico) + 0.15;
                        break;
                }
                        asignarStats(this.vidaMaxima, this.ataque, this.defensa, this.probCritico, this.dañoCritico);
                }

            return subido;
        }


    @Override
    public String toString() {
        return "Invocacion{" +
                "id=" + id +
                ", nivel=" + nivel +
                ", ascension=" + ascension +
                ", experiencia=" + experiencia +
                ", vida=" + vida +
                ", vidaMaxima=" + vidaMaxima +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", probCritico=" + probCritico +
                ", dañoCritico=" + dañoCritico +
                ", multiVida=" + multiVida +
                ", multiAtaque=" + multiAtaque +
                ", multiDefensa=" + multiDefensa +
                ", multiProbCritico=" + multiProbCritico +
                ", multiDañoCritico=" + multiDañoCritico +
                ", multiExteriencia=" + multiExteriencia +
                ", raza='" + raza + '\'' +
                ", rareza='" + rareza + '\'' +
                ", equipado=" + equipado +
                '}';
    }

    public double getExperienciaMaxima() {
        return experienciaMaxima;
    }

    public void setExperienciaMaxima(double experienciaMaxima) {
        this.experienciaMaxima = experienciaMaxima;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public double getMultiExteriencia() {
        return multiExteriencia;
    }

    public void setMultiExteriencia(double multiExteriencia) {
        this.multiExteriencia = multiExteriencia;
    }

    public double getMultiDañoCritico() {
        return multiDañoCritico;
    }

    public void setMultiDañoCritico(double multiDañoCritico) {
        this.multiDañoCritico = multiDañoCritico;
    }

    public double getMultiProbCritico() {
        return multiProbCritico;
    }

    public void setMultiProbCritico(double multiProbCritico) {
        this.multiProbCritico = multiProbCritico;
    }

    public double getMultiDefensa() {
        return multiDefensa;
    }

    public void setMultiDefensa(double multiDefensa) {
        this.multiDefensa = multiDefensa;
    }

    public double getMultiVida() {
        return multiVida;
    }

    public void setMultiVida(double multiVida) {
        this.multiVida = multiVida;
    }

    public double getMultiAtaque() {
        return multiAtaque;
    }

    public void setMultiAtaque(double multiAtaque) {
        this.multiAtaque = multiAtaque;
    }

    public double getDañoCritico() {
        return dañoCritico;
    }

    public void setDañoCritico(double dañoCritico) {
        this.dañoCritico = dañoCritico;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }

    public double getProbCritico() {
        return probCritico;
    }

    public void setProbCritico(double probCritico) {
        this.probCritico = probCritico;
    }

    public double getAtaque() {
        return ataque;
    }

    public void setAtaque(double ataque) {
        this.ataque = ataque;
    }

    public double getVidaMaxima() {
        return vidaMaxima;
    }

    public void setVidaMaxima(double vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(double experiencia) {
        this.experiencia = experiencia;
    }

    public int getAscension() {
        return ascension;
    }

    public void setAscension(int ascension) {
        this.ascension = ascension;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
