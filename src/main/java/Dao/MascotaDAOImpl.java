
package Dao;

import Entidad.Mascota;
import java.util.List;
import javax.persistence.EntityManager;


public abstract class MascotaDAOImpl implements MascotaDAO {

    final EntityManager entityManager;

    public MascotaDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Mascota insert(Mascota mascota) {
        return executeInTransaction(() -> {
            entityManager.persist(mascota);
            return mascota;
        });
    }  

    @Override
    public Mascota selectById(Long id) {
        return entityManager.find(Mascota.class, id);
    }

    @Override
    public Mascota update(Mascota mascota) {
        return executeInTransaction(() -> entityManager.merge(mascota));
    }

    @Override
    public void delete(Long id) {
        executeInTransaction(() -> {
            Mascota mascota = entityManager.find(Mascota.class, id);
            if (mascota != null) {
                entityManager.remove(mascota);
            }
        });
    }

    @Override
    public List<Mascota> selectAll() {
        return entityManager.createQuery("SELECT m FROM Mascota m", Mascota.class).getResultList();
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
