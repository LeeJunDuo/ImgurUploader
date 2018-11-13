package jun.demo.bean;

import java.util.Map;

public class UploadEvent {
	private String id;
	private Map<String, String> imageStatus;

	public UploadEvent(String id, Map<String, String> imageStatus) {
		this.id = id;
		this.imageStatus = imageStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(Map<String, String> imageStatus) {
		this.imageStatus = imageStatus;
	}
}
