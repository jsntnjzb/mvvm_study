package com.example.mvvm_study.Utils;

import android.text.TextUtils;

import java.util.Random;

public class HexUtils {
    /**
     * 10进制数转为16进制字符串
     *
     * @param
     * @return
     */
    public static String convertDecToHexString(int num) {
        String str = Integer.toHexString(num);
        if (str.length() % 2 == 1) {
            str = "0" + str;
        }
        return str;
    }


    /**
     * 十六进制编码字符串转为对应的二进制数组
     *
     * @param s 十六进制编码字符串
     * @return
     */
    public static byte[] hexStringToBytes(String s) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return baKeyword;
    }

    /**
     * byte数组转为对应的16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().trim().toUpperCase();
    }

    /**
     * 16进制字符串累加和校验
     */
    public static String makeChecksum(String data) {
        if (data == null || data.equals("")) {
            return "";
        }
        int total = 0;
        int len = data.length();
        int num = 0;
        while (num < len) {
            String s = data.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        /**
         * 用256求余最大是255，即16进制的FF
         */
        int mod = total % 256;
        String hex = Integer.toHexString(mod);
        len = hex.length();
        // 如果不够校验位的长度，补0,这里用的是两位校验
        if (len < 2) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String getSoftVersion(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return "";
        }
        char[] chars = hexString.toCharArray();
        String version_str = "V";
        for (int i = 0; i < chars.length; i++) {
            if (i < chars.length - 1) {
                version_str += chars[i] + ".";
            } else {
                version_str += chars[i];
            }
        }
        return version_str;
    }

    /**
     * 16进制字符串分割成字符串数组
     * 这里是每两位分割成一个
     *
     * @param hexStr   16进制字符串
     * @param splitLen 每隔几位分割
     */
    public static String[] getHexArray(String hexStr, int splitLen) {
        if (hexStr == null || hexStr == "" || splitLen == 0) {
            return null;
        }
        char[] chars = hexStr.toCharArray();
        int j = hexStr.length() / splitLen;
        int k = hexStr.length() % splitLen;
        int len = j + k;
        String[] array = new String[len];
        for (int i = 0; i < j; i++) {
            array[i] = (chars[splitLen * i]) + String.valueOf(chars[splitLen * i + 1]);
        }
        return array;
    }

    /**
     * 字节数组中截取指定长度数组元素
     *
     * @param src   源数组
     * @param begin 源数组要复制的起始位置
     * @param count 要复制的长度
     * @return 新的字节数组
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }


    /**
     * 10进制转16进制
     *
     * @param num
     * @param totalLenght 需要的字符串总长度
     * @return
     */
    public static String toHex(int num, int totalLenght) {
        String str = Integer.toHexString(num);
        str = splicingZero(str, totalLenght);
        return str;
    }

    /**
     * 字符串前面补零操作
     *
     * @param str         字符串本体
     * @param totalLenght 需要的字符串总长度
     * @return
     */
    public static String splicingZero(String str, int totalLenght) {
        int strLenght = str.length();
        String strReturn = str;
        for (int i = 0; i < totalLenght - strLenght; i++) {
            strReturn = "0" + strReturn;
        }
        return strReturn;
    }


    /**
     * 生成一个0 到 count 之间的随机数
     *
     * @param endNum
     * @return
     */
    public static int getNum(int endNum) {
        if (endNum > 0) {
            Random random = new Random();
            return random.nextInt(endNum);
        }
        return 0;
    }

}
