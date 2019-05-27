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

    @RequestMapping(value="{id}", method={RequestMethod.PUT}, produces = "application/json; charset=utf-8")
    public ResponseEntity<Object> updateSection(@PathVariable("id") long id, @RequestBody @Valid Section section) {
        Section updatedSection = sectionService.updateSection(id, section);

        return customJsonDataHelper.buildResponse(section);
    }

    @RequestMapping(value="{id}", method={RequestMethod.GET}, produces = "application/json; charset=utf-8")
    public ResponseEntity<Object> getSection(@PathVariable("id") long id) {
        Section section = sectionService.getById(id);

        return customJsonDataHelper.buildResponse(section);
    }

    @RequestMapping(value="{id}", method={RequestMethod.DELETE}, produces = "application/json; charset=utf-8")
    public ResponseEntity<Object> deleteSection(@PathVariable("id") long id) {
        sectionService.deleteSection(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Section with current id {" + id + "} was removed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="of-job/{id}", method={RequestMethod.GET}, produces = "application/json; charset=utf-8")
    public List<Section> getSectionsByJobId(@PathVariable("id") long id) {
        return sectionService.getSectionsByJobId(id);
    }

    /*
     * Json example {"name": "geological_class_name"}
     * Attribute "name" is required in Json request body else ParsingValueFromCustomJsonException will be thrown
     */
    @RequestMapping(value="by-class-name", method={RequestMethod.POST}, produces = "application/json; charset=utf-8")
    public List<Section> getSectionByGeologicalClassName(@RequestBody String nameJson){
        String name = customJsonDataHelper.parseSingleJsonParamAsString(nameJson, "name");

        return sectionService.getByGeologicalClassName(name);
    }

    /*
     * Json example {"code": "geological_class_code"}
     * Attribute "code" is required in Json request body else ParsingValueFromCustomJsonException will be thrown
     */
    @RequestMapping(value="by-class-code", method={RequestMethod.POST}, produces = "application/json; charset=utf-8")
    public List<Section> getSectionByGeologicalClassCode(@RequestBody String codeJson){
        String code = customJsonDataHelper.parseSingleJsonParamAsString(codeJson, "code");

       return  sectionService.getByGeologicalClassCode(code);
    }
}
