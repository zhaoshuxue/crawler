package excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.record.ArrayRecord;

import java.util.HashMap;

/**
 * Created by highness on 2017/9/28 0028.
 */
public class Jsonarraytest {

    public static void main(String[] args) {
        JSONArray array = new JSONArray();

        JSONObject object = new JSONObject();
        object.put("key","a");
        object.put("a","你好");

        array.add(object);

        object = new JSONObject();
        object.put("key","b");
        object.put("b","你好b");

        array.add(object);

        System.out.println(array.toJSONString());

        JSONObject jsonObject = array.getJSONObject(0);
        Object key = jsonObject.get("key");
        System.out.println(key);
        System.out.println(jsonObject.get(key));





    }
}
