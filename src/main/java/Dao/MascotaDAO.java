
package Dao;

import Entidad.EstadoSalud;
import Entidad.Mascota;
import java.util.List;

public interface MascotaDAO {
    Mascota insert(Mascota mascota);
    Mascota selectById(Long id);
    Mascota update(Mascota mascota);
    void delete(Long id);
    List<Mascota> selectAll();

    public List<Mascota> getMascotasPorEstadoSalud(List<EstadoSalud> estadosSalud);
}

    
    /*DAO maneja la interacción con la capa de persistencia para realizar operaciones de 
    almacenamiento y recuperación en los objetos de la aplicación.Acá se definen  las operaciones 
    básicas de almacenamiento y recuperación para las entidades.*/