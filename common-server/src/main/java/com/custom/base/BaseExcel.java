package com.custom.base;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.custom.common.ExcelRt;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Excel抽象类
 * created by fuyd on 2019-07-29
 */
public abstract class BaseExcel<T> {

    private static final int FIRST_SHEET = 0;
    private static final int FIRST_ROW = 0;

    private static final int MAX_LINE = 50;
    private static final int MAX_ROW = 2000;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(BaseExcel.class);

    /**
     * 解析Excel表单
     *
     * @param inputStream 输入流
     * @param cls         映射实体
     * @return 实体数组
     */
    protected ExcelRt<List<T>> dealWith(InputStream inputStream, Object cls) {
        XSSFWorkbook workbook;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            BaseExcel.LOG.error("[BaseExcel] err:{}", e.getMessage());
            return ExcelRt.err(e.getMessage());
        }
        XSSFSheet firstSheet = workbook.getSheetAt(FIRST_SHEET);
        int lastRowNum = firstSheet.getLastRowNum();
        if (MAX_ROW < lastRowNum) {
            String err = String.format("[BaseExcel] 不能超过:{%s}行, 当前总行数:{%s}", MAX_ROW, lastRowNum);
            BaseExcel.LOG.error(err);
            return ExcelRt.err(err);
        }
        int cellNum = firstSheet.getRow(FIRST_ROW).getPhysicalNumberOfCells();
        if (MAX_LINE < cellNum) {
            String err = String.format("[BaseExcel] 不能超过:{%s}列, 当前总列数:{%s}", MAX_ROW, cellNum);
            BaseExcel.LOG.error("[BaseExcel] 不能超过:{}列, 当前总列数:{}", MAX_ROW, cellNum);
            return ExcelRt.err(err);
        }
        Field[] declaredFields = cls.getClass().getDeclaredFields();
        if (cellNum != declaredFields.length) {
            String err = String.format("[BaseExcel] 传入模版列数:{%s} 于当前实体列数:{%s} 长度不一致!", cellNum, declaredFields.length);
            BaseExcel.LOG.error(err);
            return ExcelRt.err(err);
        }
        List<T> objects = Collections.synchronizedList(new ArrayList<>());
        List<String> fields = Collections.synchronizedList(new ArrayList<>());
        for (Row row : firstSheet) {
            Iterator<Cell> cellIterator = row.cellIterator();
            if (FIRST_ROW == row.getRowNum()) {
                String simpleName = cls.getClass().getSimpleName();
                cellIterator.forEachRemaining(e -> fields.add(this.fieldName(simpleName, e.getStringCellValue().trim())));
                continue;
            }
            for (; cellIterator.hasNext(); ) {
                Arrays.stream(declaredFields).forEach(field -> this.assignment(cellIterator.next(), field, cls));
                try {
                    objects.add(MAPPER.readValue(JSONObject.toJSONString(cls), this.getMessageType()));
                } catch (IOException e) {
                    BaseExcel.LOG.error("[BaseExcel] err:{}", e.getMessage());
                    return ExcelRt.err(e.getMessage());
                }
            }
        }
        return ExcelRt.ok(objects);
    }

    protected abstract String fieldName(String className, String code);

    private void assignment(Cell cell, Field field, Object testClass) {
        try {
            field.setAccessible(true);
            field.set(testClass, null == cell ? "" : cell.getStringCellValue());
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Class<T> getMessageType() {
        Class<T> messageType;
        TypeToken tt = TypeToken.of(this.getClass());
        TypeToken btt = tt.getSupertype(BaseExcel.class);
        messageType = (Class<T>) ((ParameterizedType) btt.getType()).getActualTypeArguments()[0];
        return messageType;
    }

}
