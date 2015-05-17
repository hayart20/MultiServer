package beans;

/**
 * This class use for keep data, which is come from xml file.
 * @author hayk
 *
 */
public class XmlFileDataBean {
	
	/**
	 * server log file path.
	 */
	private String logFile;
	
	/**
	 * prefix for target file.
	 */
	private String targetPathPrefix;
	
	/**
	 * server host.
	 */
	private String host;
	
	/**
	 * server port.
	 */
	private int port;

	/**
	 * 
	 * @return
	 */
	public String getLogFile() {
		return logFile;
	}

	/**
	 * 
	 * @param logFile
	 */
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	/**
	 * 
	 * @return
	 */
	public String getTargetPathPrefix() {
		return targetPathPrefix;
	}

	/**
	 * 
	 * @param targetPathPrefix
	 */
	public void setTargetPathPrefix(String targetPathPrefix) {
		this.targetPathPrefix = targetPathPrefix;
	}

	/**
	 * 
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

}
