package org.fukua.demo.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.fukua.demo.Entity.Job;

import java.io.IOException;

public interface XlsFileParserInterface {

    boolean validate(Workbook workbook);

    void storeData(Workbook workbook, Job job) throws IOException;
}
