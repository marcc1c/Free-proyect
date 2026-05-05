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
    public static int idUsuario = 1;

    public static void main(String[] args) {
        CargarLootEntry cargarLootEntry = new CargarLootEntry();
        lootPorCalidad = cargarLootEntry.CargarLootEntry();

        CargarItems cargarItems = new CargarItems();
        catalogoItems = cargarItems.cargarItemsUsuario(1);

        for (Items a: catalogoItems) {
            System.out.println(a);
        }
        for (Map.Entry<String, List<LootEntry>> entrada : Main.lootPorCalidad.entrySet()) {
            String calidad = entrada.getKey();
            List<LootEntry> listaLoot = entrada.getValue();

            System.out.println("Calidad: " + calidad);

            for (LootEntry lootEntry : listaLoot) {
                System.out.println("ID item: " + lootEntry.getIdObjeto());
                System.out.println("Probabilidad: " + lootEntry.getPorcentaje());
                System.out.println("Cantidad mínima: " + lootEntry.getCantidadMinima());
                System.out.println("Cantidad máxima: " + lootEntry.getCantidadMaxima());
                System.out.println("----------------------");
            }
        }

    }
    public static Invocacion saberEquipada(){
        Invocacion invocacionEquipada = null;
        for (Invocacion invocacion : inventario) {
            if (invocacion.isEquipado()) {
                invocacionEquipada = invocacion;
            }
        }
        return invocacionEquipada;
    }
}