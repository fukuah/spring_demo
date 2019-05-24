package org.fukua.demo.service;

import org.fukua.demo.Entity.GeologicalClass;
import org.fukua.demo.repository.GeologicalClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class GeologicalClassService implements Validator {
    @Autowired
    private GeologicalClassRepository geologicalClassRepositoryRepository;

    public void createGeologicalClassNode(GeologicalClass node) {
        geologicalClassRepositoryRepository.save(node);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GeologicalClass.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "GeologicalClass.name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "GeologicalClass.code.required");
    }
}
