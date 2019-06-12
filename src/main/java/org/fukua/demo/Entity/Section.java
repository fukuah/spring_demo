package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.fukua.demo.exception.GeologicalClassCodeIsNotSetException;
import org.fukua.demo.exception.GeologicalClassNameIsNotSetException;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonInclude
public class Section  extends CommonEntity{
    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name of Section object is required field.")
    @Size(max=2000)
    @NotNull
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

    public void setGeologicalClassList(List<GeologicalClass> geologicalClassList) { this.geologicalClassList = geologicalClassList; }

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

    // Validates geologicalClasses before create or update
    // Also set link from geologicalClasses item to section
    // [NOTICE]: there must be a better way to do that
    @PrePersist
    @PreUpdate
    public void validateGeologicalClasses() {
        for (GeologicalClass item: geologicalClassList) {
            if (item.getName() == null) {
                throw new GeologicalClassNameIsNotSetException();
            }
            if (item.getCode() == null) {
                throw new GeologicalClassCodeIsNotSetException();
            }
            // [NOTICE]: as @JsonManagedReference was added we need to set section manually
            item.setSection(this);
        }
    }
}
