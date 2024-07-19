package com.delorenzo.page;

import com.delorenzo.panel.ArticleContentPanel;
import com.delorenzo.panel.HeaderPanel;
import com.delorenzo.panel.MenuPanel;
import com.delorenzo.persistence.dao.DaoArticle;
import com.delorenzo.persistence.entity.Article;
import java.util.Optional;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ArticlePage extends WebPage {
    Article article;
    @SpringBean(name="articleDaoImpl")
    private DaoArticle daoArticle;
    public ArticlePage(PageParameters pars) {
        super(pars);

        
        add(new MenuPanel("menuPanel")); 
        add(new HeaderPanel("headerPanel"));
        Optional<Article> articleOptional = daoArticle.get(Long.valueOf(String.valueOf(pars.get("articleId"))));
        if(articleOptional.isPresent()) {
            article = articleOptional.get();
        }else{
            article = new Article();
            article.setName("Error");
            article.setContent("No Article Found");
        }
        add(new Label("articleName", article.getName()));
        add(new ArticleContentPanel("articleContent", new Model<String>(article.getContent())));
        
        

    }
}
