
package Persistencia;

import Entidad.Enfermo;
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


public class EnfermoJpaController implements Serializable {

    public EnfermoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
        public EnfermoJpaController (){
        emf = Persistence.createEntityManagerFactory("Veterinaria_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enfermo enfermo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(enfermo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnfermo(enfermo.getId()) != null) {
                throw new PreexistingEntityException("Enfermo " + enfermo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enfermo enfermo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            enfermo = em.merge(enfermo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = enfermo.getId();
                if (findEnfermo(id) == null) {
                    throw new NonexistentEntityException("The enfermo with id " + id + " no longer exists.");
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
            Enfermo enfermo;
            try {
                enfermo = em.getReference(Enfermo.class, id);
                enfermo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enfermo with id " + id + " no longer exists.", enfe);
            }
            em.remove(enfermo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enfermo> findEnfermoEntities() {
        return findEnfermoEntities(true, -1, -1);
    }

    public List<Enfermo> findEnfermoEntities(int maxResults, int firstResult) {
        return findEnfermoEntities(false, maxResults, firstResult);
    }

    private List<Enfermo> findEnfermoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enfermo.class));
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

    public Enfermo findEnfermo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enfermo.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnfermoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enfermo> rt = cq.from(Enfermo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
