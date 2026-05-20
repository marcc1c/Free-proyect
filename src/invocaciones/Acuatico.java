    package invocaciones;

    public class Acuatico extends Invocacion{

        public Acuatico() {
        }

        public Acuatico(int id, int nivel, String raza, String rareza) {
            super(id, nivel, raza, rareza);
        }

        public Acuatico(String rareza, String raza, double multiExteriencia, double multiDañoCritico, double multiProbCritico, double multiDefensa, double multiAtaque, double multiVida, double dañoCritico, double probCritico, double defensa, double ataque, double vidaMaxima, double vida, double experienciaMaxima, double experiencia, int ascension, int nivel, int id) {
            super(rareza, raza, multiExteriencia, multiDañoCritico, multiProbCritico, multiDefensa, multiAtaque, multiVida, dañoCritico, probCritico, defensa, ataque, vidaMaxima, vida, experienciaMaxima, experiencia, ascension, nivel, id);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
