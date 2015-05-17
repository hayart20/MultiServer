package sendbox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StressTestClient {
	private final String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws Exception{
		String url = null;
		Integer concurrentRequestCount = null;
		
		 if(args.length > 0 && args[0] != null  && args[1] != null){
			 url = String.valueOf(args[0]);
			 concurrentRequestCount = Integer.valueOf(args[1]);
		 } else {
			 System.out.println("please provide arguments. First argument requested url and second concurrent request count.");
			 //for testing purpose
			 url = "http://localhost:8123/api-3?file=c:\\gitProjects\\MultiServer\\tmp_folder\\sample.docx";
			 concurrentRequestCount = 4;
		 }
		 StressTestClient obj = new StressTestClient();//
		 
		 for (int i = 0; i< concurrentRequestCount; i++){
			 obj.sendGet(url);
		 }
	}
	
	private void sendGet(String url) throws Exception {
		 
		//String url = "http://www.google.com/search?q=mkyong";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		
		//con.getInputStream();
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
}
