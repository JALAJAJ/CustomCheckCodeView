package com.kingja.customcheckcodeview;

import android.content.Context;

import java.util.Random;

/**
 * Created by Shinelon on 2015/12/14.
 */
public class CheckCodeUtil {
    private static final String[] chineseArr = {"0","1","2","3","4","5","6","7","8","9","零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String[] getNumber(int textNumber) {
        String[] checkCode = new String[textNumber];
        for (int i = 0; i < textNumber; i++) {
            checkCode[i] = chineseArr[(int) (Math.random() * 10)];
        }
        return checkCode;
    }

    public static String[] getChinese(int textNumber) {
        String[] checkCode = new String[textNumber];
        for (int i = 0; i < textNumber; i++) {
            checkCode[i] = chineseArr[(int) (Math.random() * 10)+10];
        }
        return checkCode;
    }
    public static int getRandomDegrees() {
        Random random = new Random();
        int i = random.nextInt(90);
        int randomDegrees = i - 45;
        return randomDegrees;
    }
    public static int[] getPoint(int width, int height) {
        int[] point = new int[2];
        point[0] = (int) (Math.random() * width);
        point[1] = (int) (Math.random() * height);
        return point;
    }
}
