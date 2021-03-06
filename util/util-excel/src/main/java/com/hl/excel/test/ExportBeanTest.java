package com.hl.excel.test;

import com.hl.excel.base.AbstractPoiExcel;
import com.hl.excel.base.PoiInterface;
import com.hl.excel.base.StyleInterface;
import com.hl.excel.model.StudentModelExcelTest;
import com.hl.excel.styleImpl.TestStyle;
import com.hl.excel.util.PoiBeanFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实体导入Demo
 *
 * @author Hailin
 */
public class ExportBeanTest {
    public static void main(String[] args) throws IOException {
        // 初始化Excel表头名称
        List<String> excelHeaderNameList = initExcelHeaderNames();
        List<String> listId = new ArrayList<>(16);
        listId.add("id");
        listId.add("sex");
        listId.add("name");
        List<StudentModelExcelTest> list = new ArrayList<>(16);
        list.add(new StudentModelExcelTest(111, "张三", "男"));
        list.add(new StudentModelExcelTest(111, "李四", "男"));
        list.add(new StudentModelExcelTest(111, "王五", "女"));

        FileOutputStream fileOutputStream = null;
        if (AbstractPoiExcel.EXCEL_VERSION_07 == 0) {
            fileOutputStream = new FileOutputStream("/Users/mac/Desktop/abc.xls");
        } else {
            fileOutputStream = new FileOutputStream("/Users/mac/Desktop/abc.xls");
        }
        // （一）去工厂拿导出工具
        PoiInterface<StudentModelExcelTest> poiInterface =
                PoiBeanFactory.getInstance().getPoiUtil(AbstractPoiExcel.EXPORT_SIMPLE_EXCEL);
        // （二）自定义样式（可无）
        StyleInterface myStyle = new TestStyle();
        // （三）根据需求选择接口方法（返回码：1是成功，0为失败）
        /// 导出默认样式EXCEL文件（根据headersId来导出对应字段，）--根据headersId筛选要导出的字段
        // int flag = poiInterface.exportBeanExcel(
        //         AbstractPoiExcel.EXCEL_VERSION_07,"测试POI导出EXCEL文档",excelHeaderNameList,listId,list,
        //         fileOutputStream);

        // 导出自定义样式Excel文件--根据headersId筛选要导出的字段
        int flag = poiInterface.exportStyleBeanExcel(
                AbstractPoiExcel.EXCEL_VERSION_07, "测试POI导出EXCEL文档", excelHeaderNameList, listId, list,
                fileOutputStream, myStyle);
        /// 默认导出dtolist的所有数据--默认导出dtolist的所有数据
        // int flag = poiInterface.exportStyleBeanExcel(PoiExcelBase.EXCEL_VERSION_07,"测试POI导出EXCEL文档",listName,list,exportXls,myStyle);
        System.out.println("flag  : " + flag);
        fileOutputStream.close();
    }

    private static List<String> initExcelHeaderNames() {
        final List<String> excelHeaderNames = new ArrayList<>(16);
        excelHeaderNames.add("id");
        excelHeaderNames.add("名字");
        excelHeaderNames.add("性别");
        return excelHeaderNames;
    }
}
