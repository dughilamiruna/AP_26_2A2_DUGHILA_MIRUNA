package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PlayerRepository
{
    private final EntityManagerFactory emf;

    public PlayerRepository(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public void save(Player player)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.merge(player);
            em.getTransaction().commit();
        }
        catch (Exception exception) {
            em.getTransaction().rollback();
            System.err.println("Error at saving in data base: " + exception.getMessage());
        }
        finally {
            em.close();
        }
    }

    public Player findByName(String name)
    {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name)
                    .getSingleResult();
        }
        catch (Exception exception) {
            return null;
        }
        finally {
            em.close();
        }
    }
}