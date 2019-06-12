package org.fukua.demo.service;

import org.fukua.demo.Entity.Section;
import org.fukua.demo.exception.SectionIsNotFoundException;
import org.fukua.demo.repository.GeologicalClassRepository;
import org.fukua.demo.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

@Service
public class SectionService implements Validator{
    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private GeologicalClassRepository geologicalClassRepository;
    @Autowired
    private GeologicalClassService geologicalClassService;

    public Section createSection(Section section){
        return sectionRepository.save(section);
    }

    public Section updateSection(long id, Section section) {
        Section sectionToUpdate = sectionRepository.getById(id);
        if (sectionToUpdate == null) {
            throw new SectionIsNotFoundException();
        }
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
        if (sectionRepository.getById(id) == null) {
            throw new SectionIsNotFoundException();
        }
        sectionRepository.deleteById(id);
    }

    public List<Section> getByGeologicalClassName(String name) {
        return sectionRepository.getByGeologicalClassName(name);
    }

    public List<Section> getByGeologicalClassCode(String code) {
        return sectionRepository.getByGeologicalClassCode(code);
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
