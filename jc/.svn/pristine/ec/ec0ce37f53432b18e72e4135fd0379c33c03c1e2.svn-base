package com.hgsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密组件
 * 
 * @author huang_cx
 * @version 1.0
 */
public abstract class MD5Coder {

	/**
	 * MD5加密
	 * 
	 * @param data
	 *            待加密数据
	 * @return byte[] 消息摘要
	 * 
	 * @throws Exception
	 */
	public static byte[] encodeMD5(String data) throws Exception {
		// 执行消息摘要
		return DigestUtils.md5(data);
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 *            待加密数据
	 * @return byte[] 消息摘要
	 * 
	 * @throws Exception
	 */
	public static String encodeMD5Hex(String data) throws Exception {
		// 执行消息摘要
		return DigestUtils.md5Hex(data);
	}

	/**
	 * 登录用户密码加密
	 * 
	 * @param username
	 *            登录用户名
	 * @param password
	 *            登录密码
	 * @return
	 * @throws Exception
	 */
	public static String encodeLoginUser(String username, String password) {
		try {
			return encodeMD5Hex(password + "{" + username + "}");
		} catch (Exception e) {
		}
		return "";
	}
	
	/*======================================= =======================================*/
	/** * 16进制字符集 */
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    
	/** * 指定算法为MD5的MessageDigest */
    private static MessageDigest messageDigest = null;

    /** * 初始化messageDigest的加密算法为MD5 */
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * * 获取文件的MD5值
     * 
     * @param file
     *            目标文件
     * 
     * @return MD5字符串
     */
    public static String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;
        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                    file.length());
            messageDigest.update(byteBuffer);
            ret = bytesToHex(messageDigest.digest());
            
            //return ret;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if (ch != null) {
                try {
                    ch.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }          
        }
        return ret;
    }
    
    /**
     * * 将字节数组转换成16进制字符串
     * 
     * @param bytes
     *            目标字节数组
     * 
     * @return 转换结果
     */
    public static String bytesToHex(byte bytes[]) {
        return bytesToHex(bytes, 0, bytes.length);

    }
    
    /**
     * * 将字节数组中指定区间的子数组转换成16进制字符串
     * 
     * @param bytes
     *            目标字节数组
     * 
     * @param start
     *            起始位置（包括该位置）
     * 
     * @param end
     *            结束位置（不包括该位置）
     * 
     * @return 转换结果
     */
    public static String bytesToHex(byte bytes[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();

    }
    
    /**
     * * 将单个字节码转换成16进制字符串
     * 
     * @param bt
     *            目标字节
     * 
     * @return 转换结果
     */
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];

    }
    /*======================================= =======================================*/

    public static String getHashNio(File fileName, String hashType) {  
        FileInputStream fStream = null;  
        String hash = null;  
        try {  
            MessageDigest md5 = MessageDigest.getInstance(hashType);  
            fStream = new FileInputStream(fileName);  
            FileChannel fChannel = fStream.getChannel();  
            ByteBuffer buffer = ByteBuffer.allocate(8 * 1024);  
            for (int count = fChannel.read(buffer); count != -1; count = fChannel.read(buffer)) {  
                buffer.flip();  
                md5.update(buffer);  
                if (!buffer.hasRemaining()) {  
                    // System.out.println("count:"+count);  
                    buffer.clear();  
                }  
            }  
            hash = bytesToHex(md5.digest()); 
   
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (fStream != null)  
                    fStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return hash;  
    } 
    
    /**
     * 获取单个文件的MD5值！ (存在问题，最前面为0时会丢失)
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
      if (!file.isFile()){
        return null;
      }
      MessageDigest digest = null;
      FileInputStream in=null;
      byte buffer[] = new byte[1024];
      int len;
      try {
        digest = MessageDigest.getInstance("MD5");
        in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, 1024)) != -1) {
          digest.update(buffer, 0, len);
        }
        in.close();
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
      BigInteger bigInt = new BigInteger(1, digest.digest());
      return bigInt.toString(16).toUpperCase();
    }
    
	public static void main(String[] args) throws Exception {
		//final String name = "admin";
		//final String password = "hgsoft";

		// admin
		// test1
		// Hello
		// second

		//System.out.println(encodeMD5Hex(password + "{" + name + "}"));//c0d4d722014c42ec6cbf54b909163524
		
		/*
		long startTime = System.currentTimeMillis();
		String md51 = getHashNio(new File("C:\\Users\\Administrator\\Desktop\\2.mp4"),"MD5");
		long endTime = System.currentTimeMillis();
		
		float seconds = (endTime - startTime)/1000F;
		System.out.println(md51 + "," + Float.toString(seconds) + "seconds");
		
		long startTime2 = System.currentTimeMillis();
		String md52 = getFileMD5String(new File("C:\\Users\\Administrator\\Desktop\\2.mp4"));//D:\\webupload\\sourceFile\\2.mp4  
		long endTime2 = System.currentTimeMillis();
		
		float seconds2 = (endTime2 - startTime2)/1000F;
		System.out.println(md52 + "," + Float.toString(seconds2) + "seconds");
		
		long startTime3 = System.currentTimeMillis();
		String md53 = getFileMD5(new File("C:\\Users\\Administrator\\Desktop\\2.mp4"));
		long endTime3 = System.currentTimeMillis();
		
		float seconds3 = (endTime3 - startTime3)/1000F;
		System.out.println(md53 + "," + Float.toString(seconds3) + "seconds");
		*/
	}
}
