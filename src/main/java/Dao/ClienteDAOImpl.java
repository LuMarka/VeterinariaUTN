
package Dao;

import Entidad.Cliente;
import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    private final EntityManager entityManager;

    public ClienteDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cliente insert(Cliente cliente) {
        executeInTransaction(() -> {
            entityManager.persist(cliente);
            return null; // Puedes devolver algo si es necesario
        });
        return cliente;
    }

    @Override
    public Cliente selectById(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return executeInTransaction(() -> entityManager.merge(cliente));
    }

    @Override
    public void delete(Long id) {
        executeInTransaction(() -> {
            Cliente cliente = entityManager.find(Cliente.class, id);
            if (cliente != null) {
                entityManager.remove(cliente);
            }
        });
    }

    @Override
    public List<Cliente> selectAll() {
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
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
