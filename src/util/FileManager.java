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

public class FileManager {
	
	public static String getFileSize(String filepath){
		File file = new File(filepath);
		String filesize = "";
		if(file.exists()){
			filesize = String.valueOf(file.length());
		}
		return filesize; 
	}
	
	public static String getFileMD5(String filepath) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		
        FileInputStream fis = new FileInputStream(filepath);
 
        byte[] dataBytes = new byte[1024];
 
        int nread = 0; 
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        //System.out.println("Digest(in hex format): " + sb.toString());
 
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<mdbytes.length;i++) {
    		String hex=Integer.toHexString(0xff & mdbytes[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	//System.out.println("Digest(in hex format):: " + hexString.toString());
    	return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
	}
	
	public static String copyFile(String sourcefilepath, String prefix) {
		InputStream inStream = null;
		OutputStream outStream = null;
		long startTimeMillis = System.currentTimeMillis();
	    	try{
	    		String destfilepath = getDestFilepath(sourcefilepath, prefix);
	    	    File afile =new File(sourcefilepath);
	    	    File bfile =new File(destfilepath);
	 
	    	    inStream = new FileInputStream(afile);
	    	    outStream = new FileOutputStream(bfile);
	 
	    	    byte[] buffer = new byte[1024];
	 
	    	    int length;
	    	    int copiedlength = 0;
	    	    //copy the file content in bytes 
	    	    while ((length = inStream.read(buffer)) > 0){
	 
	    	    	outStream.write(buffer, 0, length);
	    	    	copiedlength += length;
	    	    }
	 
	    	    inStream.close();
	    	    outStream.close();
	    	    long endTimeMillis = System.currentTimeMillis();
	    	    
	    	    return destfilepath + " copied:" + copiedlength + " bytes, time:" + (endTimeMillis - startTimeMillis) + " ms";
	 
	    	}catch(IOException e){
	    		e.printStackTrace();
	    	}
	    	return null;
	}
	
	public static String getDestFilepath(String sourcefilepath, String prefix){
		long unicfileprefix = System.currentTimeMillis();
		String destfilepath = "";
		int index = sourcefilepath.lastIndexOf("/");
		String fileName = sourcefilepath.substring(index + 1);
		destfilepath = prefix + unicfileprefix + fileName;
		return destfilepath;
	}
	
	public static void main(String[] args){
		System.out.println(getDestFilepath("/home/hayk/projects/taxpayer-wk/MultiServer/tmp_folder/Meßaufgabe.docx", "/home/hayk/projects/taxpayer-wk/MultiServer/tmp_folder/prefix_"));
	}
}
