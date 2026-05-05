package logica;

import cargar.CargarItems;
import cargar.CargarLootEntry;
import invocaciones.Invocacion;
import items.Items;
import items.LootEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static ArrayList<Invocacion> inventario = new ArrayList<>();
    public static ArrayList<Items> catalogoItems = new ArrayList<>();
    public static Map<String, List<LootEntry>> lootPorCalidad;

    public static void main(String[] args) {
        CargarLootEntry cargarLootEntry = new CargarLootEntry();
        lootPorCalidad = cargarLootEntry.CargarLootEntry();

        CargarItems cargarItems = new CargarItems();
        catalogoItems = cargarItems.cargarItems();
    }

}