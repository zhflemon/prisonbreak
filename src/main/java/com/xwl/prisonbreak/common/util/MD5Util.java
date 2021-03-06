package com.xwl.prisonbreak.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xwl
 * @date 2019-11-12 11:18
 * @description MD5加密工具
 */
public class MD5Util {
	 /** 
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符 
     */  
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    protected static MessageDigest messagedigest = null;
    static {  
        try {  
            messagedigest = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  
        }  
    }

    /**
     * 对文件进行MD5加密
     * @param file 文件
     * @return 返回加密后的字符串
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];  
        int numRead = 0;  
        while ((numRead = fis.read(buffer)) > 0) {  
            messagedigest.update(buffer, 0, numRead);  
        }  
        fis.close();  
        return bufferToHex(messagedigest.digest());  
    }

    /**
     * 对字符串进行MD5加密
     * @param str 需要加密的字符串
     * @return 返回加密后的字符串
     */
    public static String getStringMD5(String str){
    	 byte[] buffer=str.getBytes();
    	 messagedigest.update(buffer);
    	return bufferToHex(messagedigest.digest());
    }
  
    public static String bufferToHex(byte bytes[]) {  
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
        // 取字节中高 4 位的数字转换
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        // 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同  
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换  
        stringbuffer.append(c0);  
        stringbuffer.append(c1);  
    }

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\Users\\XUNING\\Desktop\\和为\\项目时间节点.png");
        String stringMd5 = getFileMD5String(file);
        System.out.println(stringMd5);
    }
}  