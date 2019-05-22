package org.fukua.demo.Entity;

import org.fukua.demo.Entity.Enum.JobStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends CommonEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Section> sections = new ArrayList<Section>();

    @OneToOne
    private User user;

    private JobStatus status = JobStatus.PROCESSING;
}
