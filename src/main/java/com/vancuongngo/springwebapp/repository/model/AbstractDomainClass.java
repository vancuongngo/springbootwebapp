package com.vancuongngo.springwebapp.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class AbstractDomainClass implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Version
    private int version;

    private Date dateCreated;

    private Date lastUpdated;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated == null) {
            dateCreated = new Date();
        }
    }
}
