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
        int resultado = cantidadMinima;
        return resultado;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getCantidadMaxima() {
        int resultado = cantidadMaxima;
        return resultado;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public int getIdItem() {
        int resultado = idItem;
        return resultado;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public double getPorcentaje() {
        double resultado = porcentaje;
        return resultado;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        String resultado = "LootEntry{" +
                "cantidadMinima=" + cantidadMinima +
                ", cantidadMaxima=" + cantidadMaxima +
                ", idItem=" + idItem +
                ", porcentaje=" + porcentaje +
                '}';
        return resultado;
    }
}
