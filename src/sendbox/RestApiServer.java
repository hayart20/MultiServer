package sendbox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import beans.XmlFileDataBean;
import servers.MultiThreadedServer;
import util.ReadXMLManager;

/**
 * This is server start point.
 * 
 * @author hayk
 *
 */
public class RestApiServer {
	
	public static void main(String args[]){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		System.out.println(dateFormat.format(date) + " REST API Sample Server starting…");
		System.out.println(dateFormat.format(date) + " Environment detected: Windows MISSING!!!!");
		
		//read configuration file
		String arg = null;
		 if(args.length > 0 && args[0] != null){
             arg = String.valueOf(args[0]);
		 } else {
			 //for testing purpose
			 //arg = "/home/hayk/projects/taxpayer-wk/MultiServer/tmp_folder/myApiServer.xml";
			 arg = "C:\\gitProjects\\MultiServer\\tmp_folder\\myApiServer.xml";
			 
		 }
		 ReadXMLManager configFile = new ReadXMLManager();
		 configFile.setFilePath(arg);
		 System.out.println(dateFormat.format(date) + " Loading configuration: " + arg);
		 XmlFileDataBean configBean = configFile.parseServerXmlFile();
		 
		 System.out.println(" Server listening startup time: " + dateFormat.format(date));
		 MultiThreadedServer server = new MultiThreadedServer(configBean);
		 new Thread(server).start();
		 
		 System.out.println(" Listening for client requests on: " + configBean.getHost() + ":" +configBean.getPort());
		/*
    	try {
    	    Thread.sleep(20 * 1000);
    	} catch (InterruptedException e) {
    	    e.printStackTrace();
    	}
    	System.out.println("Stopping Server");
    	server.stop();*/
    }
}
