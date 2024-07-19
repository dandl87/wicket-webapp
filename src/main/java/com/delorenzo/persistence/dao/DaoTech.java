package com.delorenzo.persistence.dao;

import com.delorenzo.persistence.entity.Tech;
import java.util.List;

public interface DaoTech extends Dao<Tech>{
    List<Tech> getBySectionId(Long id);
    Long getTechIdByTechName(String name);
}
