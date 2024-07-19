package com.delorenzo.page;

import com.delorenzo.form.NewSectionForm;
import com.delorenzo.form.NewTechForm;
import com.delorenzo.panel.HeaderPanel;
import com.delorenzo.panel.MenuPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class NewTechPage extends WebPage {
    public NewTechPage(PageParameters parameters) {
        super(parameters);
        add(new MenuPanel("menuPanel"));
        add(new HeaderPanel("headerPanel"));
        Form<Void> form = new NewTechForm("newTechForm");
        add(form);
    }
}
