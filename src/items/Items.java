package items;

public class Items {

    private String nombre, descripcion, rareza;
    private int id, cantidad;


    public Items(String nombre, String descripcion, String rareza, int cantidad, int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rareza = rareza;
        this.cantidad = cantidad;
        this.id = id;
    }

    public int getId() {
        int resultado = id;
        return resultado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        String resultado = nombre;
        return resultado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        String resultado = descripcion;
        return resultado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRareza() {
        String resultado = rareza;
        return resultado;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public int getCantidad() {
        int resultado = cantidad;
        return resultado;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        String resultado = "Items{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", rareza='" + rareza + '\'' +
                ", id=" + id +
                ", cantidad=" + cantidad +
                '}';
        return resultado;
    }
}
