package cargar;

import items.LootEntry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CargarLootEntry {

    public Map<String, List<LootEntry>> CargarLootEntry() {
        Map<String, List<LootEntry>> lootPorCalidad = new HashMap<>();

        String select = "SELECT calidad_enemigo, id_item, porcentaje, cantidad_minima, cantidad_maxima FROM LOOT_CALIDAD";

        try {
            Connection conexion = ConexionBD.conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(select);

            while (rs.next()) {
                String calidadEnemigo = rs.getString("calidad_enemigo");
                int idObjeto = rs.getInt("id_objeto");
                double porcentaje = rs.getDouble("porcentaje");
                int cantidadMinima = rs.getInt("cantidad_minima");
                int cantidadMaxima = rs.getInt("cantidad_maxima");

                LootEntry lootEntry = new LootEntry(idObjeto, porcentaje, cantidadMinima, cantidadMaxima);

                if (!lootPorCalidad.containsKey(calidadEnemigo)) {
                    lootPorCalidad.put(calidadEnemigo, new ArrayList<>());
                }

                lootPorCalidad.get(calidadEnemigo).add(lootEntry);
            }

            rs.close();
            st.close();
            conexion.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lootPorCalidad;
    }
}