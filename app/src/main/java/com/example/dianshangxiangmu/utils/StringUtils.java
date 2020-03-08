package com.example.dianshangxiangmu.utils;

public class StringUtils {

    /**
     * 把array切割成以逗号分隔的字符串
     * @param array
     * @return
     */
    public static String splitArray(int[] array){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<array.length; i++){
            sb.append(array[i]);
            if(i<array.length-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
