package com.delorenzo.form;
 
import com.delorenzo.page.TemplatePage;
import com.delorenzo.persistence.dao.DaoArticle;
import com.delorenzo.persistence.dao.DaoTech;
import com.delorenzo.persistence.entity.Article;
import com.delorenzo.persistence.entity.Tech;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class NewPostForm extends Form<Void> {

    private final TextField<String> titleField;
    private final TextArea<String> contentArea;
    private final DropDownChoice<String> techChoise;
    @SpringBean(name="articleDaoImpl")
    private DaoArticle daoArticle;
    @SpringBean(name="techDaoImpl")
    private DaoTech daoTech;

    public NewPostForm(String id ) {
        super(id); 
        List<Tech> techs = daoTech.getAll();
        List<String> techsAsString = new ArrayList<>();
        for(Tech t : techs)
            techsAsString.add(t.getName());
        titleField = new TextField<String>("titleField", new Model<>(""));
        techChoise= new DropDownChoice<String>("technology", new Model(), techsAsString);
        contentArea = new TextArea<String>("contentArea", new Model<>(""));

        add(new Label("createArticle", "Inserisci nuovo articolo"));
        add(titleField);
        add(techChoise);
        add(contentArea);
         
        
    }
    
    public final void onSubmit() {
        String title = (String)titleField.getDefaultModelObject();
        String content = (String)contentArea.getDefaultModelObject();
        String tech = (String)techChoise.getDefaultModelObject();
        Article article = new Article();
        article.setName(title);
        article.setContent(content);
        Long techId = daoTech.getTechIdByTechName(tech);
        article.setTechId(techId);
        daoArticle.save(article);
        setResponsePage(TemplatePage.class);
    }
    
    
}
