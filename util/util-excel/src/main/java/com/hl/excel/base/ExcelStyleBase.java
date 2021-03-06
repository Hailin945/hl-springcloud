package com.hl.excel.base;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;
import java.util.Set;

/**
 * @author Hailin
 */
public abstract class ExcelStyleBase implements StyleInterface {

    /**
     * 行高
     */
    private short rowHigh = 0;
    /**
     * 列宽
     */
    private short columnWidth = 0;

    @Override
    public abstract CellStyle setHeaderStyle(Workbook wb);

    @Override
    public abstract CellStyle setDataStyle(Workbook wb);

    @Override
    public abstract void setRowHigh();

    @Override
    public abstract void setColumnWidth();

    public abstract Map<Integer, Integer> setMySpecifiedHighAndWidth();

    //可利用此方法设定特定的列宽与行高--模板模式
    @Override
    public void setSpecifiedHighAndWidth(Sheet sheet) {
        Map<Integer, Integer> map = this.setMySpecifiedHighAndWidth();
        if (map != null) {
            Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
            for (Map.Entry<Integer, Integer> entry : entrySet) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                sheet.setColumnWidth(key, value);
            }
        }
    }

    @Override
    public short getRowHigh() {
        return rowHigh;
    }

    @Override
    public short getColumnWidth() {
        return columnWidth;
    }

    protected void setMyRowHigh(short high) {
        rowHigh = high;
    }

    protected void setMyColumnWidth(short width) {
        columnWidth = width;
    }
}
