package org.fukua.demo.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class geologicalClass extends CommonEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private long sectionId;
    @Size(max=100)
    @NotNull
    private String name;
    @Size(max=100)
    @NotNull
    private String code;
}
