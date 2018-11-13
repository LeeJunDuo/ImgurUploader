package jun.demo.helper.imgur;

import java.util.LinkedHashMap;

public class PostResult {
	private LinkedHashMap data;
	private Boolean success;
	private Integer status;

	public PostResult(LinkedHashMap data, Boolean success, Integer status) {
		this.data = data;
		this.success = success;
		this.status = status;
	}

	public PostResult() {
	}

	public LinkedHashMap getData() {
		return data;
	}

	public void setData(LinkedHashMap data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
