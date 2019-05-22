package org.fukua.demo.Entity;

import org.joda.time.DateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class EntityListener {
    @PreUpdate
    public void beforeUpdate(CommonEntity o) {
        o.setUpdatedAt(DateTime.now());
        System.out.println("postUpdate" + o.toString());
    }

    @PrePersist
    public void beforeCreate(CommonEntity o) {
        o.setCreatedAt(DateTime.now());
        System.out.println("postUpdate" + o.toString());
    }
}
