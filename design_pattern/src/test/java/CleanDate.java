import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-07-09 14:12
 **/
public class CleanDate {
    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\HP\\Desktop\\业主数据.xls");
        List<Map<String, Object>> readAll = reader.readAll();
        final ArrayList<Map<String, Object>> rows = new ArrayList<>();

        readAll.forEach(i -> {
            try {
                getprenumbers(i.get("业主手机号")).forEach(num -> {
                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("分期", i.get("分期"));
                    row.put("楼座名", i.get("楼座名") +String.valueOf(i.get("单元名")));
                    row.put("房号", i.get("房号"));
                    row.put("业主姓名", i.get("业主姓名"));
//                    row.put("证件类型", i.get("证件类型"));
                    row.put("身份证号码", getIdCard(i.get("证件号码")));
                    row.put("业主手机号", num);
//                    row.put("实际居住地址", i.get("实际居住地址"));
//                    row.put("业主类型（1=业主，2=租客，3=住客，不填默认值1）", i.get("业主类型（1=业主，2=租客，3=住客，不填默认值1）"));
                    rows.add(row);
                });
            } catch (Exception e) {
                System.out.println(i.get("业主手机号"));
                e.printStackTrace();
            }
        });
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\HP\\Desktop\\干净数据规范.xls");
        writer.write(rows, true);
        writer.close();

    }

    public static List<String> getprenumbers(Object nums) {
        String nums2 = String.valueOf(nums);
        //获取纯数字
        StringBuilder sb = new StringBuilder();
        for (char c : nums2.trim().toCharArray()) {
            if (c >= 48 && c <= 57) {
                sb.append(c);
            }
        }
        List<String> numList = new ArrayList<>();
        while (sb.length() >= 11) {
            numList.add(sb.toString().substring(0, 11));
            sb.delete(0, 11);
        }
        return numList;
    }

    public  static String getIdCard(Object idcard) {
        String nums2 = String.valueOf(idcard);
        StringBuilder sb = new StringBuilder();
        for (char c : nums2.trim().toCharArray()) {
            if (c >= 48 && c <= 57) {
                sb.append(c);
            }
        }
        if (sb.length() < 10) {
            return null;
        }
        return sb.toString();
    }
}
