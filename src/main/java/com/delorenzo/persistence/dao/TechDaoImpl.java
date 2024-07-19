package com.delorenzo.persistence.dao;

import com.delorenzo.persistence.entity.Tech;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
@Named
public class TechDaoImpl implements DaoTech, Serializable {

    private final EntityManager entityManager;
    public TechDaoImpl(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("blog_pu");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Tech> get(Long id) {
        return Optional.ofNullable(entityManager.find(Tech.class, id));
    }

    @Override
    public List<Tech> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Tech e");
        return query.getResultList();
    }

    @Override
    public void save(Tech tech) {
        executeInsideTransaction(entityManager -> entityManager.persist(tech));

    }

    @Override
    public void update(Tech tech, String[] params) {
        tech.setName(Objects.requireNonNull(params[0],"Name cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(tech));
    }

    @Override
    public void delete(Tech tech) {
        executeInsideTransaction(entityManager -> entityManager.remove(tech));
    }
    
    public List<Tech> getBySectionId(Long id){
        Query query = entityManager.createQuery("SELECT e FROM Tech e WHERE e.sectionId="+id);
        return query.getResultList();
        
    }

    @Override
    public Long getTechIdByTechName(String name) {
        Query query = entityManager.createQuery("SELECT e FROM Tech e WHERE e.name='"+name+"'");
        Tech t = (Tech) query.getSingleResult();
        return (Long)t.getId();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

}
