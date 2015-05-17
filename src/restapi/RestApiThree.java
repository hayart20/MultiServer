package restapi;

import util.FileManager;

/**
 * REST API 3 class.
 * response for REST API 1 request.
 * @author Hayk
 *
 */
public class RestApiThree extends RestApiBase {
	/**
	 * format given message.
	 * format response message for api3.
	 * @param givenMessage
	 * @return
	 */
	@Override
	public String formatResponse(String givenMessage){
		return "File copied to:" + givenMessage;
	}
	
	/**
	 * Create unique server side file copy.
	 * @param givenMessage
	 * @return
	 */
	@Override
	public String proccess(String url, String target){
		return formatResponse(FileManager.copyFile(url, target));
	}
}
