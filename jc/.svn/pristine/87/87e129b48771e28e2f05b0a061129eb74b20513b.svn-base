package com.hgsoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public class FileUtil {

	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * 
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    /**
     * 递归删除目录下的所有文件及子目录下所有文件(不包括目录自身)
     * @param dir 将要删除的文件目录
     * 
     */
    public static boolean deleteDirSub(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /*文件合并*/
	public static File fileUnion(File outFile, List<File> files) {  
        FileChannel outChannel = null;  
        System.out.println("Merge " + files.toString() + " into " + outFile);  
        try {  
            outChannel = new FileOutputStream(outFile).getChannel();
            for(File f : files){  
                FileChannel fc = new FileInputStream(f).getChannel(); 
                ByteBuffer bb = ByteBuffer.allocate(1024 * 8);  
                while(fc.read(bb) != -1){  
                    bb.flip();  
                    outChannel.write(bb);  
                    bb.clear();  
                }  
                fc.close();  
            }  
            System.out.println("Merged!! ");  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        } finally {  
            try {if (outChannel != null) {outChannel.close();}} catch (IOException ignore) {}  
        } 
        
        return outFile;
    }
	
	/*文件复制*/
	public static void fileCopy(File s, File t) {
		
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(s);
			out = new FileOutputStream(t);
			
			byte[] buff = new byte[1024];
			int length;
			while((length = in.read(buff)) != -1) {
				out.write(buff, 0, length);
			}   
			
		} catch (Exception e) {   
            //e.printStackTrace();   
			System.out.println("fileCopy 文件复制异常");
        } finally {   
            try {   
            	if(out != null){out.close();}
            } catch (IOException e) {   
                e.printStackTrace();   
            } 
            try {   
            	if(in != null){in.close();}
            } catch (IOException e) {   
                e.printStackTrace();   
            }
        }
	}
	
	/*文件合并*/
	/*public File fileUnion(File union,List<File> files) {
		
		if(union.exists()) {
			union.delete();
		}
		
		FileInputStream in = null;
		FileOutputStream out = null;
			
		try {
			out = new FileOutputStream(union, true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//以追加的方式
		for(File f: files) {
			try {
				in = new FileInputStream(f);
				
				byte[] buff = new byte[1024];
				int length;
				while((length = in.read(buff)) != -1) {
					out.write(buff, 0, length);
				}
			} catch (Exception e) {   
	            e.printStackTrace();   
	        } finally {   
	            try {   
	            	if(in != null){in.close();}
	            } catch (IOException e) {   
	                e.printStackTrace();  
	            } 
	        }
		}
		
		try {   
        	if(out != null){out.close();}
        } catch (IOException e) {   
            e.printStackTrace();   
        }
			
		return union;
	}*/
	
	/* 文件复制
	public void fileChannelCopy(File s, File t) {

		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(s);

			fo = new FileOutputStream(t);

			in = fi.getChannel();// 得到对应的文件通道

			out = fo.getChannel();// 得到对应的文件通道

			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}*/
}
