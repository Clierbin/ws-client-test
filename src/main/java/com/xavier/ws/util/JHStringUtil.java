package com.xavier.ws.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangbin q243132465@163.com
 * @createDate 2020-04-20 14:34:07
 */
public class JHStringUtil {
    /**
     * 获取字符串数组,根据给定的开头结尾
     * @param resultList 结果数组
     * @param str 给定字符串
     * @param strStart 截取前缀
     * @param strEnd 截取后缀
     * @return
     */
    public static List<String> subString(List<String> resultList, String str, String strStart, String strEnd) throws Exception {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf((strStart));
        int strEndIndex = str.lastIndexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return new ArrayList<>();
//            throw new Exception("字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串");
        }
        if (strEndIndex < 0) {
            return new ArrayList<>();
//            throw new Exception("字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串");
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        Object[] objects = deleteSubString(result, "<item>");
        System.out.println("删除字串后："+objects[0]);
        System.out.println("删除了" +objects[1] + "次");
        // 如果没删除,-1  只有一条 就不拆分此字符串
        if ((Integer) objects[1]!=-1){
            String[] split = objects[0].toString().split("</item>");
            List<String> strings = Arrays.asList(split);
            return strings;
        }
        return Arrays.asList(result);
    }

    /**
     * @param str1 原字符串
     * @param str2 删除个字符串
     * @return 删除后的字符串(删除个数)
     */
    public static Object[] deleteSubString(String str1, String str2) {
        StringBuffer sb = new StringBuffer(str1);
        int delCount = 0;
        Object[] obj = new Object[2];

        while (true) {
            int index = sb.indexOf(str2);
            if(index == -1) {
                break;
            }
            sb.delete(index, index+str2.length());
            delCount++;

        }
        if(delCount!=0) {
            obj[0] = sb.toString();
            obj[1] = delCount;
        }else {
            //不存在返回-1
            obj[0] = "-1";
            obj[1] = -1;
        }

        return obj;
    }
}
