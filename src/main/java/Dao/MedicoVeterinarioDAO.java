package Dao;

import Entidad.MedicoVeterinario;
import java.util.List;


public interface MedicoVeterinarioDAO {
    MedicoVeterinario insert(MedicoVeterinario medico);
    MedicoVeterinario selectById(int id);
    MedicoVeterinario update(MedicoVeterinario medico);
    void delete(int id);
    List<MedicoVeterinario> selectAll();
}
