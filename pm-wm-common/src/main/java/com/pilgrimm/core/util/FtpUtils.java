package com.pilgrimm.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp 宸ュ叿绫�
 * 
 * @author FeiFeiLi
 * @createdAt 2016骞�11鏈�14鏃�
 */
public class FtpUtils {

	private static String ftpHostName = "127.0.0.1";
	private static int ftpPort = 11165;
	private static String ftpUserName = "user";
	private static String ftpUserPwd = "pass";
	private static String ftpBasePath = "";

	/**
	 * @return the ftpHostName
	 */
	public static String getFtpHostName() {
		return ftpHostName;
	}

	/**
	 * @param ftpHostName
	 *            the ftpHostName to set
	 */
	public static void setFtpHostName(String ftpHostName) {
		FtpUtils.ftpHostName = ftpHostName;
	}

	/**
	 * @return the ftpPort
	 */
	public static int getFtpPort() {
		return ftpPort;
	}

	/**
	 * @param ftpPort
	 *            the ftpPort to set
	 */
	public static void setFtpPort(int ftpPort) {
		FtpUtils.ftpPort = ftpPort;
	}

	/**
	 * @return the ftpUserName
	 */
	public static String getFtpUserName() {
		return ftpUserName;
	}

	/**
	 * @param ftpUserName
	 *            the ftpUserName to set
	 */
	public static void setFtpUserName(String ftpUserName) {
		FtpUtils.ftpUserName = ftpUserName;
	}

	/**
	 * @return the ftpUserPwd
	 */
	public static String getFtpUserPwd() {
		return ftpUserPwd;
	}

	/**
	 * @param ftpUserPwd
	 *            the ftpUserPwd to set
	 */
	public static void setFtpUserPwd(String ftpUserPwd) {
		FtpUtils.ftpUserPwd = ftpUserPwd;
	}

	/**
	 * @return the ftpBasePath
	 */
	public static String getFtpBasePath() {
		return ftpBasePath;
	}

	/**
	 * @param ftpBasePath
	 *            the ftpBasePath to set
	 */
	public static void setFtpBasePath(String ftpBasePath) {
		FtpUtils.ftpBasePath = ftpBasePath;
	}

	/**
	 * Description: 鍚慒TP鏈嶅姟鍣ㄤ笂浼犳枃浠�
	 * 
	 * 
	 * @param filePath
	 *            FTP鏈嶅姟鍣ㄦ枃浠跺瓨鏀捐矾寰勩��
	 * @param filename
	 *            涓婁紶鍒癋TP鏈嶅姟鍣ㄤ笂鐨勬枃浠跺悕
	 * @param file
	 *            鏂囦欢
	 * @return 鎴愬姛杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 */
	public static boolean uploadFile(String filePath, String filename, File file) {

		String host = getFtpHostName();
		int port = getFtpPort();
		String username = getFtpUserName();
		String password = getFtpUserPwd();
		String basePath = getFtpBasePath();
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 杩炴帴FTP鏈嶅姟鍣�
			ftp.connect(host, port);
			// 濡傛灉閲囩敤榛樿绔彛锛屽彲浠ヤ娇鐢╢tp.connect(host)鐨勬柟寮忕洿鎺ヨ繛鎺TP鏈嶅姟鍣�
			ftp.login(username, password);
			// 鐧诲綍
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			// 鍒囨崲鍒颁笂浼犵洰褰�
			if (!ftp.changeWorkingDirectory(basePath + filePath)) {
				// 濡傛灉鐩綍涓嶅瓨鍦ㄥ垱寤虹洰褰�
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)){
						continue;
					}
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						String path = new String(tempPath.getBytes("utf-8"), "8859_1");
						ftp.login(username, password);
						// 鐧诲綍
						reply = ftp.getReplyCode();
						if (!ftp.makeDirectory(path)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(path);
						}
					}
				}
			}
			// 璁剧疆涓婁紶鏂囦欢鐨勭被鍨嬩负浜岃繘鍒剁被鍨�
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setControlEncoding("UTF-8");
			ftp.enterLocalPassiveMode();
			// 涓婁紶鏂囦欢
			FileInputStream input = new FileInputStream(file);
			if (!ftp.storeFile(new String(filename.getBytes("utf-8"), "8859_1"), input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * Description: 鍚慒TP鏈嶅姟鍣ㄤ笂浼犳枃浠�
	 * 
	 * @param host
	 *            FTP鏈嶅姟鍣╤ostname
	 * @param port
	 *            FTP鏈嶅姟鍣ㄧ鍙�
	 * @param username
	 *            FTP鐧诲綍璐﹀彿
	 * @param password
	 *            FTP鐧诲綍瀵嗙爜
	 * @param basePath
	 *            FTP鏈嶅姟鍣ㄥ熀纭�鐩綍
	 * @param filePath
	 *            FTP鏈嶅姟鍣ㄦ枃浠跺瓨鏀捐矾寰勩��
	 * @param filename
	 *            涓婁紶鍒癋TP鏈嶅姟鍣ㄤ笂鐨勬枃浠跺悕
	 * @param file
	 *            鏂囦欢
	 * @return 鎴愬姛杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 */
	public static boolean uploadFile(String host, int port, String username, String password, String basePath,
			String filePath, String filename, File file) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 杩炴帴FTP鏈嶅姟鍣�
			ftp.connect(host, port);
			// 濡傛灉閲囩敤榛樿绔彛锛屽彲浠ヤ娇鐢╢tp.connect(host)鐨勬柟寮忕洿鎺ヨ繛鎺TP鏈嶅姟鍣�
			ftp.login(username, password);
			// 鐧诲綍
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			// 鍒囨崲鍒颁笂浼犵洰褰�
			if (!ftp.changeWorkingDirectory(basePath + filePath)) {
				// 濡傛灉鐩綍涓嶅瓨鍦ㄥ垱寤虹洰褰�
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)){
						continue;
					}
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 璁剧疆涓婁紶鏂囦欢鐨勭被鍨嬩负浜岃繘鍒剁被鍨�
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setControlEncoding("UTF-8");
			ftp.enterLocalPassiveMode();
			// 涓婁紶鏂囦欢
			FileInputStream input = new FileInputStream(file);
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * Description: 浠嶧TP鏈嶅姟鍣ㄤ笅杞芥枃浠�
	 * 
	 * @param host
	 *            FTP鏈嶅姟鍣╤ostname
	 * @param port
	 *            FTP鏈嶅姟鍣ㄧ鍙�
	 * @param username
	 *            FTP鐧诲綍璐﹀彿
	 * @param password
	 *            FTP鐧诲綍瀵嗙爜
	 * @param remotePath
	 *            FTP鏈嶅姟鍣ㄤ笂鐨勭浉瀵硅矾寰�
	 * @param fileName
	 *            瑕佷笅杞界殑鏂囦欢鍚�
	 * @param localPath
	 *            涓嬭浇鍚庝繚瀛樺埌鏈湴鐨勮矾寰�
	 * @return
	 */
	public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 濡傛灉閲囩敤榛樿绔彛锛屽彲浠ヤ娇鐢╢tp.connect(host)鐨勬柟寮忕洿鎺ヨ繛鎺TP鏈嶅姟鍣�
			ftp.login(username, password);
			// 鐧诲綍
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);
			// 杞Щ鍒癋TP鏈嶅姟鍣ㄧ洰褰�
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream os = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), os);
					os.flush();
					os.close();
				}
			}
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * 下载ftp附件 返回byte[]流
	 * 
	 * @param remotePath
	 *            ftp目录
	 * @param fileName
	 *            ftp文件名称 带 后缀
	 * @return
	 */
	public static byte[] downloadFileForByte(String remotePath, String fileName) {
		String host = FtpUtils.getFtpHostName();
		int port = FtpUtils.getFtpPort();
		String username = FtpUtils.getFtpUserName();
		String password = FtpUtils.getFtpUserPwd();
		FTPClient ftp = new FTPClient();
		InputStream is = null;
		byte[] by = null;
		try {
			int reply;
			ftp.connect(host, port);
			// 濡傛灉閲囩敤榛樿绔彛锛屽彲浠ヤ娇鐢╢tp.connect(host)鐨勬柟寮忕洿鎺ヨ繛鎺TP鏈嶅姟鍣�
			ftp.login(username, password);
			// 鐧诲綍
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
			// 杞Щ鍒癋TP鏈嶅姟鍣ㄧ洰褰�
			ftp.changeWorkingDirectory(remotePath);
			is = ftp.retrieveFileStream(fileName);
			if (null != is) {
				by = toByteArray(is);
			}
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return by;
	}

	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		copy(input, output);
		return output.toByteArray();
	}

	public static int copy(InputStream input, OutputStream output) throws IOException {
		long count = copyLarge(input, output);
		if (count > 2147483647L) {
			return -1;
		}
		return (int) count;
	}

	public static long copyLarge(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[4096];
		long count = 0L;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}
}
