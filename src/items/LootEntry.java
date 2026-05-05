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

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "LootEntry{" +
                "idObjeto=" + idObjeto +
                ", cantidadMinima=" + cantidadMinima +
                ", cantidadMaxima=" + cantidadMaxima +
                ", porcentaje=" + porcentaje +
                '}';
    }
}