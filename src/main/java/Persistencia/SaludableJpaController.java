/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Entidad.Saludable;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Admin
 */
public class SaludableJpaController implements Serializable {

    public SaludableJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
        public SaludableJpaController (){
        emf = Persistence.createEntityManagerFactory("Veterinaria_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Saludable saludable) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(saludable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSaludable(saludable.getId()) != null) {
                throw new PreexistingEntityException("Saludable " + saludable + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Saludable saludable) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            saludable = em.merge(saludable);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = saludable.getId();
                if (findSaludable(id) == null) {
                    throw new NonexistentEntityException("The saludable with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Saludable saludable;
            try {
                saludable = em.getReference(Saludable.class, id);
                saludable.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The saludable with id " + id + " no longer exists.", enfe);
            }
            em.remove(saludable);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Saludable> findSaludableEntities() {
        return findSaludableEntities(true, -1, -1);
    }

    public List<Saludable> findSaludableEntities(int maxResults, int firstResult) {
        return findSaludableEntities(false, maxResults, firstResult);
    }

    private List<Saludable> findSaludableEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Saludable.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Saludable findSaludable(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Saludable.class, id);
        } finally {
            em.close();
        }
    }

    public int getSaludableCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Saludable> rt = cq.from(Saludable.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
