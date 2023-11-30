package Controlador;

import Dao.TurnoMedicoDAO;
import Dao.TurnoMedicoDAOImpl;
import Entidad.TurnoMedico;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TurnoMedicoController {

    private final TurnoMedicoDAO turnoMedicoDAO;

    public TurnoMedicoController() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Veterinaria_PU").createEntityManager();
        this.turnoMedicoDAO = new TurnoMedicoDAOImpl(entityManager);
    }

    public TurnoMedico registrarTurnoMedico(TurnoMedico turnoMedico) {
        return turnoMedicoDAO.insert(turnoMedico);
    }

    public TurnoMedico obtenerTurnoMedico(Long id) {
        return turnoMedicoDAO.selectById(id);
    }

    public TurnoMedico actualizarTurnoMedico(TurnoMedico turnoMedico) {
        return turnoMedicoDAO.update(turnoMedico);
    }

    public void eliminarTurnoMedico(Long id) {
        turnoMedicoDAO.delete(id);
    }

    public List<TurnoMedico> obtenerTodosLosTurnosMedicos() {
        return turnoMedicoDAO.selectAll();
    }
}

