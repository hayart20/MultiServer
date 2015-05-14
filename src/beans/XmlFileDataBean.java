package beans;

public class XmlFileDataBean {
	private String logFile;
	private String targetPathPrefix;
	private String host;
	private int port;

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getTargetPathPrefix() {
		return targetPathPrefix;
	}

	public void setTargetPathPrefix(String targetPathPrefix) {
		this.targetPathPrefix = targetPathPrefix;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
