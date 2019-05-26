package org.fukua.demo.Entity;


import org.fukua.demo.Entity.Enum.JobStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends CommonEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;


//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Users user;

    @OneToMany
    private List<Section> sections = new ArrayList<>();

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

//    public Users getUser() {
//        return user;
//    }
//
//    public void setUser(Users user) {
//        this.user = user;
//    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    private JobStatus status = JobStatus.PROCESSING;

    public long getId() {
        return id;
    }
}
