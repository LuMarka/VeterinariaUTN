package Controlador;

import Dao.ClienteDAO;
import Dao.ClienteDAOImpl;
import Entidad.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDAO;

    public ClienteController() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("Veterinaria_PU").createEntityManager();
        this.clienteDAO = new ClienteDAOImpl(entityManager);
    }

    public Cliente registrarCliente(Cliente cliente) {
        return clienteDAO.insert(cliente);
    }

    public Cliente obtenerCliente(Long id) {
        return clienteDAO.selectById(id);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteDAO.update(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteDAO.delete(id);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.selectAll();
    }
}
