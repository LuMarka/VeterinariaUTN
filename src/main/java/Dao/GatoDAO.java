
package Dao;

import Entidad.Gato;
import java.util.List;


public interface GatoDAO extends MascotaDAO{
   
    // Métodos específicos para Gato si es necesario
    boolean esCazador(int idGato);
    int cantidadHorasDescanso(int idGato);
    boolean necesitaCajaArena(int idGato);
    boolean juegaConOtrosGatos(int idGato);
    String patronPelaje(int idGato);
    List<Gato> obtenerGatosPorRaza(String raza);
}
