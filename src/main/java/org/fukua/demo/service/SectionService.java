package org.fukua.demo.service;

import org.fukua.demo.Entity.GeologicalClass;
import org.fukua.demo.Entity.Job;
import org.fukua.demo.Entity.Section;
import org.fukua.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;

    public void createSection(Job job, String name, List<GeologicalClass> geologicalClassList) {
        Section section = new Section();
        section.setJob(job);
        section.setName(name);
        section.setGeologicalClassList(geologicalClassList);

        sectionRepository.saveAndFlush(section);
    }
}
