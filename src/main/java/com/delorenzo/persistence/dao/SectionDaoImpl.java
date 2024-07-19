package com.delorenzo.persistence.dao;

import com.delorenzo.persistence.entity.Section;
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
public class SectionDaoImpl implements DaoSection, Serializable {
    private final EntityManager entityManager;
    public SectionDaoImpl(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("blog_pu");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Section> get(Long id) {
        return Optional.ofNullable(entityManager.find(Section.class, id));
    }

    @Override
    public List<Section> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Section e");
        return query.getResultList();
    }

    @Override
    public void save(Section section) {
        executeInsideTransaction(entityManager -> entityManager.persist(section));

    }

    @Override
    public void update(Section section, String[] params) {
        section.setName(Objects.requireNonNull(params[0],"Name cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(section));
    }

    @Override
    public void delete(Section section) {
        executeInsideTransaction(entityManager -> entityManager.remove(section));
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

    @Override
    public Long getByName(String name) {
        Query query = entityManager.createQuery("SELECT s FROM Section s WHERE s.name='"+name+"'");
        Section section = (Section) query.getSingleResult();
        return section.getId();
    }
}
