package com.delorenzo.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name  = "tech")
@SequenceGenerator(name="TechSeq", sequenceName="public.seqTech")
public class  Tech implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TechSeq")
    private Long id;
    @Column(name = "section_id")
    private Long sectionId;
    private String name; 

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }
}
