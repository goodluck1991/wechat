package com.jgj.wechat.course.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by guojun.jiao on 2018/5/7.
 */
public class SignUtil {
    private final static String toten = "wechatCourse";

    private final static String SHA1 = "SHA-1";

    /**
     * 校验签名
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        //对toten,timestamp和nonce进行排序
        String[] paramArr = new String[]{toten,timestamp,nonce};
        Arrays.sort(paramArr);

        //将排序后的结果拼接成一个字符串
        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

        String ciphertext = null;

        try {
            MessageDigest md = MessageDigest.getInstance(SHA1);
            //对拼接后的字符串进行sha-1加密
            byte[] digest = md.digest(content.getBytes());
            ciphertext = byteToStr(digest);

        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return ciphertext!=null?ciphertext.equals(signature.toUpperCase()):false;
    }

    /**
     * 将字节数组转换成十六进制的字符串
     * @param bytes
     * @return
     */
    private static String byteToStr(byte[] bytes){
        String strDigest = "";
        for(int i=0;i<bytes.length;i++){
            strDigest+=byteToHexStr(bytes[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换成十六进制的字符串
     * @param b
     * @return
     */
    private static String byteToHexStr(byte b){
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b>>>4)&0X0F];
        tempArr[1] = Digit[b&0X0F];
        return new String(tempArr);
    }
}
