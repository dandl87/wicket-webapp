package com.delorenzo.persistence.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name  = "section")
@SequenceGenerator(name="SectionSeq", sequenceName="public.seqSection")
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SectionSeq")
    private Long id;
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
}
