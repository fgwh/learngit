package com.hgsoft.component.transmission;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.ConnectException;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.hgsoft.component.ProcessMode;
import com.hgsoft.component.UploadStatus;
import com.hgsoft.component.Uploader;

/**
 * com.hgsoft.component.transmission
 *
 * @Project: QuartzTask
 * @Date: 2013/14/27
 * @Author: zhouzhaofeng
 * @Desc:
 */
public class FtpUploader extends RemoteClient implements Uploader {
    private static final int MAX_RECONNECT_CNT = 10;  //最大重连次数
    private static final int INTERVAL_TIMEOUT = 5000;  //重连间隔时间
    private static Logger logger = Logger.getLogger(FtpDownloader.class);
    private ProcessMode mode = ProcessMode.NORMAL;  //是否支持正则
    private FTPClient ftpClient;
    private File[] files;
    private UploadStatus[] statuses;

    public FtpUploader(String server, int port, String userName, String userPassword) {
        this.server = server;
        this.port = port;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public static void main(String[] args) throws IOException {
        FtpUploader ftpUploader = new FtpUploader("127.0.0.1", 21, "root", "123456");
        ftpUploader.setFiles(new File[]{
                new File("E:/front/313_20131101_TB_User.txt"),
                new File("E:/front/313_20131101_TB_TradeList.txt"),
                new File("E:/front/313_20131101_TB_OBU.txt"),
                new File("E:/front/313_20131101_TB_DisputeData.txt"),
                new File("E:/front/313_20131101_TB_InvoiceStock.txt"),
                new File("E:/front/313_20131101_TB_DayEndVerify.txt"),
                new File("E:/front/313_20131101_TB_CardBlackList.txt"),
                new File("E:/front/313_20131101_TB_Card.txt"),
                new File("E:/front/313_20131101_TB_BusinessSum.txt"),
                new File("E:/front/313_20131101_TB_AccountVerify.txt"),
                new File("E:/front/313_20131101_TB_Account.txt")
        });
        ftpUploader.connect();
        ftpUploader.upload("upload");
    }

    /**
     * 连接
     */

    public void connect() {
        logger.info("正在进行FTP连接.......");
        ftpClient = new FTPClient();  //持有FTP client实例
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); //ftp操作命令写在控制台
        try {
            int reply; //返回码，标志是否成功
            ftpClient.connect(server, port);  //连接
            ftpClient.login(userName, userPassword);  //登录
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                logger.error("连接FTP的用户名或者密码错误......");
                this.disconnect();
            }
            logger.info("登录成功......");
            logger.info("FTP服务器地址：" + server);
            logger.info("FTP服务器端口：" + port);
            logger.info("FTP用户名：" + userName);
            ftpClient.changeWorkingDirectory(INIT_DIRECTORY); //切换到ftp工作目录
        } catch (ConnectException connectException) {
            logger.warn("连接异常，准备重连......");
            try {
                Thread.sleep(INTERVAL_TIMEOUT);
            } catch (InterruptedException e) {
                logger.error("连接终止！");
            }
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把files文件全部传输到特定文件夹
     *
     * @param remotePath
     * @throws IOException
     */
    public boolean upload(String remotePath) throws IOException {
        if (remotePath == null || remotePath.length() == 0) {
            remotePath = "";
        }
        UploadStatus status;
        boolean isUpload = true;
        if (files.length > 0) {
            for (File file : files) {
            	status = this.upload(remotePath + "/" + file.getName(), file.getAbsolutePath());
            	if(UploadStatus.Upload_From_Break_Failed == status || UploadStatus.Upload_New_File_Failed == status) {
            		logger.error("上传文件失败");
            		isUpload = false;
            		break;
            	}
            }
        } else {
            logger.error("找不到需要上传的文件");
            isUpload = false;
        }
        
        return isUpload;
    }

    @Override
    public UploadStatus upload(String remote, String local) throws IOException {
        logger.info("把*" + local + "*上传到*" + remote + "*");

        //设置PassiveMode传输
        ftpClient.enterLocalPassiveMode();
        //设置以二进制流的方式传输
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.setControlEncoding("GBK");

        UploadStatus result;
        //对远程目录的处理
        String remoteFileName = remote;
        if (remote.contains("/")) {
            remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
            //创建服务器远程目录结构，创建失败直接返回
            if (CreateDirecroty(remote, ftpClient) == UploadStatus.Create_Directory_Fail) {
                return UploadStatus.Create_Directory_Fail;
            }
        }

        //检查远程是否存在文件
        FTPFile[] files = ftpClient.listFiles(remote);
 /*       if (files.length == 1) {  //远程文件已经存在
            long remoteSize = files[0].getSize();
            File f = new File(local);
            long localSize = f.length();
            if (remoteSize == localSize) {
                return UploadStatus.File_Exits;
            } else if (remoteSize > localSize) {
                return UploadStatus.Remote_Bigger_Local;
            }

            //尝试移动文件内读取指针,实现断点续传
            result = uploadFile(remoteFileName, f, ftpClient, remoteSize);

            //如果断点续传没有成功，则删除服务器上文件，重新上传
            if (result == UploadStatus.Upload_From_Break_Failed) {
                if (!ftpClient.deleteFile(remoteFileName)) {
                    return UploadStatus.Delete_Remote_Faild;
                }
                result = uploadFile(remoteFileName, f, ftpClient, 0);
            }
        } else {
            result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
        }*/
        result = uploadFile(remote, new File(local), ftpClient, 0);
        return result;
    }

    public void disconnect() {
        logger.info("进入FTP连接关闭连接方法...");
        // 判断客户端是否连接上FTP
        if (ftpClient.isConnected()) {
            // 如果连接上FTP，关闭FTP连接
            try {
                logger.info("关闭ftp连接......");
                ftpClient.disconnect();
            } catch (IOException e) {
                logger.error("关闭ftp连接出现异常......", e);
            }
        }
    }

    //创建目录
    public UploadStatus CreateDirecroty(String remote, FTPClient ftpClient) throws IOException {
        logger.info("在FTP服务器新建文件：" + remote);
        UploadStatus status = UploadStatus.Create_Directory_Success;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(new String(directory.getBytes("GBK"), "iso-8859-1"))) {
            //如果远程目录不存在，则递归创建远程服务器目录
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        logger.error("创建目录失败");
                        return UploadStatus.Create_Directory_Fail;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                //检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return status;
    }

    public UploadStatus uploadFile(String remoteFile, File localFile, FTPClient ftpClient, long remoteSize) throws IOException {
        UploadStatus status;
        //显示进度的上传
        long step = localFile.length() / 100;  // 18 / 100
        long process = 0;
        long localreadbytes = 0L;
        RandomAccessFile raf = new RandomAccessFile(localFile, "r");
        //OutputStream out = ftpClient.appendFileStream(remoteFile); //追加文件
        OutputStream out = ftpClient.storeFileStream(remoteFile+".bak");  //直接覆盖原来文件
        //断点续传
        if (remoteSize > 0) {
            ftpClient.setRestartOffset(remoteSize);
            process = remoteSize / step;
            raf.seek(remoteSize);
            localreadbytes = remoteSize;
        }
        byte[] bytes = new byte[1024];
        int c;
        while ((c = raf.read(bytes)) != -1) {
            out.write(bytes, 0, c);
            localreadbytes += c;
            if (step == 0) {  //文件太小....
                logger.info("上传进度:100%");
            } else {
                if (localreadbytes / step != process) {
                    process = localreadbytes / step;
                    logger.info("上传进度:" + process);
                }
            }
        }
        out.flush();
        raf.close();
        out.close();
        boolean result = ftpClient.completePendingCommand();
       
        if (remoteSize > 0) {
            status = result ? UploadStatus.Upload_From_Break_Success : UploadStatus.Upload_From_Break_Failed;
        } else {
            status = result ? UploadStatus.Upload_New_File_Success : UploadStatus.Upload_New_File_Failed;
        }

        if (result) {
        	if (!(ftpClient.rename(remoteFile+".bak", remoteFile))) {
        		
        		if (remoteSize > 0) {
                    status = UploadStatus.Upload_From_Break_Failed;
                } else {
                    status = UploadStatus.Upload_New_File_Failed;
                }
        		
        		logger.error(remoteFile+".bak上传文件重命名失败！");
        	}
        }
        return status;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }
}
