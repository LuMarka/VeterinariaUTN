package Controlador;

import Dao.PerroDAO;
import Dao.PerroDAOImpl;
import Entidad.Mascota;
import Entidad.Perro;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class PerroController {

    private final PerroDAO perroDAO;

    public PerroController() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Veterinaria_PU").createEntityManager();
        this.perroDAO = new PerroDAOImpl(entityManager);
    }

    public Perro registrarPerro(Perro perro) {
        return (Perro) perroDAO.insert(perro);
    }

    public Perro obtenerPerro(Long id) {
        return (Perro) perroDAO.selectById(id);
    }

    public Perro actualizarPerro(Perro perro) {
        return (Perro) perroDAO.update(perro);
    }

    public void eliminarPerro(Long id) {
        perroDAO.delete(id);
    }

    public List<Mascota> obtenerTodosLosPerros() {
        return perroDAO.selectAll();
    }
}

