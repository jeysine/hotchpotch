package com.jeysine.services.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jxyao
 * @date 2018-5-15
 */
public class StringUtil {

    /**
     * 字符串转数组
     * @param str  源
     * @return 转后的数组
     */
    public static List<Integer> stringToList(String str){
        if(StringUtil.isEmpty(str)){
            return null;
        }
        List<Integer> list = new ArrayList<>();

        int[] equipAttrIds = StringUtil.stringToIntArg(str, ",");
        for(int i:equipAttrIds){
            list.add(i);
        }
        return list;
    }

    /**
     * 反字符串变数组
     *
     * @param str 字符串
     * @param split 分隔符
     * @return 数组
     * @date 2008-11-25
     * @author eric.chan
     */
    private static int[] stringToIntArg(String str, String split) {
        if (isEmpty(str)) {
            return new int[0];
        }
        String[] strs = str.split(split);
        int[] rt = new int[strs.length];
        for (int i = 0; i < rt.length; i++) {
            rt[i] = Integer.parseInt(strs[i]);
        }
        return rt;
    }

    /**
     * 根据指定的字符把源字符串分割成一个List
     *
     * @param src 源字符串, 表达式
     * @return List
     */
    public static List<String> toList(String src, String pattern) {
        List<String> list = new ArrayList<>();
        if (isNotEmpty(src)) {
            String[] result = src.split(pattern);
            list = Arrays.asList(result);
        }
        return list;
    }

    /**
     * 根据指定的字符把源字符串分割成一个List
     *
     * @param src
     * @return
     */
    public static List<String> toList(String src) {
        String pattern = "，|,|、|。";
        return toList(src, pattern);
    }

    /**
     * 判断对象是否为空
     *
     * @param str 源字符串
     * @return boolean
     */
    private static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断是否是空字符串 null和"" 都返回 true
     *
     * @author Robin Chang
     * @param s 源字符串
     * @return boolean
     */
    private static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
