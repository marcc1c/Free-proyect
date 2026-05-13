package logica;
import cargar.*;
import items.Items;
import invocaciones.Invocacion;
import items.LootEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static ArrayList<Invocacion> inventarioInvocaciones = new ArrayList<>();
    public static ArrayList<Items> catalogoItems = new ArrayList<>();
    public static Map<String, ArrayList<LootEntry>> lootPorCalidad = new HashMap<>();
    public static int idUsuario;
    public static int pisoTorreInfinita;
    public static int pisoCampana;
    public static int nivelCampana;
    public static int suerte = 1;




    public static void main(String[] args) {

    }

    public static void inicializarJuego() {
        ConexionBD conexionBD = new ConexionBD();

        conexionBD.cargarItemsUsuario(idUsuario);
        conexionBD.cargarPoolObjetosDrop(lootPorCalidad);
        conexionBD.cargarInvocaciones(idUsuario);
        conexionBD.cargarDatosCombate(idUsuario);
    }
}