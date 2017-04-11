package com.gridlayouttest.util;


/**
 * 星座计算
 *
 * @author qihuaren@uworks.cc
 * @date 2015年7月9日
 */
public class ConstellationUtil {

    private final static int[] dayArr = new int[]{20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22};
    private final static String[] constellationArr = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};

    /**
     * 根据日期计算星座
     *
     * @param month 月
     * @param day   日
     * @return String
     */
    public static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];
    }
}
