package com.custom.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Excel 导入
 * created by fuyd on 2019-09-18
 */
public class ExcelImport {

    private static final int DEFAULT_SHEET = 0;
    private static final int DEFAULT_ROW = 0;

    public static <T> List<T> execute(MultipartFile file, T cls) {

        if (file.isEmpty()) {
            return null;
        }
        Workbook book = null;
        try {
            book = Workbook.getWorkbook(file.getInputStream());
        } catch (IOException | BiffException e) {
            e.printStackTrace();
            return null;
        }
        Sheet sheet = book.getSheet(DEFAULT_SHEET);

        Field[] fields = cls.getClass().getDeclaredFields();
        int rows = sheet.getRows();
        if (rows != fields.length) {
            return null;
        }
        Stream.iterate(0, i -> i + 1)
                .limit(rows)
                .forEach(i -> {
                    Cell[] cells = sheet.getRow(i);
                    Arrays.stream(cells).forEach((cell) -> {
                        String contents = cell.getContents();
                        int column = cell.getColumn();
                        Field field = fields[column];
                        field.setAccessible(true);
                        try {
                            field.set(cls, contents);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } finally {
                            field.setAccessible(false);
                        }
                    });
                });
        return null;
    }
}
