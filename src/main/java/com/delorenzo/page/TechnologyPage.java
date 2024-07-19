package com.delorenzo.page;

import com.delorenzo.panel.ArticlePanel;
import com.delorenzo.panel.HeaderPanel;
import com.delorenzo.panel.MenuPanel; 
import com.delorenzo.persistence.dao.DaoArticle;
import com.delorenzo.persistence.dao.DaoTech; 
import com.delorenzo.persistence.entity.Article;
import com.delorenzo.utils.RowArticle;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TechnologyPage extends WebPage {
    @SpringBean(name="articleDaoImpl")
    private DaoArticle daoArticle;
    @SpringBean(name="techDaoImpl")
    private DaoTech daoTech;

    public TechnologyPage(PageParameters pars) {

        super(pars); 
        add(new MenuPanel("menuPanel"));
        add(new HeaderPanel("headerPanel"));
        add(new Label("techName",pars.get("name"))); 
        
        /*
        list of Articles
        */
        String techName = pars.get("name").toString();
        Long techId =  daoTech.getTechIdByTechName(techName);
        List<Article> articles = daoArticle.getByTechId(techId);
        renderArticlesProperly(articles);

 
        
    }
    
    private void renderArticlesProperly(List<Article> articles){
        int  numberOfArticles = articles.size();
        int numberOfRow = numberOfArticles/4;
        int mod = numberOfArticles%4;
        if(mod>0)
            numberOfRow++;

        int j=0;
        int l=0;
        
        List<RowArticle> articleRows = new ArrayList<>();
        List<Article> articlesParziale;
        for(int i =0; i< numberOfRow; i++) {
            articlesParziale = new ArrayList<Article>();
            for (int k = 0; (k < 4) && (j < (articles.size())); k++) {
                articlesParziale.add(articles.get(j));
                j++;
            }
            RowArticle rowArticle = new RowArticle(articlesParziale);
            articleRows.add(rowArticle);
        }
            add(new ListView<RowArticle>("articlesRow", articleRows) {

                @Override
                protected void populateItem(ListItem<RowArticle> item) {
                    item.add(new ArticlePanel("articlesPanel",
                            item.getModel()));
                }
            });
    }
}
