package com.mycompany.veterinaria;
import Entidad.Enfermo;
import Entidad.Perro;
import Entidad.Saludable;
//import Persistencia.ControladoraPersistencia;
import java.sql.SQLException;
//import java.sql.Statement;


public class Veterinaria {
   // public static Conexion conexion = new Conexion();
    
    public static void main(String[] args) throws SQLException {
        
     /*  conexion.estableceConexion();
        
        Statement stmt = conexion.conectar.createStatement();
        stmt.executeUpdate("INSERT INTO Perro VALUES(\"Conan\")");
        // stmt.executeUpdate("CREATE INTO Perro VALUES(\"id\")");?????
        
        conexion.cerrarConexion();*/
        
        
        // Crear un perro
        
       // ControladoraPersistencia controlPersis = new ControladoraPersistencia();
        
        Perro miPerro = new Perro();
        miPerro.setNombre("Max");
        miPerro.setEstadoSalud(new Saludable());

        // Verificar salud inicial
        System.out.println("Salud inicial de mi perro " + miPerro.getNombre() + " es: "+ miPerro.getDescripcionEstadoSalud());

        // Cambiar el estado de salud a Enfermo
        miPerro.setEstadoSalud(new Enfermo());

        // Verificar salud después del cambio
        System.out.println("Ahora el estado de salud de mi mascota " + miPerro.getNombre() +" es " + miPerro.getDescripcionEstadoSalud());
    }
    
}
