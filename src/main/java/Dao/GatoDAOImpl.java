package Dao;

import Entidad.EstadoSalud;
import Entidad.Gato;
import Entidad.Mascota;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.Query;

public class GatoDAOImpl extends MascotaDAOImpl implements GatoDAO {

    public GatoDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public boolean esCazador(int idGato) {
        // Obtener información del gato desde la base de datos
        Gato gato = entityManager.find(Gato.class, idGato);
        // Verificar si un gato es cazador
        return gato != null && gato.isEsCazador();
    }

    @Override
    public int cantidadHorasDescanso(int idGato) {
        // Obtener información del gato desde la base de datos
        Gato gato = entityManager.find(Gato.class, idGato);
        // Obtener la cantidad de horas de descanso de un gato
        return (gato != null) ? gato.getCantidadHorasDescanso() : 0;
    }

    @Override
    public boolean necesitaCajaArena(int idGato) {
        // Obtener información del gato desde la base de datos
        Gato gato = entityManager.find(Gato.class, idGato);
        // Verificar si un gato necesita caja de arena
        return gato != null && gato.isNecesitaCajaArena();
    }

    @Override
    public boolean juegaConOtrosGatos(int idGato) {
        // Obtener información del gato desde la base de datos
        Gato gato = entityManager.find(Gato.class, idGato);
        // Verificar si un gato juega con otros gatos
        return gato != null && gato.isJuegaConOtrosGatos();
    }

    @Override
    public String patronPelaje(int idGato) {
        // Obtener información del gato desde la base de datos
        Gato gato = entityManager.find(Gato.class, idGato);
        // Obtener el patrón de pelaje de un gato
        return (gato != null) ? gato.getPatronPelaje() : "";
    }


    // Método específico de GatoDAO para obtener gatos por raza
    @Override
    public List<Gato> obtenerGatosPorRaza(String raza) {
        // Obtener gatos por raza desde la base de datos
        Query query = entityManager.createQuery("SELECT g FROM Gato g WHERE g.raza = :raza", Gato.class);
        query.setParameter("raza", raza);
        return query.getResultList();
    }

    @Override
    public List<Mascota> getMascotasPorEstadoSalud(List<EstadoSalud> estadosSalud) {
    // Crear una consulta para obtener las mascotas por estado de salud
    @SuppressWarnings("unchecked")
    Query query = entityManager.createQuery("SELECT m FROM Mascota m WHERE m.estadoSalud IN :estadosSalud", Mascota.class);
    query.setParameter("estadosSalud", estadosSalud);
    
    // Ejecutar la consulta y devolver el resultado
    return query.getResultList();
    }
}


