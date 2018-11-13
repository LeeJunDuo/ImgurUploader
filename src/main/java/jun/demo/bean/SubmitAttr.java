package jun.demo.bean;

public class SubmitAttr {
	private String sinkPath;
	private String clientId;
	private String submitUrl;

	public SubmitAttr(String sinkPath, String clientId, String submitUrl) {
		this.sinkPath = sinkPath;
		this.clientId = clientId;
		this.submitUrl = submitUrl;
	}

	public String getSinkPath() {
		return sinkPath;
	}

	public void setSinkPath(String sinkPath) {
		this.sinkPath = sinkPath;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
}
