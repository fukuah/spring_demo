package org.fukua.demo.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.fukua.demo.service.JobService;
import org.fukua.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import java.io.*;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value="{username}", method={RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String loadXlsFile(@PathVariable("username") String username, @RequestParam("file") MultipartFile file) throws IOException {
//        File convertFile = new File("/tmp/xls/" + file.getOriginalFilename());

        byte [] buffer = file.getBytes();


//        File targetFile  = new File("tmp/xls/" + file.getOriginalFilename());
//        OutputStream outStream = new FileOutputStream(targetFile);
//        outStream.write(buffer);
//        outStream.close();


        String s = "";
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            for(Sheet sheet: workbook) {
                s += "=> " + sheet.getSheetName();
            }

        } catch (InvalidFormatException | org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
            e.printStackTrace();
        }

//        return servletContext.getResourcePaths("/tmp/xls/");
//        if (convertFile.createNewFile()) {
//            FileOutputStream fout = new FileOutputStream(convertFile);
//            fout.write(file.getBytes());
//            fout.close();
//        }
//
//        Job job = new Job();
//        User user = userService.getUserByLogin(username);
//        if (user != null) {
//            job.setUser(user);
//            return jobService.createJob(job);
//        }

        return s;
    }
}
