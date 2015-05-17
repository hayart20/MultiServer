package restapi;

import util.FileManager;

/**
 * REST API 2 class.
 * response for REST API 1 request.
 * @author Hayk
 *
 */
public class RestApiTwo extends RestApiBase {
	
	/**
	 * format given message.
	 * format response message for api2.
	 * @param givenMessage
	 * @return
	 */
	@Override
	public String formatResponse(String givenMessage){
		return "MD5:" + givenMessage + " Bytes";
	}
	
	/**
	 * Return MD5 hash string based on the file content .
	 * @param givenMessage
	 * @return
	 */
	@Override
	public String proccess(String url, String targeturl){
		return formatResponse(FileManager.getFileMD5(url));
	}
}
