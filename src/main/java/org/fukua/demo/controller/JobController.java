package org.fukua.demo.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.fukua.demo.Entity.Enum.JobStatus;
import org.fukua.demo.Entity.Job;
import org.fukua.demo.exception.NotXlsOrXlsxFileProvided;
import org.fukua.demo.exception.XlsFileIsOfInvalidFormat;
import org.fukua.demo.service.JobService;
import org.fukua.demo.service.SectionService;
import org.fukua.demo.service.UserService;
import org.fukua.demo.service.XlsFileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<Map> loadXlsFile(@RequestParam("file") MultipartFile file) throws IOException{
        Map<String, String> response = new HashMap<>();

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Job job = jobService.createJob();
            response.put("job_id", Long.toString(job.getId()));

            if (!parsingService.validate(workbook)) {
                job.setStatus(JobStatus.FAILED);
                jobService.save(job);

                throw  new XlsFileIsOfInvalidFormat();
            }

            parsingService.storeData(workbook, job);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

        } catch (InvalidFormatException | org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            Job job = jobService.createJob();
            job.setStatus(JobStatus.FAILED);
            jobService.save(job);

            throw new NotXlsOrXlsxFileProvided();
        }
    }
}
