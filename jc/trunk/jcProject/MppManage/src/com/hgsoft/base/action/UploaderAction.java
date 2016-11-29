package com.hgsoft.base.action;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hgsoft.base.entity.Upload;
import com.hgsoft.base.service.UploadService;
import com.hgsoft.cxf.common.PropertiesUtil;
import com.hgsoft.security.action.BaseAction;
import com.hgsoft.util.FileUtil;
import com.hgsoft.util.MD5Coder;

@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UploaderAction extends BaseAction {

	public final static String TEMP = PropertiesUtil.getProperty("baseDir") + "/" + PropertiesUtil.getProperty("temp");
	
	private static Map FILE_PIECE_MD5 = new HashMap<String,HashMap<String,String>>();//用于保存文件分片MD5
	
	private String size;
	private String name;
	private String fileMd5;
	private int chunk;
	private int chunks;
	private String pid;
	private String uploadBizType;
	private String fileType;
	private File file;
	
	private Upload upload = new Upload();
	private HashMap map = new HashMap();
	
	@Resource
	private UploadService uploadService;
 
	/*
	 * 上传预处理
	 */
	public String preUpload() {
		
		upload = uploadService.getUploadByPidAndMd5(pid,fileMd5);
		//上传成功
		if(null != upload) {
			map.put("result", "success");
		}else {
			Map piece_md5 = (HashMap)FILE_PIECE_MD5.get(name);
			//缓存中不存在目标文件的md5 Map
			if(null == piece_md5) {
				String prefix = name.substring(0,name.lastIndexOf("."));//文件前缀test.txt -> test 
				File targetFolder = new File(TEMP + "/" + prefix);//
				if(targetFolder.exists()) {
					File[] files = targetFolder.listFiles();
					piece_md5 = new HashMap<String,String>();
					String pieceNo;
					String fName;//test_1.txt					
					for(File f: files) {
						fName = f.getName();
						if(fName.contains(prefix)) {
							pieceNo = fName.substring(fName.lastIndexOf("_") + 1,fName.lastIndexOf("."));
							piece_md5.put(pieceNo, MD5Coder.getHashNio(f,"MD5"));
						}
					}
				}
				
				//起点上传
				if(null == piece_md5 || piece_md5.isEmpty()) {
					map.put("result", "never");
				//断点续传
				}else {
					synchronized (UploaderAction.class) {
						FILE_PIECE_MD5.put(name, piece_md5);
					}
					
					map.put("chunkMd5s", piece_md5);
				}
			//断点续传
			}else {
				map.put("chunkMd5s", piece_md5);
			}
		}
				
		return "success";
	}

	public void upload() {
		System.out.println(file + " " + size + " " + name + " " + chunk + " " + chunks);
		
		String prefix = name.substring(0,name.lastIndexOf("."));//文件前缀test.txt -> test
		File targetFolder = new File(TEMP + "/" + prefix);//D:/webupload/temp/test
		File target = null;
		
		//保存第一块分片时
		if(chunk == 0) {
			if(!(targetFolder.exists() && targetFolder.isDirectory())) {
				targetFolder.mkdir();
			}			
		}
		target = new File(TEMP + "/" + prefix + "/" + prefix + "_" + chunk + name.substring(name.lastIndexOf(".")));
		
		if(target.exists()) {
			target.delete();
		}
		FileUtil.fileCopy(file,target);
		
		//分片时
		if(chunks > 1 && null != target) {
			Map piece_md5 = (HashMap)FILE_PIECE_MD5.get(name);
			if(piece_md5 == null) {
				piece_md5 = new HashMap<String,String>();
			}
			piece_md5.put(chunk, MD5Coder.getHashNio(target,"MD5"));//设置分片文件的md5
			synchronized (UploaderAction.class) {
				FILE_PIECE_MD5.put(name, piece_md5);//更新缓存
			}		
		}

	}
	
	public String mergeFiles() {
		
		String prefix = name.substring(0,name.lastIndexOf("."));//文件前缀test.txt -> test
		File targetFolder = new File(TEMP + "/" + prefix);//
		File[] files = targetFolder.listFiles();
		String fName;//test_1.txt
		
		List<File> tarFiles = new ArrayList<File>();
		for(File f: files) {
			fName = f.getName();
			if(fName.contains(prefix)) {
				tarFiles.add(f);
			}
		}
		
		if(tarFiles.size() < chunks) {
			map.put("result", "lose");//文件分片丢失
		}else {
			tarFiles = sortPieceFile(tarFiles);//文件排序
			
			String extName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();//文件扩展名
			String uploadNewFileName = UUID.randomUUID().toString()+"."+extName;
			File sourceFile = uploadService.creatMkdir(UploadService.path, uploadNewFileName, uploadBizType);//new File(UploadService.path + "/" + "uploadDemo" + "/" + name);
			sourceFile = FileUtil.fileUnion(sourceFile,tarFiles);//文件合并
			
	        String md5After = "";//文件合并后md5值
	        if(sourceFile.exists()) {// && sourceFile.length() == Long.parseLong(size)
	        	md5After = MD5Coder.getHashNio(sourceFile,"MD5");
	        }
	        //md5验证
	        if(md5After.equalsIgnoreCase(fileMd5)) {//	
	        	//现场记录 -- 同时添加验收管理附件
	        	if(uploadBizType.equals("monitor")){
	        	/*	Accept accept = acceptService.getAcceptByMid(pid);
	        		if(accept!=null){
	        			//保存文件信息
			        	uploadService.saveFile(sourceFile.getAbsolutePath(), sourceFile.getName(), sourceFile.length(), accept.getId(), operator.getName(), fileType,fileMd5);
	        		}*/
	        	}
	        	//保存文件信息
	        	uploadService.saveFile(sourceFile.getAbsolutePath(), sourceFile.getName(), sourceFile.length(), pid, operator.getName(), fileType,fileMd5);
	        	
	        	//删除缓存中的数据
	        	synchronized (UploaderAction.class) {
	        		FILE_PIECE_MD5.remove(name);
	        	}
	        	
	        	//删除temp文件中的分片文件
	        	if(targetFolder.exists()) {
		        	FileUtil.deleteDir(targetFolder);
	        	}
	        		        	
	        	map.put("result", "success");
	        }else {
	        	if(sourceFile.exists()) {
	        		sourceFile.delete();
	        	}
	        	
	        	map.put("result", "fail");//文件合并错误
	        }
		}
		
		return "success";
	}
	
	//清空缓存和temp中分片文件
	public synchronized void clear() {
		
		FILE_PIECE_MD5.clear();//清空缓存
		
		FileUtil.deleteDirSub(new File(TEMP));
		
		System.out.println("清空缓存和temp中分片文件成功！......");
	}
	
	
	/*按分片编号排序*/
	public List<File> sortPieceFile(List<File> tarFiles) {
		
		Collections.sort(tarFiles, new Comparator<File>() {
		    @Override
		    public int compare(File o1, File o2) {
		        if (o1.isDirectory() && o2.isFile())
		            return -1;
		        if (o1.isFile() && o2.isDirectory())
		            return 1;
		        String f1Nm = o1.getName();
		        int no1 = Integer.parseInt(f1Nm.substring(f1Nm.lastIndexOf("_") + 1,f1Nm.lastIndexOf(".")));
		        String f2Nm = o2.getName();
		        int no2 = Integer.parseInt(f2Nm.substring(f2Nm.lastIndexOf("_") + 1,f2Nm.lastIndexOf(".")));
		        return no1 - no2;
		    }
		});
		
		return tarFiles;
	}
	
	public void ajaxResponse(String result) {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		Writer writer = null;
		try {
			writer = response.getWriter();
			writer.write(result);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/* ========================= get/set ======================= */

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		//将文件扩展名转换成小写
		this.name = name.substring(0, name.lastIndexOf(".")) + "." + name.substring(name.lastIndexOf(".")+1).toLowerCase();
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	
	public Upload getUpload() {
		return upload;
	}

	public void setUpload(Upload upload) {
		this.upload = upload;
	}

	public int getChunk() {
		return chunk;
	}

	public void setChunk(int chunk) {
		this.chunk = chunk;
	}

	public int getChunks() {
		return chunks;
	}

	public void setChunks(int chunks) {
		this.chunks = chunks;
	}

	public HashMap getMap() {
		return map;
	}

	public void setMap(HashMap map) {
		this.map = map;
	}

	public String getUploadBizType() {
		return uploadBizType;
	}

	public void setUploadBizType(String uploadBizType) {
		this.uploadBizType = uploadBizType;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
