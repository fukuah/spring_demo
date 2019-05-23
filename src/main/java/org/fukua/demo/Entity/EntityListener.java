package org.fukua.demo.Entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.util.Date;

public class EntityListener {
    @PreUpdate
    public void beforeUpdate(CommonEntity o) {
        Date date= new Date();

        long time = date.getTime();
        o.setUpdatedAt(new Timestamp(time));
        System.out.println("postUpdate" + o.toString());
    }

    @PrePersist
    public void beforeCreate(CommonEntity o) {
        Date date= new Date();

        long time = date.getTime();
        o.setCreatedAt(new Timestamp(time));
        System.out.println("postUpdate" + o.toString());
    }
}
