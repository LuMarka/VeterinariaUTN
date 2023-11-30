package Controlador;

import Dao.MedicoVeterinarioDAO;
import Dao.MedicoVeterinarioDAOImpl;
import Entidad.MedicoVeterinario;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class MedicoVeterinarioController {

    private final MedicoVeterinarioDAO medicoVeterinarioDAO;

    public MedicoVeterinarioController() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Veterinaria_PU").createEntityManager();
        this.medicoVeterinarioDAO = new MedicoVeterinarioDAOImpl(entityManager);
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


    // Otros métodos para CRUD y funcionalidades adicionales de MedicoVeterinario
    /*VeterinariaController y el MedicoVeterinarioController manejan las operaciones 
    relacionadas con las mascotas y los médicos veterinarios respectivamente.
    Aca se definen operaciones CRUD de mascotas o médicos veterinarios. Los métodos 
    en estos controladores corresponden a las operaciones específicas que un cliente 
    (podría ser una aplicación web, una aplicación móvil, etc.) puede solicitar.
    Los controladores pueden utilizar los DAOs para interactuar con la capa de 
    persistencia cuando es necesario.*/


