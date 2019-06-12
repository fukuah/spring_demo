package org.fukua.demo.controller;

import org.fukua.demo.Entity.Section;
import org.fukua.demo.helper.CustomJsonDataHelper;
import org.fukua.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("section")
public class SectionController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private CustomJsonDataHelper customJsonDataHelper;

    @PostMapping
    public ResponseEntity createSection(@RequestBody @Valid Section section)
    {
        return new ResponseEntity<>(sectionService.createSection(section), HttpStatus.OK);
    }

    @RequestMapping(value="{id}", method={RequestMethod.PUT}, produces = "application/json")
    public ResponseEntity updateSection(@PathVariable("id") long id, @RequestBody @Valid Section section) {
        Section updatedSection = sectionService.updateSection(id, section);

        return customJsonDataHelper.buildResponse(updatedSection);
    }

    @ResponseBody
    @RequestMapping(value="{id}", method={RequestMethod.GET}, produces = "application/json")
    public ResponseEntity getSection(@PathVariable("id") long id) {
        Section section = sectionService.getById(id);

        return customJsonDataHelper.buildResponse(section);
    }

    @RequestMapping(value="{id}", method={RequestMethod.DELETE}, produces = "application/json")
    public ResponseEntity deleteSection(@PathVariable("id") long id) {
        sectionService.deleteSection(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Section with current id {" + id + "} was removed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="of-job/{id}", method={RequestMethod.GET}, produces = "application/json")
    public ResponseEntity getSectionsByJobId(@PathVariable("id") long id) {
        return new ResponseEntity<>(sectionService.getSectionsByJobId(id), HttpStatus.OK);
    }

    @RequestMapping(value="by-class-name/{name}", method={RequestMethod.GET}, produces = "application/json")
    public ResponseEntity getSectionByGeologicalClassName(@PathVariable("name") String name){
        return new ResponseEntity<>(sectionService.getByGeologicalClassName(name), HttpStatus.OK);
    }

    @RequestMapping(value="by-class-code/{code}", method={RequestMethod.GET}, produces = "application/json")
    public ResponseEntity getSectionByGeologicalClassCode(@PathVariable("code") String code){
       return new ResponseEntity<>(sectionService.getByGeologicalClassCode(code), HttpStatus.OK);
    }
}
