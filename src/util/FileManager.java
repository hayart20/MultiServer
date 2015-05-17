package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class work with the file. for example return given file size, MD5 for
 * given file and copy file.
 * 
 * @author hayk
 *
 */
public class FileManager {

	/**
	 * Return given file size by byte. as argument get file path.
	 * 
	 * @param filepath
	 * @return
	 */
	public static String getFileSize(String filepath) {
		File file = new File(filepath);
		String filesize = null;
		if (file.exists()) {
			filesize = String.valueOf(file.length());
		}
		return filesize;
	}

	/**
	 * Return given file size by byte. as argument get file path.
	 * 
	 * @param filepath
	 * @return
	 */
	public static String getFileMD5(String filepath) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");

			FileInputStream fis = new FileInputStream(filepath);

			byte[] dataBytes = new byte[1024];

			int nread = 0;
			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}
			fis.close();
			byte[] mdbytes = md.digest();
			// convert the byte to hex format method 
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < mdbytes.length; i++) {
				String hex = Integer.toHexString(0xff & mdbytes[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Copy file. as argument get file path, and target file path with prefix.
	 * 
	 * @param filepath
	 * @return
	 */
	public static String copyFile(String sourcefilepath, String prefix) {
		InputStream inStream = null;
		OutputStream outStream = null;
		long startTimeMillis = System.currentTimeMillis();
		try {
			String destfilepath = getTargetFilepath(sourcefilepath, prefix);
			
			if(destfilepath == null){
				System.out.println("Target path incorrect.");
				return null;
			}
			
			File afile = new File(sourcefilepath);
			File bfile = new File(destfilepath);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			int copiedlength = 0;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);
				copiedlength += length;
			}

			inStream.close();
			outStream.close();
			long endTimeMillis = System.currentTimeMillis();

			return destfilepath + " copied:" + copiedlength + " bytes, time:"
					+ (endTimeMillis - startTimeMillis) + " ms";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Return target file name and path.
	 * 
	 * @param sourcefilepath
	 * @param prefix
	 * @return
	 */
	public static String getTargetFilepath(String sourcefilepath, String prefix) {
		long unicfileprefix = System.currentTimeMillis(); // as unique I am
															// using current
															// time in millis.
		String targetfilepath = null;
		int index = sourcefilepath.lastIndexOf("/");//for linux specific path
		if(index == -1){//if not found try to window specific path
			index = sourcefilepath.lastIndexOf("\\");//for linux specific path
		}

		if(index != -1){// if found correct path
			String fileName = sourcefilepath.substring(index + 1);
			targetfilepath = prefix + unicfileprefix + fileName;
		}	
		
		return targetfilepath;
	}

}
