package com.qquique.jsbm.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class Base {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_date_created")
    private Date lastDateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_date_modified")
    private Date lastDateModified;

    @PrePersist
    public void prePersist() {
        this.lastDateCreated = new Date();
        this.lastDateModified = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastDateModified = new Date();
    }

}
