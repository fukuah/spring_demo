package org.fukua.demo.controller;

import org.fukua.demo.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("xls-section")
public class SectionController {
    @Autowired
    private SectionService sectionService;


}
