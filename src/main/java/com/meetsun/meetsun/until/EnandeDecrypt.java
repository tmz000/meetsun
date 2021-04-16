package com.meetsun.meetsun.until;
 
import java.util.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
 
public class EnandeDecrypt{
	public static void main(String[] args) throws Exception {
		String str = "青锋剑，偃月刀";
     	base64(str);
     	enAndDeCode(str);
	}
 
	public static void base64(String str) {
		byte[] bytes = str.getBytes();
     
		String encoded = Base64.getEncoder().encodeToString(bytes);
		System.out.println("Base 64 加密后：" + encoded);
     
		byte[] decoded = Base64.getDecoder().decode(encoded);
		String decodeStr = new String(decoded);
		System.out.println("Base 64 解密后：" + decodeStr);
		System.out.println();
	}
   
	public static void enAndDeCode(String str) throws Exception {
		String data = encryptBASE64(str.getBytes());
		System.out.println("sun.misc.BASE64 加密后：" + data);
		
		byte[] byteArray = decryptBASE64(data);
		System.out.println("sun.misc.BASE64 解密后：" + new String(byteArray));
	}
 
	public static byte[] decryptBASE64(String key) throws Exception { 
		return (new BASE64Decoder()).decodeBuffer(key); 
	}
 
	public static String encryptBASE64(byte[] key) throws Exception { 
		return (new BASE64Encoder()).encodeBuffer(key); 
	}
 }
