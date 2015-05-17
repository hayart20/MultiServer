package util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * test fileManager class methods.
 * @author hayk
 *
 */
public class FileManagerTest {

	@Test
	/**
	 * Test file size method.
	 */
	public void testFileSize() {
		FileManager fileManager = new FileManager();
		String fileSize = fileManager.getFileSize("/home/hayk/projects/taxpayer-wk/MultiServer/tmp_folder/Meßaufgabe.docx");
		assertNotNull(fileSize);
	}

	@Test
	/**
	 * Test file MD5 method.
	 */
	public void testFileMD5() {
		FileManager fileManager = new FileManager();
		String fileMD5 = fileManager.getFileMD5("/home/hayk/projects/taxpayer-wk/MultiServer/tmp_folder/Meßaufgabe.docx");
		assertNotNull(fileMD5);
	}
	
	@Test
	/**
	 * Test copy file method.
	 */
	public void testCopyFile() {
		FileManager fileManager = new FileManager();
		//String fileCopyResult = fileManager.copyFile("/home/hayk/projects/taxpayer-wk/MultiServer/tmp_folder/Meßaufgabe.docx", "/home/hayk/projects/taxpayer-wk/MultiServer/tmp_folder/prefix_");
		String fileCopyResult = fileManager.copyFile("C:\\gitProjects\\MultiServer\\tmp_folder\\Me�aufgabe.docx", "C:\\gitProjects\\MultiServer\\tmp_folder\\unique_");
		
		assertNotNull(fileCopyResult);
	}
}
