package com.delorenzo.panel;

import com.delorenzo.page.TechnologyPage;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class TechnologyPanel extends Panel {
    private final String name;
    private final Image img;
    private final BookmarkablePageLink<Void> link;



    public TechnologyPanel(String id, Model<String> model) {
        super(id);
        name = model.getObject();
        img = new Image("image") {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                
                tag.put("src", "assets/images/"+name+".jpg");
            }
        };
        PageParameters pars = new PageParameters();
        pars.add("name", name);
        link = new BookmarkablePageLink<>("link", TechnologyPage.class, pars);
        add(link);
        link.add(img);
         
        


    }

}
