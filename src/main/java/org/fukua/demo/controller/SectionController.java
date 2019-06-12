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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("section")
public class SectionController {

    @Autowired
    private SectionService sectionService;
    @Autowired
    private CustomJsonDataHelper customJsonDataHelper;

    @PostMapping
    public Section createSection(@RequestBody @Valid Section section) {
        return sectionService.createSection(section);
    }

    @RequestMapping(value="{id}", method={RequestMethod.PUT}, produces = "application/json")
    public ResponseEntity<Section> updateSection(@PathVariable("id") long id, @RequestBody @Valid Section section) {
        Section updatedSection = sectionService.updateSection(id, section);

        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value="{id}", method={RequestMethod.GET}, produces = "application/json")
    public ResponseEntity<Section> getSection(@PathVariable("id") long id) {
        Section section = sectionService.getById(id);

        return new ResponseEntity<>(section, HttpStatus.OK);
    }

    @RequestMapping(value="{id}", method={RequestMethod.DELETE}, produces = "application/json")
    public ResponseEntity<Object> deleteSection(@PathVariable("id") long id) {
        sectionService.deleteSection(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Section with current id {" + id + "} was removed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="of-job/{id}", method={RequestMethod.GET}, produces = "application/json")
    public List<Section> getSectionsByJobId(@PathVariable("id") long id) {
        return sectionService.getSectionsByJobId(id);
    }

    @RequestMapping(value="by-class-name/{name}", method={RequestMethod.GET}, produces = "application/json")
    public List<Section> getSectionByGeologicalClassName(@PathVariable("name") String name){
        return sectionService.getByGeologicalClassName(name);
    }

    @RequestMapping(value="by-class-code/{code}", method={RequestMethod.GET}, produces = "application/json")
    public List<Section> getSectionByGeologicalClassCode(@PathVariable("code") String code){
       return  sectionService.getByGeologicalClassCode(code);
    }
}
