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
    @Autowired
    private GeologicalClassService geologicalClassService;

    public void createSection(Job job, String name, List<GeologicalClass> geologicalClassList) {
        Section section = new Section();
        section.setJob(job);
        section.setName(name);
        section.setGeologicalClassList(geologicalClassList);

        sectionRepository.save(section);
    }

    public Section createSection(Section section){
        return sectionRepository.save(section);
    }

    public Section updateSection(long id, Section section) {
        Section sectionToUpdate = sectionRepository.getById(id);
        sectionToUpdate.setName(section.getName());
        sectionToUpdate.setGeologicalClassList(section.getGeologicalClassList());

        sectionRepository.save(section);
        return sectionRepository.save(section);
    }

    public Section getById(long id) {
        return sectionRepository.getById(id);
    }

    public List<Section> getSectionsByJobId(long id) {
        return sectionRepository.getByJobId(id);
    }

    public void deleteSection(long id) {
        sectionRepository.deleteById(id);
    }
}
