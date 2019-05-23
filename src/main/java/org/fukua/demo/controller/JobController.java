package org.fukua.demo.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.fukua.demo.Entity.Job;
import org.fukua.demo.service.JobService;
import org.fukua.demo.service.SectionService;
import org.fukua.demo.service.UserService;
import org.fukua.demo.service.XlsFileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private XlsFileParserService parsingService;

    @RequestMapping(value="{username}", method={RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String loadXlsFile(@PathVariable("username") String username, @RequestParam("file") MultipartFile file) throws IOException {

        Job job = jobService.createJob(username);

        byte [] buffer = file.getBytes();

        String s = "File is loaded";
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            if (parsingService.validate(workbook)) {
                parsingService.storeData(workbook, job);
            } else {
                return "file is of invalid format";
            }

        } catch (InvalidFormatException | org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }

        return s;
    }
}
