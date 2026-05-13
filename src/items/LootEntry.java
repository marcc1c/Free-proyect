package items;

public class LootEntry {

    private int cantidadMinima, cantidadMaxima, idItem;
    private double porcentaje;

    public LootEntry(int cantidadMinima, int idItem, int cantidadMaxima, double porcentaje) {
        this.cantidadMinima = cantidadMinima;
        this.idItem = idItem;
        this.cantidadMaxima = cantidadMaxima;
        this.porcentaje = porcentaje;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return "LootEntry{" +
                "cantidadMinima=" + cantidadMinima +
                ", cantidadMaxima=" + cantidadMaxima +
                ", idItem=" + idItem +
                ", porcentaje=" + porcentaje +
                '}';
    }
}