
package Dao;

import Entidad.Perro;
import java.util.List;


public interface PerroDAO extends MascotaDAO {
    //métodos
    boolean necesitaPaseoDiario(int idPerro);
    boolean tieneMicroChip(int idPerro);
    List<Perro> obtenerPerrosPorRaza(String raza);
}


