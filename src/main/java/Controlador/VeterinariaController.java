package Controlador;

import Dao.MascotaDAO;
import Dao.MascotaDAOImpl;
import Dao.MedicoVeterinarioDAO;
import Dao.MedicoVeterinarioDAOImpl;
import Dao.TurnoMedicoDAO;
import Dao.TurnoMedicoDAOImpl;
import Entidad.EstadoSalud;
import Entidad.Mascota;
import Entidad.MedicoVeterinario;
import Entidad.TurnoMedico;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class VeterinariaController {

    private final MascotaDAO mascotaDAO;
    private final TurnoMedicoDAO turnoMedicoDAO;
    private final MedicoVeterinarioDAO medicoVeterinarioDAO;

    public VeterinariaController() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Veterinaria_PU").createEntityManager();
        this.mascotaDAO = new MascotaDAOImpl(entityManager) {
            @Override
            public List<Mascota> getMascotasPorEstadoSalud(List<EstadoSalud> estadosSalud) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        this.turnoMedicoDAO = new TurnoMedicoDAOImpl(entityManager);
        this.medicoVeterinarioDAO = new MedicoVeterinarioDAOImpl(entityManager);
    }

    public Mascota registrarMascota(Mascota mascota) {
        return mascotaDAO.insert(mascota);
    }

    public Mascota obtenerMascota(Long id) {
        return mascotaDAO.selectById(id);
    }

    public Mascota actualizarMascota(Mascota mascota) {
        return mascotaDAO.update(mascota);
    }

    public void eliminarMascota(Long id) {
        mascotaDAO.delete(id);
    }

    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaDAO.selectAll();
    }

    public TurnoMedico asignarTurno(TurnoMedico turnoMedico) {
        return turnoMedicoDAO.insert(turnoMedico);
    }

    public MedicoVeterinario registrarMedicoVeterinario(MedicoVeterinario medico) {
        return medicoVeterinarioDAO.insert(medico);
    }

    public MedicoVeterinario obtenerMedicoVeterinario(int id) {
        return medicoVeterinarioDAO.selectById(id);
    }

    public MedicoVeterinario actualizarMedicoVeterinario(MedicoVeterinario medico) {
        return medicoVeterinarioDAO.update(medico);
    }

    public void eliminarMedicoVeterinario(int id) {
        medicoVeterinarioDAO.delete(id);
    }

    public List<MedicoVeterinario> obtenerTodosLosMedicosVeterinarios() {
        return medicoVeterinarioDAO.selectAll();
    }

}

