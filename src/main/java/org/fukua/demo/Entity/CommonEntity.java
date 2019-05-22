package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class CommonEntity implements Serializable {
//    @JsonIgnore
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private java.sql.Timestamp createdAt = null;
//    @JsonIgnore
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private java.sql.Timestamp updatedAt = null;

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public java.sql.Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.sql.Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
