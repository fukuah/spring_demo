package org.fukua.demo.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section  extends CommonEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Size(max=100)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @OneToMany
    private List<GeologicalClass> geologicalClassList = new ArrayList<>();

    public List<GeologicalClass> getGeologicalClassList() {
        return geologicalClassList;
    }

    public void setGeologicalClassList(List<GeologicalClass> geologicalClassList) {
        this.geologicalClassList = geologicalClassList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}
