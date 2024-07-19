package com.delorenzo.persistence.dao;

import com.delorenzo.persistence.entity.Article; 
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
public class ArticleDaoImpl implements DaoArticle, Serializable {
    private final EntityManager entityManager;
    public ArticleDaoImpl(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("blog_pu");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Article> get(Long id) {
        return Optional.ofNullable(entityManager.find(Article.class, id));
    }

    @Override
    public List<Article> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Article e");
        return query.getResultList();
    }

    @Override
    public void save(Article article) {
        executeInsideTransaction(entityManager -> entityManager.persist(article));

    }

    @Override
    public void update(Article article, String[] params) {
        article.setName(Objects.requireNonNull(params[0],"Name cannot be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(article));
    }

    @Override
    public void delete(Article article) {
        executeInsideTransaction(entityManager -> entityManager.remove(article));
    }

    public List<Article> getByTechId(Long id){
        Query query = entityManager.createQuery("SELECT e FROM Article e WHERE e.techId="+id);
        return query.getResultList();

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
