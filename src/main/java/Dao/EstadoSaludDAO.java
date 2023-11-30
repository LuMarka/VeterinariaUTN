
package Dao;

import Entidad.EstadoSalud;
import Entidad.Mascota;
import java.util.List;


public interface EstadoSaludDAO {
    EstadoSalud insert(EstadoSalud estadoSalud);
    EstadoSalud selectById(Long id);
    EstadoSalud update(EstadoSalud estadoSalud);
    void delete(Long id);
    List<EstadoSalud> selectAll();

    public List<EstadoSalud> selectAllByDescripcion(String descripcionEstado);

    public List<Mascota> getMascotasPorEstadoSalud(List<EstadoSalud> estadosSalud);
}
