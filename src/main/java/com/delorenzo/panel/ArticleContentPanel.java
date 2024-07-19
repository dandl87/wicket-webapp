package com.delorenzo.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class ArticleContentPanel extends Panel {
    public ArticleContentPanel(String id, IModel<String> model) {
        super(id, model);
        String html = model.getObject();
        String dynamicHtml = createDynamicHtml("contentHtml",html);
        Label divLabel = new Label("htmlLabel",dynamicHtml );

        //Setted EscapeModelStrings False for the label
        divLabel.setEscapeModelStrings(false);

        //added label to panel here
        add(divLabel);

    }
    public String createDynamicHtml(String div_id,String content) {

        //created a instance named "divSB " of StringBuilder here
        StringBuilder divSB = new StringBuilder(512);

        /**
         *appended dynamic html code to divscriptSB instance of StringBuilder
         */
        divSB.append("<div id=\"");
        divSB.append(div_id);
        divSB.append("\">");
        divSB.append(content);
        divSB.append("</div>");

        //return StringBuilder instance as a String which contains our dynamic html code
        return divSB.toString();
    }
}
