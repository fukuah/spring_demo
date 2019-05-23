package org.fukua.demo.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.fukua.demo.service.JobService;
import org.fukua.demo.service.SectionService;
import org.fukua.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import java.io.*;
import java.security.Provider;
import java.util.Iterator;

@RestController
@RequestMapping("job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private SectionService sectionService;

    @RequestMapping(value="{username}", method={RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String loadXlsFile(@PathVariable("username") String username, @RequestParam("file") MultipartFile file) throws IOException {


        byte [] buffer = file.getBytes();

        String s = "";
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            for(Sheet sheet: workbook) {
                // This is safe code (someone could put beginning of a table not in A1 cell)
                Cell firstCell = sheet.getRow(0)
                        .getCell(0);

                // Check for invalid format
                if (firstCell == null) {
                    return "file is of invalid format.";
                }

                for (Row row: sheet) {
                    String sectionName = null;
                    // Check for invalid format
                    if (row.getPhysicalNumberOfCells() != 3 || row.getPhysicalNumberOfCells() != 2 ) {
                        return "file is of invalid format.";
                    }
                    // Parse data from xls document to database
//                    if (row.getPhysicalNumberOfCells() == 3) {
//                        if (sectionName != null) {
//
//                        }
//
//                        sectionName = row.getCell(0).getStringCellValue();
//
//                        for (Cell cell: row) {
//
//                        }
//                    } else if (row.getPhysicalNumberOfCells() == 2) {
//
//                    }
                }

            }
            workbook.close();
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
