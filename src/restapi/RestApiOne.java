package restapi;

import util.FileManager;

/**
 * REST API 1 class.
 * response for REST API 1 request.
 * @author Hayk
 *
 */
public class RestApiOne extends RestApiBase {
	/**
	 * format given message.
	 * format response message for api1.
	 * @param givenMessage
	 * @return
	 */
	@Override
	public String formatResponse(String givenMessage){
		return "File size:" + givenMessage + " Bytes";
	}
	
	/**
	 * make operation by given url.
	 * this method return file size.
	 * @param givenMessage
	 * @return
	 */
	@Override
	public String proccess(String url, String targeturl){
		return formatResponse(FileManager.getFileSize(url));
	}
}
