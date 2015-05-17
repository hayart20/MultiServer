package restapi;

/**
 * REST API base class.
 * @author Hayk
 *
 */
public class RestApiBase {
	
	/**
	 * format given message.
	 * this method will be override on the child class.
	 * @param givenMessage
	 * @return
	 */
	public String formatResponse(String givenMessage){
		return "Format given Message" + givenMessage;
	}
	
	/**
	 * make operation by given url.
	 * this method will be override on the child class.
	 * @param givenMessage
	 * @return
	 */
	public String proccess(String url, String targeturl){
		return "make proccess by given url" + url;
	}
}
