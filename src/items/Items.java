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
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
