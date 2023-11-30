package Dao;

import Entidad.EstadoSalud;
import Entidad.Mascota;
import Entidad.Perro;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PerroDAOImpl extends MascotaDAOImpl implements PerroDAO {

    public PerroDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public boolean necesitaPaseoDiario(int idPerro) {
        // Simulación de la lógica: Obtener información del perro desde la base de datos
        Perro perro = entityManager.find(Perro.class, idPerro);
        // Lógica específica para verificar si un perro necesita paseo diario
        return perro != null && perro.isNecesitaPaseoDiario();
    }

    @Override
    public boolean tieneMicroChip(int idPerro) {
        // Obtener información del perro desde la base de datos
        Perro perro = entityManager.find(Perro.class, idPerro);
        // Verificar si un perro tiene microchip
        return perro != null && perro.isTieneMicroChip();
    }

    @Override
    public List<Perro> obtenerPerrosPorRaza(String raza) {
        // Obtener perros por raza desde la base de datos
        Query query = entityManager.createQuery("SELECT p FROM Perro p WHERE p.raza = :raza", Perro.class);
        query.setParameter("raza", raza);
        return query.getResultList();
    }

    // Implementación del método getMascotasPorEstadoSalud heredado de MascotaDAOImpl
    @Override
    public List<Mascota> getMascotasPorEstadoSalud(List<EstadoSalud> estadosSalud) {
        // Implementa la lógica para obtener perros por estado de salud si es diferente de la clase MascotaDAOImpl
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
