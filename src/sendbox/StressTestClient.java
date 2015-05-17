package sendbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * multithreading client (stress-test) that sends chosen API-1, API-2 or API-3 concurrent requests for the given file path specified on command line.
 * @author Hayk
 *
 */
public class StressTestClient {

	public static void main(String[] args) {
		String url = null;
		Integer concurrentRequestCount = null;

		if (args.length > 0 && args[0] != null && args[1] != null) {
			url = String.valueOf(args[0]);
			concurrentRequestCount = Integer.valueOf(args[1]);
			StressTestClient obj = new StressTestClient();//
			obj.send(url, concurrentRequestCount);
		} else {
			System.out.println("please provide arguments. First argument requested url and second concurrent request count.");
		}
		
	}

	/**
	 * send request by given parameter. 
	 * @param url
	 * @param concurrentRequestCount
	 * @return
	 */
	public Boolean send(String url, Integer concurrentRequestCount) {
		Boolean result = null;
		for (int i = 0; i < concurrentRequestCount; i++) {
			result = sendGet(url);
			if(!result){
				return false;
			}
		}
		return true;
	}

	/**
	 * send get request by given URL.
	 * 
	 * @param url
	 * @throws MalformedURLException
	 * @throws Exception
	 */
	private Boolean sendGet(String url) {

		URL obj;
		try {
			obj = new URL(url);

			HttpURLConnection con;

			con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			int responseCode;

			responseCode = con.getResponseCode();

			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			// print result
			System.out.println(response.toString());
			in.close();
			return true;
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL has occurred. Either no legal protocol could be found in a specification string or the string could not be parsed.");
			//e.printStackTrace();
			return false;
		} catch (ProtocolException e) {
			System.out.println("There is an error in the underlying protocol, such as a TCP error.");
			//e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("There is an error. I/O exception of has occurred. It may be server is down.");
			//e.printStackTrace();
			return false;
		}

	}
}
