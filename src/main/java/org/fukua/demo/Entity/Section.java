package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.Nullable;

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

    @Size(max=2000)
    @NotNull(message = "Section name is required.")
    private String name;

    @ManyToOne
    @Nullable
    @JsonIgnore
    @JoinColumn(name = "job_id")
    private Job job;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="section_id")
    private List<GeologicalClass> geologicalClassList = new ArrayList<>();

    public List<GeologicalClass> getGeologicalClassList() {
        return geologicalClassList;
    }

    public void setGeologicalClassList(List<GeologicalClass> geologicalClassList) {
        this.geologicalClassList = geologicalClassList;
    }

    public long getId() {
        return id;
    }

    public Section(){}

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
