package org.fukua.demo.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.fukua.demo.Entity.GeologicalClass;
import org.fukua.demo.Entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
@EnableAsync
public class XlsFileParserService {

    @Autowired
    private SectionService sectionService;

    final private int cellsInRowWithClasses = 2;
    final private int cellsInRow2 = 3;

    @Async
    public void storeData(Workbook workbook, Job job) throws IOException {
        for (Sheet sheet : workbook) {

            String sectionName = null;
            List<GeologicalClass> geologicalClassList = null;
            for (Row row : sheet) {
                // Parse data from xls document to database
                if (isSectionDefinitionRow(row)) {
                    if (sectionName != null) {
                        sectionService.createSection(job, sectionName, geologicalClassList);
                    }
                    sectionName = row.getCell(0).getStringCellValue();
                    geologicalClassList = new ArrayList<>();
                    String geologicalClassName = row.getCell(1).getStringCellValue(),
                            geologicalClassCode = row.getCell(1).getStringCellValue();

                    geologicalClassList.add(new GeologicalClass(geologicalClassName, geologicalClassCode));

                } else if (isGeologicalClassRow(row)) {
                    String geologicalClassName = row.getCell(1).getStringCellValue(),
                            geologicalClassCode = row.getCell(1).getStringCellValue();

                    if (geologicalClassList != null) {
                        geologicalClassList.add(new GeologicalClass(geologicalClassName, geologicalClassCode));
                    }
                }
            }
        }
        workbook.close();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean validate(Workbook workbook) {
        for(Sheet sheet: workbook) {
            // This is safe code (someone could put beginning of a table not in A1 cell)
            Cell firstCell = sheet.getRow(0)
                    .getCell(0);

            // Check for invalid format
            if (firstCell == null) {
                return false;
            }
            for (Row row: sheet) {
                if (!isSectionDefinitionRow(row) && !isGeologicalClassRow(row)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isSectionDefinitionRow(Row row) {
        return row.getPhysicalNumberOfCells() == 3;
    }

    private boolean isGeologicalClassRow(Row row) {
        return row.getPhysicalNumberOfCells() == 2;
    }
}
