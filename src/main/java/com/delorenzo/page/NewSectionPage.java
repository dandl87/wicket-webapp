package com.delorenzo.page;

import com.delorenzo.form.NewPostForm;
import com.delorenzo.form.NewSectionForm;
import com.delorenzo.panel.HeaderPanel;
import com.delorenzo.panel.MenuPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class NewSectionPage extends WebPage {
    public NewSectionPage(PageParameters parameters) {
        super(parameters);
        add(new MenuPanel("menuPanel"));
        add(new HeaderPanel("headerPanel"));
        Form<Void> form = new NewSectionForm("newSectionForm");
        add(form);
    }
}
