
package Dao;

import Entidad.TurnoMedico;
import javax.persistence.EntityManager;
import java.util.List;

public class TurnoMedicoDAOImpl implements TurnoMedicoDAO {

    private final EntityManager entityManager;

    public TurnoMedicoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TurnoMedico insert(TurnoMedico turnoMedico) {
        executeInTransaction(() -> {
            entityManager.persist(turnoMedico);
            return null; // Puedes devolver algo si es necesario
        });
        return turnoMedico;
    }

    @Override
    public TurnoMedico selectById(Long id) {
        return entityManager.find(TurnoMedico.class, id);
    }

    @Override
    public TurnoMedico update(TurnoMedico turnoMedico) {
        return executeInTransaction(() -> entityManager.merge(turnoMedico));
    }

    @Override
    public void delete(Long id) {
        executeInTransaction(() -> {
            TurnoMedico turnoMedico = entityManager.find(TurnoMedico.class, id);
            if (turnoMedico != null) {
                entityManager.remove(turnoMedico);
            }
        });
    }

    @Override
    public List<TurnoMedico> selectAll() {
        return entityManager.createQuery("SELECT tm FROM TurnoMedico tm", TurnoMedico.class).getResultList();
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
