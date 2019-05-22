package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class CommonEntity implements Serializable {
    @JsonIgnore
    private DateTime createdAt = null;
    @JsonIgnore
    private DateTime updatedAt = null;

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
