package com.example.mvvm_study.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * 创建者 Chuhui
 *
 * @创建时间 2020/3/6 14:35
 * @描述 获取屏幕宽度和高度
 */
public class DisplayUtil {
    public static int[] getScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            KLog.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
            // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
            int[] sizes = new int[]{widthPixels, heightPixels};
            return sizes;
        }
        return null;
    }

    public static int[] getScreenInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            KLog.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
            // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
            int screenWidth = (int) (widthPixels / density);  // 屏幕宽度(dp)
            int screenHeight = (int) (heightPixels / density);// 屏幕高度(dp)
            int[] sizes = new int[]{screenWidth, screenHeight};
            return sizes;
        }
        return null;
    }

    /**
     * dp转px
     */
    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }


    /**
     * px转dp
     */
    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().density;
    }


    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }


    public static int pxToDpCeilInt(Context context, float px) {
        return (int) (pxToDp(context, px) + 0.5f);
    }
}
