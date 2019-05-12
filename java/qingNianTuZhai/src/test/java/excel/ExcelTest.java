package excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by highness on 2017/9/28 0028.
pom添加依赖
 <dependency>
     <groupId>org.apache.poi</groupId>
     <artifactId>poi</artifactId>
     <version>3.17</version>
 </dependency>
 <dependency>
     <groupId>org.apache.poi</groupId>
     <artifactId>poi-ooxml</artifactId>
     <version>3.17</version>
 </dependency>

 */
public class ExcelTest {

    static String key = "field";

    public static void main(String[] args) {


        JSONArray titles = new JSONArray();
        JSONObject object = new JSONObject();
        object.put(key, "a");
        object.put("a", "字段一");
        titles.add(object);

        object = new JSONObject();
        object.put(key, "b");
        object.put("b", "字段二");
        titles.add(object);

        object = new JSONObject();
        object.put(key, "c");
        object.put("c", "字段三");
        titles.add(object);

        JSONArray data = new JSONArray();
        for (int i = 1; i <= 10; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("a", "哈" + i);
            jsonObject.put("b", "呵" + i);
            jsonObject.put("c", "嘿" + i);
            data.add(jsonObject);
        }


        createExcel(new File("D:/git/2.xlsx"), titles, data, 0);
        createExcel(new File("D:/git/2.xlsx"), titles, data, data.size());
        createExcel(new File("D:/git/2.xlsx"), titles, data, data.size() * 2);
        createExcel(new File("D:/git/2.xlsx"), titles, data, data.size() * 3);

    }

    public static void createExcel(File file, JSONArray titles, JSONArray data, int num) {
        try {
            Workbook wb = null;
            Sheet sheet = null;
            ArrayList<String> fields = Lists.newArrayList();

            if (num == 0) {
                //创建一个新的Excel
                wb = new SXSSFWorkbook();
                //创建sheet页
                sheet = wb.createSheet(); // wb.createSheet("sheet1");

                //设置第一行为Header
                Row row = sheet.createRow(0);
                for (int i = 0; i < titles.size(); i++) {
                    JSONObject jsonObject = titles.getJSONObject(i);
                    String field = jsonObject.getString(key);
                    String text = jsonObject.getString(field);
                    Cell cell = row.createCell(i);
                    cell.setCellValue(text);
                    fields.add(field);
                }
            } else {
                FileInputStream is = new FileInputStream(file);  //读取的文件路径
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new BufferedInputStream(is));
                wb = new SXSSFWorkbook(xssfWorkbook);
                is.close();

                for (int i = 0; i < titles.size(); i++) {
                    JSONObject jsonObject = titles.getJSONObject(i);
                    String field = jsonObject.getString(key);
                    fields.add(field);
                }
                sheet = wb.getSheetAt(0);
            }
//1048575
            for (int i = 0; i < data.size(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                //得到Excel工作表的行
                Row row = sheet.createRow(num + i + 1);
                for (int j = 0; j < fields.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(jsonObject.getString(fields.get(j)));
                    sheet.setColumnWidth(j, 10000);
                }
            }
            //保存
            OutputStream out = new FileOutputStream(file);
            wb.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
