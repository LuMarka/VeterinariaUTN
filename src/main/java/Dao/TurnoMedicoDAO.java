
package Dao;

import Entidad.TurnoMedico;
import java.util.List;

public interface TurnoMedicoDAO {
    TurnoMedico insert(TurnoMedico turnoMedico);
    TurnoMedico selectById(Long id);
    TurnoMedico update(TurnoMedico turnoMedico);
    void delete(Long id);
    List<TurnoMedico> selectAll();
}
