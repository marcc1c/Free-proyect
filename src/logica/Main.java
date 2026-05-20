package logica;

import cargar.CargarDatos;
import interfaz.IniciarSesion;
import items.Items;
import invocaciones.Invocacion;
import items.LootEntry;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.HashMap;
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
        JFrame frame = new JFrame("Call the Beast");
        frame.setContentPane(new IniciarSesion().panelIniciarSesion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon logoVentana = new ImageIcon("src/resources/logo.png");
        frame.setIconImage(logoVentana.getImage());

        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void inicializarJuego() {
        inventarioInvocaciones.clear();
        catalogoItems.clear();
        lootPorCalidad.clear();

        CargarDatos cargarDatos = new CargarDatos();
        cargarDatos.cargarJuegoCompleto(idUsuario, lootPorCalidad);
    }

    public static int siguienteIdEnPartida() {
        int maxId = -1;

        for (Invocacion invocacion : inventarioInvocaciones) {
            if (invocacion.getId() > maxId) {
                maxId = invocacion.getId();
            }
        }

        return maxId + 1;
    }

    public static void limpiarDatosSesion() {
        inventarioInvocaciones.clear();
        catalogoItems.clear();
        lootPorCalidad.clear();
        idUsuario = 0;
        pisoTorreInfinita = 0;
        pisoCampana = 0;
        nivelCampana = 0;
    }
}
