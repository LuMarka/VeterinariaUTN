package Controlador;

import Dao.GatoDAO;
import Dao.GatoDAOImpl;
import Entidad.Gato;
import Entidad.Mascota;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class GatoController {

    private final GatoDAO gatoDAO;

    public GatoController() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Veterinaria_PU").createEntityManager();
        this.gatoDAO = new GatoDAOImpl(entityManager);
    }

    public Gato registrarGato(Gato gato) {
        return (Gato) gatoDAO.insert(gato);
    }

    public Gato obtenerGato(Long id) {
        return (Gato) gatoDAO.selectById(id);
    }

    public Gato actualizarGato(Gato gato) {
        return (Gato) gatoDAO.update(gato);
    }

    public void eliminarGato(Long id) {
        gatoDAO.delete(id);
    }

    public List<Mascota> obtenerTodosLosGatos() {
        return gatoDAO.selectAll();
    }
}

