package com.delorenzo.panel;

import com.delorenzo.page.NewPostPage;
import com.delorenzo.page.NewSectionPage;
import com.delorenzo.page.NewTechPage;
import com.delorenzo.page.TemplatePage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuPanel extends Panel {
    public MenuPanel(String id) {
        super(id);
        add(new BookmarkablePageLink<>("home", TemplatePage.class));
        add(new BookmarkablePageLink<>("newPost", NewPostPage.class));
        add(new BookmarkablePageLink<>("newSection", NewSectionPage.class));
        add(new BookmarkablePageLink<>("newTech", NewTechPage.class));
    }
}
