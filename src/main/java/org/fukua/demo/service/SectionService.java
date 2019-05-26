package org.fukua.demo.service;

import org.fukua.demo.Entity.GeologicalClass;
import org.fukua.demo.Entity.Job;
import org.fukua.demo.Entity.Section;
import org.fukua.demo.repository.GeologicalClassRepository;
import org.fukua.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService implements Validator{
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private GeologicalClassRepository geologicalClassRepository;
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

        return sectionRepository.save(sectionToUpdate);
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

    private List<Long> getSectionIdListFromGeologicalClassList(List<GeologicalClass> geologicalClassList){
        List<Long> sectionIds = new ArrayList<>();
        long id;
        for (GeologicalClass item: geologicalClassList) {
            id = item.getSection().getId();
            sectionIds.add(id);
        }

        return sectionIds;
    }

    public List<Section> getByGeologicalClassName(String name) {
        List<GeologicalClass> geologicalClassList = geologicalClassRepository.findSectionIdByName(name);
        List<Long> sectionIds = getSectionIdListFromGeologicalClassList(geologicalClassList);

        return sectionRepository.findAllById(sectionIds);
    }

    public List<Section> getByGeologicalClassCode(String code) {
        List<GeologicalClass> geologicalClassList = geologicalClassRepository.findSectionIdByCode(code);
        List<Long> sectionIds = getSectionIdListFromGeologicalClassList(geologicalClassList);

        return sectionRepository.findAllById(sectionIds);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Section.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Section.name.required");
    }
}
