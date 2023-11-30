package Dao;


import Entidad.MedicoVeterinario;

import javax.persistence.EntityManager;
import java.util.List;

public class MedicoVeterinarioDAOImpl implements MedicoVeterinarioDAO {

    private final EntityManager entityManager;

    public MedicoVeterinarioDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MedicoVeterinario insert(MedicoVeterinario medico) {
        return executeInTransaction(() -> {
            entityManager.persist(medico);
            return null;
        }, medico);
    }

    @Override
    public MedicoVeterinario selectById(int id) {
        return entityManager.find(MedicoVeterinario.class, id);
    }

    @Override
    public MedicoVeterinario update(MedicoVeterinario medico) {
        return executeInTransaction(() -> entityManager.merge(medico), medico);
    }

    @Override
    public void delete(int id) {
        executeInTransaction(() -> {
            MedicoVeterinario medico = entityManager.find(MedicoVeterinario.class, id);
            if (medico != null) {
                entityManager.remove(medico);
            }
        });
    }

    @Override
    public List<MedicoVeterinario> selectAll() {
        return entityManager.createQuery("SELECT mv FROM MedicoVeterinario mv", MedicoVeterinario.class).getResultList();
    }

    private <T> T executeInTransaction(Operation<T> operation, T result) {
        entityManager.getTransaction().begin();
        result = operation.execute();
        entityManager.getTransaction().commit();
        return result;
    }

    private void executeInTransaction(Runnable operation) {
        entityManager.getTransaction().begin();
        operation.run();
        entityManager.getTransaction().commit();
    }

    @FunctionalInterface
    private interface Operation<T> {
        T execute();
    }
}
