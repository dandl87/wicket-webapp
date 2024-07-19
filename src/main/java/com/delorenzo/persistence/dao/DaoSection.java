package com.delorenzo.persistence.dao;

import com.delorenzo.persistence.entity.Article;
import com.delorenzo.persistence.entity.Section;
import java.util.List;

public interface DaoSection extends Dao<Section>{

    Long  getByName(String name);


}
