package com.jcg.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 
 * File: EncryptUtil.java
 *
 * Description: 加密解密 工具类
 *
 * @author 胡大林
 * Notes: EncryptUtil.java 2018年10月24日 下午2:07:38 darling
 */
public class EncryptUtil {

	/**
	 * DES解密
	 * 
	 * @param src   数据源
	 * @param key   密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * DES加密
	 * @param src   数据源
	 * 
	 * @param key   密钥，长度必须是8的倍数
	 * 
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	/**
	 * sha1加密算法
	 * @param key需要加密的字符串
	 * @return 加密后的结果
	 */
	public static String sha1(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(key.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
			return key;
		}
	}
	
	/* AES 对称加密算法 加密\解密 begin */
	private  static final String AES="AES";  
    private  static final String UTF8="UTF-8";  
    static KeyGenerator kgen =null;  
    static{  
        try {  
            kgen= KeyGenerator.getInstance(AES);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 加密
     * @param content: 需要加密的字符串
     * @param password: 加密的秘钥
     */  
    public static byte[] encryptAes(String content, String password) {  
        try {  
            // 使用静态代码块来生成KeyGenerator对象  
            //KeyGenerator kgen = KeyGenerator.getInstance(AES);  
        	
        	// 解决加密结果不一致问题不一致问题
        	SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" ); 
            secureRandom.setSeed(password.getBytes()); 
        	
            // 使用128 位  
            kgen.init(128, secureRandom);  
            // kgen.init(128, new SecureRandom(password.getBytes()));  
            SecretKey secretKey = kgen.generateKey();  
            byte[] encodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);  
            // Cipher对象实际完成加密操作  
            Cipher cipher = Cipher.getInstance(AES);  
            // 加密内容进行编码  
            byte[] byteContent = content.getBytes(UTF8);  
            // 用密匙初始化Cipher对象  
            cipher.init(Cipher.ENCRYPT_MODE, key);  
            // 正式执行加密操作  
            byte[] result = cipher.doFinal(byteContent);  
            return result;  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    /** 
     * 解密
     * @param content: 加密后字符串
     * @param password: 加密的秘钥
     * @throws UnsupportedEncodingException 
     */  
    public static String decryptAes(String content, String password) throws UnsupportedEncodingException {  
        try {// 使用静态代码块来生成KeyGenerator对象  
            //KeyGenerator kgen = KeyGenerator.getInstance(AES);  
        	
        	// 解决加密结果不一致问题不一致问题
        	SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" ); 
            secureRandom.setSeed(password.getBytes()); 
            
            // 使用128 位  
            kgen.init(128, secureRandom);  
            SecretKey secretKey = kgen.generateKey();  
            byte[] encodeFormat = secretKey.getEncoded();  
            SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);  
            // Cipher对象实际完成加密操作  
            Cipher cipher = Cipher.getInstance(AES);  
            // 用密匙初始化Cipher对象  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            // 正式执行解密操作  
            byte[] decryptFrom = Util.parseHexStr2Byte(content);  
            byte[] decryptResult = cipher.doFinal(decryptFrom);  
            // 解密内容进行解码  
            String result = new String(decryptResult, UTF8);
            return result;  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    /* AES 对称加密算法 加密\解密 end */
    
    /* 自定义针对APP加密交互  加密\解密 begin */
	/**
	 * 解密
	 * @param param
	 * @return
	 * @throws Exception 
	 */
    public static JSONObject decrypt(String param) throws Exception{
		param = param.replaceAll("\\s", "+");
		JSONObject json = new JSONObject();
		// 解密前端参数
		byte[] bytes = EncryptUtil.decrypt(EncryptUtil.decryptBASE64(param), Constant.DES_PWD_JCGWX.getBytes());
		// Convert back to String.
		param = new String(bytes);
		json = JSONObject.parseObject(param);
		return json;
	}
	/**
	 * 加密
	 * @param param
	 * @return
	 * @throws Exception 
	 */
    public static String encrypt(String param) throws Exception{
		// 加密返回数据
		param = EncryptUtil.encryptBASE64(EncryptUtil.encrypt(param.getBytes(), Constant.DES_PWD_JCGWX.getBytes()));
		param = param.replaceAll("[*\t\n\r]", ""); 
		return param;
	}
    /* 自定义针对APP加密交互  加密\解密 end */
    
    /* 自定义MD5 加密\解密 begin */
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            	hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     * convertMD5(s)
     * convertMD5(convertMD5(s))
     */   
    public static String convertMD5(String inStr){  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }
    /** 
     * MD5加密
     * origin 加密数据
     * charsetname 加密编码，如：utf-8
     */  
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
           resultString = new String(origin);
           MessageDigest md = MessageDigest.getInstance("MD5");
           if (charsetname == null || "".equals(charsetname))
              resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
           else
              resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
	}
	private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
           resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
     }
     private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
           n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
     }
     private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    /* 自定义MD5 加密\解密 end */
    
    /**
	 * SHA256加密
	 * @param str 加密后的报文
	 * @return
	 */
	public static String sha256(String str){
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
		    encodeStr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
	    }
		return encodeStr;
	}
	
	/**
	 * 将byte转为16进制
	 * @param bytes
	 * @return
	 */
	private static String byte2Hex(byte[] bytes){
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i=0;i<bytes.length;i++){
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length()==1){
				//1得到一位的进行补0操作
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}
}
