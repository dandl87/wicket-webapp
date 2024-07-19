package com.delorenzo.form;

import com.delorenzo.page.TemplatePage;
import com.delorenzo.persistence.dao.DaoSection;
import com.delorenzo.persistence.entity.Section;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class NewSectionForm extends Form<Void> {    
    private final TextField<String> titleField;
    @SpringBean(name="sectionDaoImpl")
    private DaoSection daoSection;

    public NewSectionForm(String id ) {
        super(id);
        titleField = new TextField<String>("titleField", new Model<>(""));

        add(new Label("createSection", "Inserisci nome Sezione"));
        add(titleField);
        
        
         
        
    }
    
    public final void onSubmit() {
        String title = (String)titleField.getDefaultModelObject();
        Section section = new Section();
        section.setName(title);
        daoSection.save(section);
        setResponsePage(TemplatePage.class);
    }
}
