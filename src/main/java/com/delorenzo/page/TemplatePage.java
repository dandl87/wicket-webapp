package com.delorenzo.page;

import com.delorenzo.panel.FooterPanel;
import com.delorenzo.panel.HeaderPanel;
import com.delorenzo.panel.MenuPanel;
import com.delorenzo.panel.TechnologyPanel;
import com.delorenzo.persistence.dao.DaoSection;
import com.delorenzo.persistence.dao.DaoTech;
import com.delorenzo.persistence.entity.Section;
import com.delorenzo.persistence.entity.Tech;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class TemplatePage extends WebPage {
    @SpringBean(name="techDaoImpl")
    private DaoTech daoTech;
    @SpringBean(name="sectionDaoImpl")
    private DaoSection daoSection;

    public TemplatePage() throws ClassNotFoundException {
 

        add(new MenuPanel("menuPanel"));
        add(new HeaderPanel("headerPanel")); 
        List<Section> sectionList = daoSection.getAll();
        List<Tech> techList =daoTech.getAll();
        buildPage(sectionList, techList);

        add(new FooterPanel("footerPanel"));
    }

    private void buildPage(List<Section> sectionList, List<Tech> techList) {
        for (long i = 0; i < sectionList.size(); i++) {
            
                List<Tech> techs = new ArrayList<>();

                for (Tech t : techList) {
                    if(t.getSectionId()==i+1)
                    techs.add(t);
                    
                }
                Optional<Section> section = Optional.ofNullable(sectionList.get((int) i));

                if (section.isPresent())
                    addTechnologySection(section.get().getName(), techs, i);

            
        }
        
    }

    private void addTechnologySection(String techSectionName, List<Tech> techList, long i) {

 
        String techSectionNameId = techSectionName.substring(0, 1).toLowerCase() + techSectionName.substring(1);
        add(new ListView<Tech>(techSectionNameId, techList) {

            @Override
            protected void populateItem(ListItem<Tech> listItem) {
                listItem.add(new TechnologyPanel("tech",
                        new Model<String>(listItem.getModelObject().getName())));
            }

        });

    }
}
