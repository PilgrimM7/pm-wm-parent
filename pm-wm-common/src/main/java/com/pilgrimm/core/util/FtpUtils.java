package com.pilgrimm.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {

	private static String ftpHostName = "127.0.0.1";
	private static int ftpPort = 11165;
	private static String ftpUserName = "user";
	private static String ftpUserPwd = "pass";
	private static String ftpBasePath = "";

	public static String getFtpHostName() {
		return ftpHostName;
	}

	public static void setFtpHostName(String ftpHostName) {
		FtpUtils.ftpHostName = ftpHostName;
	}

	public static int getFtpPort() {
		return ftpPort;
	}

	public static void setFtpPort(int ftpPort) {
		FtpUtils.ftpPort = ftpPort;
	}

	public static String getFtpUserName() {
		return ftpUserName;
	}

	public static void setFtpUserName(String ftpUserName) {
		FtpUtils.ftpUserName = ftpUserName;
	}

	public static String getFtpUserPwd() {
		return ftpUserPwd;
	}

	public static void setFtpUserPwd(String ftpUserPwd) {
		FtpUtils.ftpUserPwd = ftpUserPwd;
	}

	public static String getFtpBasePath() {
		return ftpBasePath;
	}

	public static void setFtpBasePath(String ftpBasePath) {
		FtpUtils.ftpBasePath = ftpBasePath;
	}

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
			ftp.connect(host, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			if (!ftp.changeWorkingDirectory(basePath + filePath)) {
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
						reply = ftp.getReplyCode();
						if (!ftp.makeDirectory(path)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(path);
						}
					}
				}
			}
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setControlEncoding("UTF-8");
			ftp.enterLocalPassiveMode();
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

	public static boolean uploadFile(String host, int port, String username, String password, String basePath,
			String filePath, String filename, File file) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			if (!ftp.changeWorkingDirectory(basePath + filePath)) {
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

	public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);
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
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
			}
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
