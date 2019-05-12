package excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

/**
 * Created by highness on 2017/9/28 0028.
 */
public class ExcelTest1 {

    public static void main(String[] args) {


//        create("D:/git/1.xlsx");
//        read("D:/git/1.xlsx");

//        field
        JSONArray titals = new JSONArray();
        JSONObject object = new JSONObject();
        object.put("field", "a");
        object.put("a", "字段一");
        titals.add(object);

        object = new JSONObject();
        object.put("field", "b");
        object.put("b", "字段二");
        titals.add(object);

        object = new JSONObject();
        object.put("field", "c");
        object.put("c", "字段三");
        titals.add(object);

        JSONArray data = new JSONArray();
        for(int i=1; i<=10; i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("a", "哈" + i);
            jsonObject.put("b", "呵" + i);
            jsonObject.put("c", "嘿" + i);
            data.add(jsonObject);
        }



        createExcel(new File("D:/git/1.xlsx"), titals, data, 0);
        createExcel(new File("D:/git/1.xlsx"), titals, data, data.size());

    }

    public static void createExcel(File file, JSONArray titals, JSONArray data, int num){
        try {
            if (num == 0){
                //创建一个新的Excel
                Workbook wb = new SXSSFWorkbook();
                //创建sheet页
                Sheet sheet = wb.createSheet(); // wb.createSheet("sheet1");

                //设置第一行为Header
                Row row = sheet.createRow(0);
                ArrayList<String> fields = Lists.newArrayList();
                for (int i=0; i<titals.size(); i++){
                    JSONObject jsonObject = titals.getJSONObject(i);
                    String field = jsonObject.getString("field");
                    String text = jsonObject.getString(field);
                    Cell cell = row.createCell(i);
                    cell.setCellValue(text);
                    fields.add(field);
                }
//1048575
                for(int i=0;i<data.size();i++){
                    JSONObject jsonObject = data.getJSONObject(i);

                    row = sheet.createRow(i + 1);

                    for(int j=0; j<fields.size(); j++){
                        Cell cell = row.createCell(j);
                        cell.setCellValue(jsonObject.getString(fields.get(j)));
                        sheet.setColumnWidth(j, 5000);
                    }
                }

                try {
                    FileOutputStream out = new FileOutputStream(file);
                    wb.write(out);
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                FileInputStream is = new FileInputStream(file);  //读取的文件路径
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new BufferedInputStream(is));
                Workbook wb = new SXSSFWorkbook(xssfWorkbook);
                is.close();

                ArrayList<String> fields = Lists.newArrayList();
                for (int i=0; i<titals.size(); i++){
                    JSONObject jsonObject = titals.getJSONObject(i);
                    String field = jsonObject.getString("field");
                    fields.add(field);
                }

                Sheet sheet = wb.getSheetAt(0);
                for(int i=0;i<data.size();i++){
                    JSONObject jsonObject = data.getJSONObject(i);
                    //得到Excel工作表的行
                    Row row = sheet.createRow(num + i + 1);
                    for(int j=0;j<fields.size();j++){
                        Cell cell = row.createCell(j);
                        cell.setCellValue(jsonObject.getString(fields.get(j)));
                        sheet.setColumnWidth(j, 5000);
                    }
                }

                //将修改后的数据保存
                OutputStream out = new FileOutputStream(file);
                wb.write(out);
                out.close();

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void read(String fileName){
        try {
            InputStream is = new FileInputStream(new File(fileName));
            Workbook wb = WorkbookFactory.create(is);
            is.close();
//得到Excel工作表对象
            Sheet sheet = wb.getSheetAt(0);
            int sheet_numbers = wb.getNumberOfSheets();//获取表的总数
            System.out.println(sheet_numbers);
//总行数
            int trLength = sheet.getLastRowNum();
            System.out.println("总行数: " + trLength);
            //4.得到Excel工作表的行
            Row row = sheet.getRow(0);
            //总列数
            int tdLength = row.getLastCellNum();
            System.out.println("总列数: " + tdLength);

            for(int i=0;i<trLength;i++){
                //得到Excel工作表的行
                Row row1 = sheet.getRow(i);
                for(int j=0;j<tdLength;j++){
                    //得到Excel工作表指定行的单元格
                    Cell cell1 = row1.getCell(j);
                    /**
                     * 为了处理：Excel异常Cannot get a text value from a numeric cell
                     * 将所有列中的内容都设置成String类型格式
                     */
                    if(cell1!=null){
                        System.out.println(cell1.getStringCellValue());
                    }
                    //获得每一列中的值
                    System.out.print(cell1+"                   ");
                }
                System.out.println();
            }

            //将修改后的数据保存
            OutputStream out = new FileOutputStream(new File(fileName));
            wb.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(String fileName) {
//创建一个新的Excel
        Workbook wb = new SXSSFWorkbook();
//创建sheet页
        Sheet sheet1 = wb.createSheet("sheet1");

        //设置第一行为Header
        Row row = sheet1.createRow(0);
        Cell cell0 = row.createCell(0);
        Cell cell1 = row.createCell(1);
        Cell cell2 = row.createCell(2);

        cell0.setCellValue("序号");
        cell1.setCellValue("名字");
        cell2.setCellValue("年龄");
//1048575
        for(int i=0;i<5;i++){
            row = sheet1.createRow(i * 2 + 1);
            cell0 = row.createCell(0);
            cell1 = row.createCell(1);
            cell2 = row.createCell(2);

            cell0.setCellValue(i + 1);
            cell1.setCellValue(UUID.randomUUID().toString());
            cell2.setCellValue(new Random().nextInt());

            sheet1.setColumnWidth(0, 4000);
            sheet1.setColumnWidth(1, 4000);
            sheet1.setColumnWidth(2, 4000);
        }

        try {
            FileOutputStream out = new FileOutputStream(new File(fileName));
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
