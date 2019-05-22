package org.fukua.demo.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Section  extends CommonEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Size(max=100)
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

}
