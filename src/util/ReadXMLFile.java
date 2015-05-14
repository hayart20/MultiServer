package util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import beans.XmlFileDataBean;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadXMLFile {
	private String filePath = null;

	public void setFilePath(String path) {
		this.filePath = path;
	}

	public String getFilePath() {
		return this.filePath;
	}

	private String getStringElement(Element eElement, String tagName) {
		try {
			NodeList logFileList = eElement.getElementsByTagName(tagName);
			Element logFile = (Element) logFileList.item(0);
			NodeList textlogFile = logFile.getChildNodes();
			String result = ((Node) textlogFile.item(0)).getNodeValue().trim();
			return result;
		} catch (NullPointerException e) {
			return null;
		}
	}

	private int getIntElement(Element eElement, String tagName) {
		try {
			NodeList logFileList = eElement.getElementsByTagName(tagName);
			Element logFile = (Element) logFileList.item(0);
			NodeList textlogFile = logFile.getChildNodes();
			String strresult = ((Node) textlogFile.item(0)).getNodeValue()
					.trim();
			int result = Integer.valueOf(strresult);
			return result;
		} catch (NullPointerException e) {
			return -1;
		}
	}

	public XmlFileDataBean parse() {
		XmlFileDataBean result = null;
		if (filePath != null) {
			try {

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				System.out.println(dateFormat.format(date) + " Configuration loaded…: ");
				File fXmlFile = new File(filePath);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(fXmlFile);

				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("server");
				int totalPersons = nList.getLength();
				if (totalPersons != 0) {
					Node node = nList.item(0);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) node;
						System.out.println(dateFormat.format(date) + " Printing configuration: ");
						
						result = new XmlFileDataBean();
						
						String logFile = getStringElement(eElement, "logFile");
						if(logFile != null) {
							System.out.println(dateFormat.format(date) + " Log file path: " + logFile);
							result.setLogFile(logFile);
						} else {
							System.out.println(dateFormat.format(date) + " ERROR can not read Log file path: ");
							return null;
						}
						
						String targetPathPrefix = getStringElement(eElement, "targetPathPrefix");
						if(targetPathPrefix != null) {
							System.out.println(dateFormat.format(date) + " Target Path: " + targetPathPrefix);
							result.setTargetPathPrefix(targetPathPrefix);
						} else {
							System.out.println(dateFormat.format(date) + " ERROR can not read Target Path: ");
							return null;
						}
						
						String host = getStringElement(eElement, "host");
						if(host != null) {
							System.out.println(dateFormat.format(date) + " Listening host: " + host);
							result.setHost(host);
						} else {
							System.out.println(dateFormat.format(date) + " ERROR can not read host: ");
							return null;
						}
						

						int port = getIntElement(eElement, "port");
						if(port != -1) {
							System.out.println(dateFormat.format(date) + " Listening port: " + port);
							result.setPort(port);
						} else {
							System.out.println(dateFormat.format(date) + " ERROR can not read port: ");
							return null;
						}
					}
				}
			} catch (SAXParseException err) {
				System.out.println("** Parsing error" + ", line "
						+ err.getLineNumber() + ", uri " + err.getSystemId());
				System.out.println(" " + err.getMessage());
			} catch (SAXException e) {
				Exception x = e.getException();
				((x == null) ? e : x).printStackTrace();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		} else {
			System.out.println("File Pathe missing");
		}
		return result;
	}

}