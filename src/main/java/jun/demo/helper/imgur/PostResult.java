package jun.demo.helper.imgur;

public class PostResult {
	private ImageInfo data;
	private Boolean success;
	private Integer status;

	public PostResult(ImageInfo data, Boolean success, Integer status) {
		this.data = data;
		this.success = success;
		this.status = status;
	}

	public ImageInfo getData() {
		return data;
	}

	public void setData(ImageInfo data) {
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
