package servers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;

import restapi.RestApiBase;
import restapi.RestApiOne;
import restapi.RestApiThree;
import restapi.RestApiTwo;
import beans.XmlFileDataBean;

/**
 * 
 */
public class WorkerRunnable implements Runnable {

	protected Socket clientSocket = null;
	protected XmlFileDataBean configBean = null;

	/**
	 * 
	 * @param clientSocket
	 * @param configBean
	 */
	public WorkerRunnable(Socket clientSocket, XmlFileDataBean configBean) {
		this.clientSocket = clientSocket;
		this.configBean = configBean;
	}

	/**
	 * 
	 */
	public void run() {
		try {
			InputStream input = clientSocket.getInputStream();
			OutputStream output = clientSocket.getOutputStream();
			String responseMeassage = "";
			String[] parseRequest = parseRequest();
			if(parseRequest != null && parseRequest.length == 2) {
				RestApiBase api = new RestApiBase();
				if (parseRequest[0].equals("api-1")){
					//responseMeassage = "File size: " + FileManager.getFileSize(parseRequest[1]) + " Bytes" ;
					api = new RestApiOne();
					responseMeassage = api.proccess(parseRequest[1], null);
				} else if (parseRequest[0].equals("api-2")){
					api = new RestApiTwo();
					responseMeassage = api.proccess(parseRequest[1], null);
				} else if (parseRequest[0].equals("api-3")){
					api = new RestApiThree();
					responseMeassage = api.proccess(parseRequest[1], configBean.getTargetPathPrefix());				
				}
			} else {
				responseMeassage = "Please set paramater";
			}
			output.write(("HTTP/1.1 200 OK\n\n " + responseMeassage).getBytes());
			output.close();
			input.close();
		} catch (IOException e) {
			// report exception somewhere.
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse request and return array which contains Api name and file path.
	 * 
	 * @return
	 */
	private String[] parseRequest(){
		String[] result = new String[2];//result[0] Api name result[0] filepath
		BufferedReader in = null;
		try {
			in = new BufferedReader( 
					new InputStreamReader(this.clientSocket.getInputStream()));
			String s=null;
			while ((s=in.readLine())!=null) {
				if (s.indexOf("GET") > -1) {
			       s = s.substring(4);
			       int i = s.indexOf(" ");
			       String string = s.substring(1, i);
			       String[] parts = string.split("=");
			       if(parts.length == 2){
			    	   String part1 = parts[0];  
			    	   String[] part1array = part1.split("\\?");
			    	   if(part1array.length == 2){
			    		   result[0] = part1array[0];
			    		   String filename = parts[1];
			    		   filename = URLDecoder.decode(filename, "UTF-8");
			    		   result[1] = filename; 
			    		   return result;
			    	   }
			    	   
			       }
				} 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}