package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@MappedSuperclass
public abstract class CommonEntity implements Serializable {
    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private java.sql.Timestamp createdAt = null;
    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private java.sql.Timestamp updatedAt = null;

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    private void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }


    @PreUpdate
    public void beforeUpdate() {
        Date date= new Date();

        long time = date.getTime();
        this.setUpdatedAt(new Timestamp(time));
        System.out.println("postUpdate" + this.toString());
    }

    @PrePersist
    public void beforeCreate() {
        Date date= new Date();

        long time = date.getTime();
        this.setCreatedAt(new Timestamp(time));
        System.out.println("postCreate" + this.toString());
    }
}
