package Controlador;

import Dao.EstadoSaludDAO;
import Dao.EstadoSaludDAOImpl;
import Entidad.EstadoSalud;
import Entidad.Mascota;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class EstadoSaludController {

    private final EstadoSaludDAO estadoSaludDAO;

    public EstadoSaludController() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Veterinaria_PU").createEntityManager();
        this.estadoSaludDAO = new EstadoSaludDAOImpl(entityManager) {
            @Override
            public List<EstadoSalud> selectAllByDescripcion(String descripcionEstado) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public List<Mascota> getMascotasPorEstadoSalud(List<EstadoSalud> estadosSalud) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
    }
        // Método de ejemplo que utiliza estadoSaludDAO
            public void listaEstadoSalud() {
            List<EstadoSalud> estadosSalud = estadoSaludDAO.selectAllByDescripcion("Saludable");
                // Imprimir información sobre los estados de salud
            for (EstadoSalud estado : estadosSalud) {
                System.out.println("Descripción: " + estado.getDescripcion());
            }
        }
}


    

    
        
      
