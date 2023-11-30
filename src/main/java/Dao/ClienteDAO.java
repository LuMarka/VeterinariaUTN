
package Dao;

import Entidad.Cliente;
import java.util.List;


public interface ClienteDAO {
    Cliente insert(Cliente cliente);
    Cliente selectById(Long id);
    Cliente update(Cliente cliente);
    void delete(Long id);
    List<Cliente> selectAll();
}
