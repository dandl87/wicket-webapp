package com.delorenzo.persistence.dao;

import com.delorenzo.persistence.entity.Article;
import java.util.List;

public interface DaoArticle extends Dao<Article>{

    List<Article> getByTechId(Long id);
}
