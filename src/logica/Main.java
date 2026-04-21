package logica;

import invocaciones.Invocacion;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Invocacion> inventario = new ArrayList<>();

    public static void main(String[] args) {

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