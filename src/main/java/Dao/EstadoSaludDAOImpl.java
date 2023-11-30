
package Dao;

import Entidad.EstadoSalud;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class EstadoSaludDAOImpl implements EstadoSaludDAO {

    private final EntityManager entityManager;

    public EstadoSaludDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EstadoSalud insert(EstadoSalud estadoSalud) {
        executeInTransaction(() -> {
            entityManager.persist(estadoSalud);
            return null; // Puedes devolver algo si es necesario
        });
        return estadoSalud;
    }

    @Override
    public EstadoSalud selectById(Long id) {
        return entityManager.find(EstadoSalud.class, id);
    }

    @Override
    public EstadoSalud update(EstadoSalud estadoSalud) {
        return executeInTransaction(() -> entityManager.merge(estadoSalud));
    }

    @Override
    public void delete(Long id) {
        executeInTransaction(() -> {
            EstadoSalud estadoSalud = entityManager.find(EstadoSalud.class, id);
            if (estadoSalud != null) {
                entityManager.remove(estadoSalud);
            }
        });
    }

    @Override
    public List<EstadoSalud> selectAll() {
        return entityManager.createQuery("SELECT es FROM EstadoSalud es", EstadoSalud.class).getResultList();
    }

    private void executeInTransaction(Runnable operation) {
        entityManager.getTransaction().begin();
        operation.run();
        entityManager.getTransaction().commit();
    }

    private <T> T executeInTransaction(Operation<T> operation) {
        entityManager.getTransaction().begin();
        T result = operation.execute();
        entityManager.getTransaction().commit();
        return result;
    }

    @FunctionalInterface
    private interface Operation<T> {
        T execute();
    }
}
