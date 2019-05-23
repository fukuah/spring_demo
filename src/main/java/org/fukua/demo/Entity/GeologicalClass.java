package org.fukua.demo.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GeologicalClass{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Size(max=100)
    @NotNull
    private String name;

    @ManyToOne
    private Section section;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Size(max=100)
    @NotNull
    private String code;
}
