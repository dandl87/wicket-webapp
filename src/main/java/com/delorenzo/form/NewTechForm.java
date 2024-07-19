package com.delorenzo.form;

import com.delorenzo.page.TemplatePage;
import com.delorenzo.persistence.dao.DaoSection;
import com.delorenzo.persistence.dao.DaoTech;
import com.delorenzo.persistence.entity.Article;
import com.delorenzo.persistence.entity.Section;
import com.delorenzo.persistence.entity.Tech;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class NewTechForm extends Form<Void> {
    private final TextField<String> titleField;

    private final DropDownChoice<String> sectionChoise;
    @SpringBean(name="techDaoImpl")
    private DaoTech daoTech;

    @SpringBean(name="sectionDaoImpl")
    private DaoSection daoSection;

    public NewTechForm(String id ) {
        super(id);
        titleField = new TextField<String>("titleField", new Model<>(""));

        add(new Label("createTech", "Inserisci nome Tecnologia"));
        add(titleField);
        List<Section> sections = daoSection.getAll();
        List<String> sectionsAsString = new ArrayList<>();
        for(Section s : sections)
            sectionsAsString.add(s.getName());
        sectionChoise= new DropDownChoice<String>("section", new Model(), sectionsAsString);
        
        add(titleField);
        add(sectionChoise);


    }

    public final void onSubmit() {
        String title = (String)titleField.getDefaultModelObject();
        Tech tech = new Tech();
        tech.setName(title);
        String section = (String)sectionChoise.getDefaultModelObject();

        Long sectionId = daoSection.getByName(section);
        tech.setSectionId(sectionId);
        daoTech.save(tech);
        setResponsePage(TemplatePage.class);
        
        
        
    }
}
