package org.fukua.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class GeologicalClass{
    public GeologicalClass() {}

    public GeologicalClass(@Size(max = 1000) @NotNull String name, @Size(max = 1000) @NotNull String code) {
        this.name = name;
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name of GeologicalClass object is required field.")
    @Size(max=1000)
    @NotNull
    private String name;

    @NotBlank(message = "Code of GeologicalClass object is required field.")
    @Size(max=1000)
    @NotNull
    private String code;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "section_id")
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

    public Section getSection() {
        return section;
    }
}
