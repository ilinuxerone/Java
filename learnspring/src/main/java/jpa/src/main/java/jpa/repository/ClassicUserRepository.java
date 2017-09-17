package jpa.repository;

import jpa.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Repository
public class ClassicUserRepository {
    @PersistenceContext
    EntityManager em;

    public List<User> findByName(String name) {
        return getEntityManger()
                .createNamedQuery("User.classicQuery", User.class)
                .setParameter("name", name)
                .getResultList();
    }


    private EntityManager getEntityManger() {
        return em;
    }
}
