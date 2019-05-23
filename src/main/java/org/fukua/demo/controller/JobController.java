package org.fukua.demo.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.fukua.demo.Entity.Enum.JobStatus;
import org.fukua.demo.Entity.Job;
import org.fukua.demo.service.JobService;
import org.fukua.demo.service.SectionService;
import org.fukua.demo.service.UserService;
import org.fukua.demo.service.XlsFileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("register-job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private XlsFileParserService parsingService;

    @RequestMapping(value="{username}", method={RequestMethod.PUT}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json; charset=utf-8")
    public ResponseEntity<Map> loadXlsFile(@PathVariable("username") String username, @RequestParam("file") MultipartFile file) throws IOException{
        Map<String, String> response = new HashMap<>();

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Job job = jobService.createJob(username);
            response.put("job_id", Long.toString(job.getId()));

            if (!parsingService.validate(workbook)) {
                job.setStatus(JobStatus.FAILED);
                jobService.save(job);

                response.put("error", "file is of invalid format.");
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            }

            parsingService.storeData(workbook, job);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        } catch (InvalidFormatException | org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            Job job = jobService.createJob(username);
            job.setStatus(JobStatus.FAILED);
            jobService.save(job);
            response.put("job_id", Long.toString(job.getId()));
            response.put("error", "file is not of xls or xlsx type.");
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
