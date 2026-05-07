package logica;
import cargar.*;
import interfaz.Principal;
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
    public static Map<String, List<LootEntry>> lootPorCalidad = new HashMap<>();
    public static int idUsuario = 1;

    public static void main(String[] args) {

        ConexionBD conexionBD = new ConexionBD();

        conexionBD.cargarItemsUsuario(1);
        conexionBD.cargarPoolObjetosDrop();
        conexionBD.cargarItemsUsuario(idUsuario);
    }

    public static Invocacion saberEquipada() {
        Invocacion invocacionEquipada = null;

        for (Invocacion invocacion : Main.inventarioInvocaciones) {
            if (invocacion.isEquipado()) {
                invocacionEquipada = invocacion;
            }
        }

        return invocacionEquipada;
    }

    public static void inicializarJuego() {
        ConexionBD conexionBD = new ConexionBD();

        conexionBD.cargarItemsUsuario(idUsuario);
        conexionBD.cargarPoolObjetosDrop();
        conexionBD.cargarInvocaciones();
    }
}