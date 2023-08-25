package com.chaim.authorization.util;

public class StringUtil {

    public static Boolean isEmpty(String str){
        if (str == null) {
            return true;
        }
        str = str.trim();
        return str.length() == 0;
    }

    /**
     * 获取总页数
     *
     * @param count 数据总量
     * @param pageSize 分页大小
     * @return 总页码
     */
    public static int totalPages(int count, int pageSize) {
        int totalPages = 0;
        if (count % pageSize == 0) {
            totalPages = (int) (double) (count / pageSize);
        } else {
            totalPages = (int) (double) (count / pageSize) + 1;
        }
        return totalPages;
    }
}
