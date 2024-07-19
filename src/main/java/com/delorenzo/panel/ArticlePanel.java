package com.delorenzo.panel;

import com.delorenzo.persistence.entity.Article;
import com.delorenzo.utils.RowArticle;
import java.util.List;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class ArticlePanel extends Panel {
    public ArticlePanel(String id, IModel<RowArticle> model) {
        super(id, model);
        RowArticle articles = model.getObject();
        List<Article> articlesList = articles.getArticles();

        add(new ListView<Article>("articles", articlesList) {

            @Override
            protected void populateItem(ListItem<Article> item) {
                item.add(new ArticlePostPanel("articlesPanel",
                        new Model<Article>(item.getModelObject())));
            }
        });


    }
}
