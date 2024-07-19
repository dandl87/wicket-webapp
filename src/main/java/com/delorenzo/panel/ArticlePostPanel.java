package com.delorenzo.panel;

import com.delorenzo.page.ArticlePage;
import com.delorenzo.persistence.entity.Article;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ArticlePostPanel extends Panel {
    private final Article article;

    public ArticlePostPanel(String id, Model<Article> model) {
        super(id);
        article = model.getObject();

        PageParameters pars = new PageParameters(); 
        pars.add("articleId", article.getId());
        add(new BookmarkablePageLink<>("link", ArticlePage.class, pars).add(new Label("label", article.getName())));



    }

}
