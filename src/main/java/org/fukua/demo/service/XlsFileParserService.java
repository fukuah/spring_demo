package org.fukua.demo.service;

import org.apache.poi.ss.usermodel.*;
import org.fukua.demo.Entity.Enum.JobStatus;
import org.fukua.demo.Entity.GeologicalClass;
import org.fukua.demo.Entity.Job;
import org.fukua.demo.Entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Configuration
@EnableAsync
public class XlsFileParserService implements XlsFileParserInterface {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private JobService jobService;

    @Async
    public void storeData(Workbook workbook, Job job) throws IOException {
        for (Sheet sheet : workbook) {
            Section section = null;
            for (Row row : sheet) {
                String rowSectionName = getCellValue(row, 0),
                        geologicalClassName = getCellValue(row, 1),
                        geologicalClassCode = getCellValue(row, 2);

                if (rowSectionName != null) {
                    if (section != null) {
                        sectionService.createSection(section);
                    }
                    section = new Section();
                    section.setJob(job);
                    section.setName(rowSectionName);
                }

                if (section != null) {
                    section.getGeologicalClassList().add(new GeologicalClass(geologicalClassName, geologicalClassCode));
                }
            }

            if (section != null) {
                sectionService.createSection(section);
            }
        }

        workbook.close();

        // Change status of a job after parsing is completed
        job.setStatus(JobStatus.COMPLETE);
        jobService.save(job);
    }

    public boolean validate(Workbook workbook) {
        for(Sheet sheet: workbook) {
            // This is safe code (someone could put beginning of a table not in A1 cell)
            String firstCellValue = getCellValue(sheet.getRow(0), 0);

            // Check for invalid format
            if (firstCellValue == null) {
                return false;
            }

            for (Row row: sheet) {
                int rowSize = row.getPhysicalNumberOfCells();
                if (rowSize != 2 && rowSize != 3) {
                    return false;
                }
            }
        }

        return true;
    }

    private String getCellValue(Row row, int i) {
        Cell cell = row.getCell(i);
        if (cell != null) {
            cell.setCellType(CellType.STRING);
            return cell.getStringCellValue();
        }

        return null;
    }
}
