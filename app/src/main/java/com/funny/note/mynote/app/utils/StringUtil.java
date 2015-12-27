package com.funny.note.mynote.app.utils;

import android.text.TextUtils;
import android.text.format.DateFormat;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final String phone_number_match = "^1+[34578]+\\d{9}";
    public static final String pwd_match = "[0-9A-Za-z]{6,16}";
    public static final String email_match = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";;
    // _________________
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private final static String UNITS[] = {" bytes", " KB", " MB", " GB", " TB"};
    private final static int MODEL = 1024;

    private StringUtil() {
    }

    public static boolean isPhoneNumber(String str) {
        return str.matches(phone_number_match);
    }

    public static boolean isPwdCollect(String str) {
        return str.matches(pwd_match);
    }

    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    public static boolean isQQNumber(String mobiles) {
        Pattern p = Pattern.compile("^[1-9][0-9]{4,}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isTelephone(String strEmail) {
        String strPattern = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    public static boolean isMobilephone(String mobiles) {
        if (mobiles.startsWith("86") || mobiles.startsWith("+86")) {
            mobiles = mobiles.substring(mobiles.indexOf("86") + 2);
        }
        /*
         * Pattern p = Pattern
		 * .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		 */
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isZipCode(String mobiles) {
        Pattern p = Pattern.compile("[0-9]\\d{5}(?!\\d)");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isNotBlankAndEmpty(String titleName) {
        if (titleName != null && titleName.length() > 0) {
            return true;
        }
        return false;
    }

    /**
     * @Title: passWordValid
     * @Description: TODO 同时含有数字和字母，且长度要在6-16位之间。
     * @param: @param passWord
     * @param: @return 设定文件
     * @return: boolean 返回类型
     * @date: 2014-8-25 下午6:25:02
     */
    public static boolean passWordValid(String passWord) {
        //String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        String regex = "^[0-9A-Za-z]{6,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(passWord);
        return m.matches();
    }

    public static boolean isIp(String value) {
        if (TextUtils.isEmpty(value)) {
            return false;
        }

        String[] fields = value.split("\\.");
        if (fields.length != 4) {
            return false;
        }

        boolean result = true;
        for (int i = 0; i < fields.length; i++) {
            try {
                int number = Integer.parseInt(fields[i]);
                result &= (number >= 0 && number <= 255);
            } catch (NumberFormatException e) {
                result = false;
            }

            if (!result) {
                break;
            }
        }
        return result;
    }

    public static String getFileMD5(String fileName) {
        InputStream fis;
        try {
            fis = new FileInputStream(fileName);
            byte[] buffer = new byte[1024];
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            return bufferToHex(md5.digest());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMD5(String str) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            int i;
            byte[] b = md5.digest(str.getBytes("UTF-8"));
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = md5Chars[(bt & 0xf0) >> 4];
        char c1 = md5Chars[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    private static char md5Chars[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toHex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i];
            builder.append(HEX[(0xF0 & v) >> 4]);
            builder.append(HEX[0x0F & v]);
        }
        return builder.toString();
    }

    public static byte[] hexToBytes(String str) {
        int len = str.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < b.length; i++) {
            int start = i * 2;
            String v = str.substring(start, start + 2);
            try {
                b[i] = (byte) Integer.parseInt(v, 16);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return b;
    }

    public static String strToHourAndMin(int value) {
        String str = "";
        if ((value + "").length() == 1) {
            str = "0" + value;
        } else {
            str = value + "";
        }
        return str;
    }

    public static boolean isEquals(String str1, String str2) {
        boolean result = false;
        if (str1 != null) {
            result = str1.equals(str2);
        } else if (str2 == null) {
            result = true;
        }
        return result;
    }

    public static String toSqliteStr(String str) {
        String result = null;
        if (str != null) {
            result = str.replaceAll("'", "''");
            result = result.replaceAll("%", "/%");
            result = result.replaceAll("_", "/_");
        }
        return result;
    }

    public static String toFileSize(long size) {
        int lastIndex = 0;
        long value = size;
        long last = 0L;

        for (int i = 0; i < UNITS.length; i++) {
            long newValue = value / MODEL;
            if (newValue <= 0) {
                break;
            }

            last = value % MODEL;
            value = newValue;
            lastIndex = i + 1;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(value);
        if (last > 0L) {
            float f = (last + 0.0f) / (MODEL + 0.0f);
            int i = Math.round(f * 100);
            if (i > 0) {
                builder.append(".").append(i);
            }
        }
        builder.append(UNITS[lastIndex]);
        return builder.toString();
    }

    public static String getDateString(long time) {
        return (String) DateFormat.format("yyyy-MM-dd", time * 1000);
    }

    public static int nullSafeStringComparator(final String one,
                                               final String two) {
        if (one == null ^ two == null) {
            return (one == null) ? -1 : 1;
        }

        if (one == null && two == null) {
            return 0;
        }
        return one.compareToIgnoreCase(two);
    }

    public static byte[] toBytes(String str) {
        int len = str.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < b.length; i++) {
            int start = i * 2;
            String v = str.substring(start, start + 2);
            try {
                b[i] = (byte) Integer.parseInt(v, 16);
            } catch (NumberFormatException e) {
                e.getMessage();
                return null;
            }
        }
        return b;
    }

    public static String[] split(String string, String delimiters) {
        StringTokenizer st = new StringTokenizer(string, delimiters);
        String[] fields = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++) {
            String token = st.nextToken();
            fields[i] = token;
        }

        return fields;
    }

    public static int versionCompare(String version1, String version2) {
        if (version1 == null && version2 == null) {
            return 0;
        } else if (version1 == null) {
            return -1;
        } else if (version2 == null) {
            return 1;
        } else {
            String[] fields1 = split(version1, ".");
            String[] fields2 = split(version2, ".");
            int loop = Math.min(fields1.length, fields2.length);

            int result = 0;
            for (int i = 0; i < loop; i++) {
                result = fields1[i].compareTo(fields2[i]);
                if (result != 0) {
                    return result;
                }
            }

            return fields1.length - fields2.length;
        }
    }

    public static String formatGB(String format, long usedSize,
                                  long availableSize) {
        return String.format(format, usedSize / (1024.0 * 1024.0 * 1024.0),
                availableSize / (1024.0 * 1024.0 * 1024.0));
    }

    public static String formatJsonString(String jsonObj) {
        if (!jsonObj.startsWith("{"))
            jsonObj = jsonObj.substring(1);

        return jsonObj;
    }

    public static boolean isNullJsonObj(JSONObject json, String name) {
        return json != null ? json.isNull(name) : false;
    }

    public static String unEncode(String data) {
        if (null == data || "".equals(data))
            return data;
        data = data.replaceAll("&quot;", "\"");
        data = data.replaceAll("&lt;", "<");
        data = data.replaceAll("&gt;", ">");
        data = data.replaceAll("&#92;", "\\\\");
        data = data.replaceAll("&apos;", "'");
        data = data.replaceAll("&amp;", "&");
        return data;
    }

    /**
     * 检查字符串是否为null或者为空
     *
     * @param s
     * @return
     */
    public static boolean emptyOrNull(String s) {
        if (s == null) {
            return true;
        }
        if ("".equals(s)) {
            return true;
        }
        return false;
    }

    /**
     * 将string转为int ，异常时返回-1
     *
     * @param s
     * @return
     */
    public static int toInt(String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

    /**
     * @Title: getDistatce
     * @Description: TODO 经纬度距离
     * @param: @param lat1
     * @param: @param lat2
     * @param: @param lon1
     * @param: @param lon2
     * @param: @return 设定文件
     * @return: double 返回类型
     * @date: 2014-12-16 下午2:56:19
     */
    public static double getDistatce(double lat1, double lat2, double lon1,
                                     double lon2) {
        double R = 6371;
        double distance = 0.0;
        double dLat = (lat2 - lat1) * Math.PI / 180;
        double dLon = (lon2 - lon1) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1 * Math.PI / 180)
                * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        distance = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) * R;
        return distance;
    }
}
