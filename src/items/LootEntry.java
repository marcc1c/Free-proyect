package items;

public class LootEntry {

    private int idObjeto, cantidadMinima, cantidadMaxima;
    private double porcentaje;

    public LootEntry(int idObjeto, double porcentaje, int cantidadMinima, int cantidadMaxima) {
        this.idObjeto = idObjeto;
        this.porcentaje = porcentaje;
        this.cantidadMinima = cantidadMinima;
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }
}